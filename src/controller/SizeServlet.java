package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MallBranchGodown;
import model.Size;
import service.SizeService;

@WebServlet("/SizeServlet")
public class SizeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Size size = new Size();
		SizeService sizeServe = new SizeService();
		MallBranchGodown currentMall = (MallBranchGodown) request.getSession().getAttribute("Current Mall");
		size.setMallBranchGodown(currentMall);
		size.setSize(request.getParameter("txtSizeName"));
		size.setStatus('A');
		long flag = 0;
		flag = sizeServe.insert(size);
		if(flag>0){
			response.sendRedirect("add_size.jsp?sizeInsertedSuccessful=true");
		}
		else if(flag==0){
			response.sendRedirect("add_size.jsp?sizeInsertedSuccessful=false");
		}
	}
}