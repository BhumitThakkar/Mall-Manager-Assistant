package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Reminder;
import service.ReminderService;

@WebServlet("/ReminderChangesServlet")
public class ReminderChangesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String flagString = request.getParameter("flag");
		ReminderService reminderServe = new ReminderService();
		Reminder reminder = reminderServe.getReminderById(id);
		if(flagString.equals("edit")){
			request.getSession().setAttribute("Edit Reminder",reminder);
			response.sendRedirect("reminder.jsp?editReminder=true");
		}
		if(flagString.equals("delete")){
			reminderServe.delete(reminder);
			response.sendRedirect("reminder.jsp?reminderDeletedSuccessfully=true");
	}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Reminder reminder = new Reminder();
		ReminderService reminderServe = new ReminderService();
		Reminder reminderEditSessionObj = (Reminder) request.getSession().getAttribute("Edit Reminder");
		reminder.setId(reminderEditSessionObj.getId());
		reminder.setMallBranchGodown(reminderEditSessionObj.getMallBranchGodown());
		reminder.setProductNumber(request.getParameter("txtProductNumber"));
		reminder.setReminderDetails(request.getParameter("txtReminder"));
		reminder.setStatus(reminderEditSessionObj.getStatus());
		reminderServe.update(reminder);
		request.removeAttribute("Edit Reminder");
		response.sendRedirect("reminder.jsp?reminderUpdatedSuccessfully=true");
	}
}
