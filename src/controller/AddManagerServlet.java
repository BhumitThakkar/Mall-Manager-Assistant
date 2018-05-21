package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MallBranchGodown;
import model.Manager;
import service.CommonService;
import service.MallService;
import service.ManagerService;

@WebServlet("/AddManagerServlet")
public class AddManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Manager manager = new Manager();
	ManagerService managerServe = new ManagerService();
	MallService mallServe = new MallService();
	MallBranchGodown mall = null;
	CommonService commonServe = new CommonService();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String txtManagerMail = request.getParameter("txtManagerMail");
		Manager manager = managerServe.getManagerByEmail(txtManagerMail);
		mall = (MallBranchGodown) request.getSession().getAttribute("Current Mall");
		System.out.println(mall+" - "+manager);
		commonServe.insertCommon(mall, manager, request, response);
		response.sendRedirect("index.jsp");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		long flag = 0;
  		manager.setName(request.getParameter("txtManagerName"));
  		manager.setMobile_number(Long.parseLong(request.getParameter("txtMobile")));
		manager.setMail_id(request.getParameter("txtMailId"));
		manager.setPassword(request.getParameter("txtPassword"));
		manager.setStatus('A');
		flag = managerServe.checkMailExistancy(manager);
		if(flag==1)
		{
			request.getSession().setAttribute("Email Exists Error","Email Already Exists");
			response.sendRedirect("signup.jsp?txtNumber="+manager.getMobile_number()+"&txtName="+manager.getName());
		}
		else{
			mall = (MallBranchGodown) request.getSession().getAttribute("Current Mall");
			commonServe.insertThroughManager(mall, manager);
			response.sendRedirect("index.jsp");
		}
	}

}