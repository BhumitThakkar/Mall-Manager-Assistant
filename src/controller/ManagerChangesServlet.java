package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Manager;
import service.ManagerService;

@WebServlet("/ManagerChangesServlet")
public class ManagerChangesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Manager manager = null;
	ManagerService managerServe = new ManagerService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long flag = 0;
  		manager = new Manager();
		String oldMail = request.getParameter("txtMailIdOld");
		Manager oldManagerDetails = managerServe.getManagerByEmail(oldMail);
  		manager.setId(oldManagerDetails.getId());
  		manager.setName(request.getParameter("txtManagerName"));
		manager.setMobile_number(Long.parseLong(request.getParameter("txtMobile")));
		manager.setMail_id(request.getParameter("txtMailId"));
		manager.setPassword(request.getParameter("txtPassword"));
		manager.setMallBranchGodowns(oldManagerDetails.getMallBranchGodowns());
		manager.setStatus('A');
		flag = managerServe.checkMailExistancyForChanges(manager,request.getParameter("txtMailIdOld"));
		if(flag==1)
		{
			request.getSession().setAttribute("Email Exists Error","Email Already Exists");
			response.sendRedirect("account.jsp?txtNumber="+manager.getMobile_number()+"&txtName="+manager.getName());
		}
		else{
			managerServe.updateManager(manager);
			request.removeAttribute("Login Session");
			request.getSession().setAttribute("Login Session",manager.getMail_id());
			response.sendRedirect("account.jsp?changesSuccessfull=yes");
		}
	}
}