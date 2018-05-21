package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MallBranchGodown;
import model.Spoil;
import service.ProductService;
import service.SpoilService;

@WebServlet("/SpoilServlet")
public class SpoilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Spoil spoil = new Spoil();
		SpoilService spoilServe = new SpoilService();
		MallBranchGodown currentMall = (MallBranchGodown) request.getSession().getAttribute("Current Mall");		
		spoil.setMallBranchGodown(currentMall);
		spoil.setSpoilReason(request.getParameter("txtSpoilReason"));
		spoil.setProductNumber(request.getParameter("txtProductNumber"));
		try {
			Date date=new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtSpoilDate"));
			String finalSpoilDate=date.getDate()+"-"+(date.getMonth()+1)+"-"+(date.getYear()+1900);
			spoil.setSpoilDate(finalSpoilDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		spoil.setSpoilQuantity(Double.parseDouble(request.getParameter("txtSpoilQuantity")));
		long flag = 0;
		ProductService productServe = new ProductService();
		flag = productServe.updateBySpoils(spoil,currentMall);
		if(flag==-1){
			response.sendRedirect("spoilage.jsp?unknownProductSpoilError=true");
		}
		else if(flag==0){
			response.sendRedirect("spoilage.jsp?productLessThanSpoiled=true");
		}
		else if(flag==1)
		{
			flag = spoilServe.insert(spoil);
			if(flag>0){
				response.sendRedirect("spoilage.jsp?spoilageInsertedSuccessfully=true");
			}
			else if(flag==0){
				response.sendRedirect("sales.jsp?spoilageInsertedSuccessfully=false");
			}
		}
	}
}