package oncall.controller;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oncall.database.DepartmentDAO;
import oncall.database.ResourceDAO;
import oncall.model.Department;
import oncall.model.Resource;

@WebServlet("/ResourceController")
public class ResourceController extends HttpServlet {

	private ResourceDAO dao;
	
	public static final String PAGE_RESOURCE_LIST = "/resources.jsp";
	public static final String PAGE_RESOURCE = "/resource.jsp";
	
	public ResourceController() {
		dao = new ResourceDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String page = "";
		String action = request.getParameter("action");
		if (action == null) {
			action = "list";
		}

		if (action.equalsIgnoreCase("delete")) {
			page = PAGE_RESOURCE_LIST;
			int sequence = Integer.parseInt(request.getParameter("sequence"));
			dao.deleteResource(sequence);
			request.setAttribute("resources", dao.getResources());

		} else if (action.equalsIgnoreCase("edit")) {
			page = PAGE_RESOURCE;
			int sequence = Integer.parseInt(request.getParameter("sequence"));
			Resource resource = dao.getResource(sequence);
			request.setAttribute("resource", resource);
			ArrayList<Department> departments = new DepartmentDAO().getDepartments();
			request.setAttribute("departments", departments);
		} else if (action.equalsIgnoreCase("insert")) {
			page = PAGE_RESOURCE;
		} else if (action.equalsIgnoreCase("list")) {
			ArrayList<Resource> list = dao.getResources();
			request.setAttribute("resources", list);
			page = PAGE_RESOURCE_LIST;
		} else {
			throw new ServletException("Invalid Action");
		}
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Resource resource = new Resource();
		resource.setSequence(-1);
		resource.setFirstName(request.getParameter("firstname"));
		resource.setLastName(request.getParameter("lastname"));
		resource.setContactNumber(request.getParameter("contactnumber"));
		resource.setEmail(request.getParameter("email"));
		resource.setActive(request.getParameter("active").toString().equalsIgnoreCase("Y") ? true : false);
		resource.setDepartments(request.getParameterValues("departments"));
		String seq = request.getParameter("sequence");
		if (seq == null || seq.isEmpty()) {
			dao.insertResource(resource);
		} else {
			resource.setSequence(Integer.parseInt(seq));
			dao.updateResource(resource);
		}
		request.setAttribute("resources", dao.getResources());
		RequestDispatcher rd = request.getRequestDispatcher(PAGE_RESOURCE_LIST);
		rd.forward(request, response);
	}
	
}
