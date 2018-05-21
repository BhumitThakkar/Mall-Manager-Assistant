package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MallBranchGodown;
import model.Reminder;
import service.ReminderService;

@WebServlet("/ReminderServlet")
public class ReminderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Reminder reminder = new Reminder();
		ReminderService reminderServe = new ReminderService();
		MallBranchGodown currentMall = (MallBranchGodown) request.getSession().getAttribute("Current Mall");
		reminder.setMallBranchGodown(currentMall);
		reminder.setProductNumber(request.getParameter("txtProductNumber"));
		reminder.setReminderDetails(request.getParameter("txtReminder"));
		reminder.setStatus('A');
		long flag = 0;
		flag = reminderServe.insert(reminder);
		if(flag>0){
			response.sendRedirect("reminder.jsp?reminderInsertedSuccessfully=true");
		}
		else if(flag==0){
			response.sendRedirect("reminder.jsp?reminderInsertedSuccessfully=false");
		}
	}

}
