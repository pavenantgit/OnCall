package oncall.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import oncall.database.DepartmentDAO;
import oncall.model.Department;

@WebServlet("/DepartmentController")
public class DepartmentController extends HttpServlet {

	private static Logger logger = Logger.getLogger(DepartmentController.class);

	private DepartmentDAO dao;
	
	public static final String PAGE_DEPARTMENT_LIST = "/departments.jsp";
	public static final String PAGE_DEPARTMENT = "/department.jsp";
	
	public DepartmentController() {
		dao = new DepartmentDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String page = "";
		String action = request.getParameter("action");
		
		logger.info("action: " + action);

		if (action == null) {
			action = "list";
		}

		if (action.equalsIgnoreCase("delete")) {
			page = PAGE_DEPARTMENT_LIST;
			int sequence = Integer.parseInt(request.getParameter("sequence"));
			dao.deleteDepartment(sequence);
			request.setAttribute("departments", dao.getDepartments());

		} else if (action.equalsIgnoreCase("edit")) {
			page = PAGE_DEPARTMENT;
			int sequence = Integer.parseInt(request.getParameter("sequence"));
			Department department = dao.getDepartment(sequence);
			request.setAttribute("department", department);
		
		} else if (action.equalsIgnoreCase("insert")) {
			page = PAGE_DEPARTMENT;
		
		} else if (action.equalsIgnoreCase("list")) {
			ArrayList<Department> list = dao.getDepartments();
			request.setAttribute("departments", list);
			page = PAGE_DEPARTMENT_LIST;

		}
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Department department = new Department();
		department.setSequence(-1);
		department.setName(request.getParameter("name"));
		department.setDescription(request.getParameter("description"));
		department.setActive(request.getParameter("active").toString().equalsIgnoreCase("Y") ? true : false);
		String seq = request.getParameter("sequence");
		if (seq == null || seq.isEmpty()) {
			dao.insertDepartment(department);
		} else {
			department.setSequence(Integer.parseInt(seq));
			dao.updateDepartment(department);
		}
		request.setAttribute("departments", dao.getDepartments());
		RequestDispatcher rd = request.getRequestDispatcher(PAGE_DEPARTMENT_LIST);
		rd.forward(request, response);
	}
	
}
