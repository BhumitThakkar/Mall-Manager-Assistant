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
import service.ManagerService;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MallBranchGodown mall = new MallBranchGodown();
	Manager manager = null;
	ManagerService managerServe = new ManagerService();
	CommonService commonServe = new CommonService();
	
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
  				/* Add Mall Data comes here*/
 		long flag = 0;
 		
  		mall.setMall_name(request.getParameter("txtMallName"));
  		mall.setM_b_g_mb(request.getParameter("txtMBG"));
		mall.setLandmark(request.getParameter("txtLandmark"));
		mall.setArea(request.getParameter("txtArea"));
		mall.setCity(request.getParameter("txtCity"));
		mall.setPincode(Long.parseLong(request.getParameter("txtPin")));
		mall.setState(request.getParameter("txtState"));
		mall.setCountry(request.getParameter("txtCountry"));
		mall.setMobile_number(Long.parseLong(request.getParameter("txtMobile")));
		mall.setStatus('A');
		
		
		String currentLogin = (String) request.getSession().getAttribute("Login Session");
		if(currentLogin != null)
		{
			manager = managerServe.getManagerByEmail(currentLogin);
			flag = commonServe.insertThroughMall(mall, manager);
		}
		else{
			flag = commonServe.insertThroughManager(mall, manager);
		}
		if(currentLogin == null)	{
			if(flag > 0)
			{
				response.sendRedirect("login.jsp");
			}
			else{
				request.getSession().setAttribute("Insertion Error","There is some error in insertion");
				response.sendRedirect("signup.jsp");
			}
		}
		else{
			if(flag > 0)
			{
				response.sendRedirect("index.jsp");
			}
			else{
				request.getSession().setAttribute("Insertion Error","There is some error in insertion");
				response.sendRedirect("add_mall.jsp");
			}
		}
		
  	}
  	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Manager Data comes here*/
  		long flag = 0;
  		manager = new Manager();
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
			response.sendRedirect("add_mall.jsp");
		}
	}
}