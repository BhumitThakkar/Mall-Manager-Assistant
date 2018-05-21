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
import model.Sale;
import service.ProductService;
import service.SaleService;

@WebServlet("/SaleServlet")
public class SaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Sale sale = new Sale();
		SaleService saleServe = new SaleService();
		MallBranchGodown currentMall = (MallBranchGodown) request.getSession().getAttribute("Current Mall");		
		sale.setMallBranchGodown(currentMall);
		sale.setProductNumber(request.getParameter("txtProductNumber"));
		try {
			Date date=new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtSaleDate"));
			String finalSaleDate=date.getDate()+"-"+(date.getMonth()+1)+"-"+(date.getYear()+1900);
			sale.setSaleDate(finalSaleDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		sale.setSaleQuantity(Double.parseDouble(request.getParameter("txtSaleQuantity")));
		sale.setSp(Double.parseDouble(request.getParameter("txtProductSalePrice")));
		long flag = 0;
		ProductService productServe = new ProductService();
		flag = productServe.updateBySales(sale,currentMall);
		if(flag==-1){
			response.sendRedirect("sales.jsp?unknownProductSoldError=true");
		}
		else if(flag==0){
			response.sendRedirect("sales.jsp?productLessThanSold=true");
		}
		else if(flag==1)
		{
			flag = saleServe.insert(sale);
			if(flag>0){
				response.sendRedirect("sales.jsp?saleInsertedSuccessfully=true");
			}
			else if(flag==0){
				response.sendRedirect("sales.jsp?saleInsertedSuccessfully=false");
			}
		}
	}
}