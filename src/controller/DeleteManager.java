package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Manager;
import service.ManagerService;

@WebServlet("/DeleteManager")
public class DeleteManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManagerService managerServe = new ManagerService();
		String currentLogin = (String) request.getSession().getAttribute("Login Session");
		Manager manager = managerServe.getManagerByEmail(currentLogin);
		managerServe.deteteManager(manager);
		request.removeAttribute("Login Session");
		response.sendRedirect("login.jsp");
	}

}
