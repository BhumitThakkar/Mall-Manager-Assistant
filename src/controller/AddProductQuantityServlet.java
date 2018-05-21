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

import model.AddProductQuantity;
import model.MallBranchGodown;
import service.AddProductQuantityService;
import service.ProductService;

@WebServlet("/AddProductQuantityServlet")
public class AddProductQuantityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddProductQuantity addProductQuantity = new AddProductQuantity();
		AddProductQuantityService addProductQuantityServe = new AddProductQuantityService();
		MallBranchGodown currentMall = (MallBranchGodown) request.getSession().getAttribute("Current Mall");		
		addProductQuantity.setMallBranchGodown(currentMall);
		addProductQuantity.setProductNumber(request.getParameter("txtProductNumber"));
		try {
			Date date=new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtProductEntryDate"));
			String finalProductEntryDate=date.getDate()+"-"+(date.getMonth()+1)+"-"+(date.getYear()+1900);
			addProductQuantity.setProductEntryDate(finalProductEntryDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		addProductQuantity.setAddProductQuantity(Double.parseDouble(request.getParameter("txtProductQuantity")));
		Double tempCP = Double.parseDouble(request.getParameter("txtProductCostPrice"));
		Double tempSP = Double.parseDouble(request.getParameter("txtProductSalePrice"));
		if(tempCP>tempSP){
			response.sendRedirect("add_quantity.jsp?productCpMoreThanSp=true");
		}
		else{
		addProductQuantity.setCp(Double.parseDouble(request.getParameter("txtProductCostPrice")));
		addProductQuantity.setSp(Double.parseDouble(request.getParameter("txtProductSalePrice")));
		}		
		addProductQuantity.setSold(0.0);
		addProductQuantity.setSpoil(0.0);
		long flag = 0;
		ProductService productServe = new ProductService();
		flag = productServe.updateByAddProductQuantity(addProductQuantity,currentMall);
		if(flag==-1){
			response.sendRedirect("add_quantity.jsp?unknownProduct=true");
		}
		else if(flag==1)
		{
			flag = addProductQuantityServe.insert(addProductQuantity);
			if(flag>0){
				response.sendRedirect("add_quantity.jsp?productQuantityInsertedSuccessfully=true");
			}
			else if(flag==0){
				response.sendRedirect("add_quantity.jsp?productQuantityInsertedSuccessfully=false");
			}
		}
	}
}
