package oncall.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oncall.database.ScheduleDAO;
import oncall.model.Schedule;

@WebServlet("/ScheduleController")
public class ScheduleController extends HttpServlet {

	private ScheduleDAO dao;
	
	public static final String PAGE_SCHEDULE_LIST = "/schedules.jsp";
	public static final String PAGE_SCHEDULE = "/schedule.jsp";
	
	public ScheduleController() {
		dao = new ScheduleDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String page = "";
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("delete")) {
			page = PAGE_SCHEDULE_LIST;
			int sequence = Integer.parseInt(request.getParameter("sequence"));
			dao.deleteSchedule(sequence);
			request.setAttribute("schedules", dao.getSchedules());
		} else if (action.equalsIgnoreCase("edit")) {
			page = PAGE_SCHEDULE;
			int sequence = Integer.parseInt(request.getParameter("sequence"));
			Schedule schedule = dao.getSchedule(sequence);
			request.setAttribute("schedule", schedule);
		} else if (action.equalsIgnoreCase("insert")) {
			page = PAGE_SCHEDULE;
		} else if (action.equalsIgnoreCase("list")) {
			ArrayList<Schedule> list = dao.getSchedules();
			request.setAttribute("schedules", list);
			page = PAGE_SCHEDULE_LIST;
		} else {
			throw new ServletException("Invalid Action");
		}
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Schedule schedule = new Schedule();
		schedule.setSequence(-1);
		
		String dateString = request.getParameter("startdate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			date = new Date();
		}
		schedule.setStartDate(new java.sql.Date(date.getTime()));

		dateString = request.getParameter("enddate");
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			date = new Date();
		}
		schedule.setEndDate(new java.sql.Date(date.getTime()));

		schedule.setDepartment(Integer.parseInt(request.getParameter("department")));
		schedule.setResource(Integer.parseInt(request.getParameter("resource")));
		schedule.setActive(request.getParameter("active").toString().equalsIgnoreCase("Y") ? true : false);
		String seq = request.getParameter("sequence");
		if (seq == null || seq.isEmpty()) {
			dao.insertSchedule(schedule);
		} else {
			schedule.setSequence(Integer.parseInt(seq));
			dao.updateSchedule(schedule);
		}
		request.setAttribute("schedules", dao.getSchedules());
		RequestDispatcher rd = request.getRequestDispatcher(PAGE_SCHEDULE_LIST);
		rd.forward(request, response);
	}
	
}
