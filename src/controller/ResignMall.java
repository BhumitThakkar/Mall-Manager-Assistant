package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MallBranchGodown;
import model.Manager;
import service.MallService;
import service.ManagerService;

@WebServlet("/ResignMall")
public class ResignMall extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long flag=0;
		Long mallId=Long.parseLong(request.getParameter("txtMallId"));
		ManagerService managerServe = new ManagerService();
		String currentLogin = (String) request.getSession().getAttribute("Login Session");
		Manager manager = managerServe.getManagerByEmail(currentLogin);
		MallService mallServe = new MallService();
		MallBranchGodown mall = mallServe.getMallById(mallId);
		flag = managerServe.resignSelectedMall(manager,mall);
		if(flag > 0){
			if(mall.getManagers().equals(null)){
				mallServe.DeleteMall(mall);
			}
			response.sendRedirect("account.jsp?changesSuccessfull=yes");
		}
		else
			response.sendRedirect("account.jsp?changesSuccessfull=no");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}