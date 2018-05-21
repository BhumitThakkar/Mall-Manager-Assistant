package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MallBranchGodown;
import model.Product;
import service.AddProductQuantityService;
import service.ProductService;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product product = new Product();
		ProductService productServe = new ProductService();
		MallBranchGodown currentMall = (MallBranchGodown) request.getSession().getAttribute("Current Mall");
		
		product.setMallBranchGodown(currentMall);
		product.setProductNumber(request.getParameter("txtProductNumber"));
		product.setCompany(request.getParameter("txtProductCompany"));
		product.setProductName(request.getParameter("txtProductName"));
		product.setColor(request.getParameter("txtProductColor"));
		product.setCategory(request.getParameter("txtProductCategory"));
		product.setSize(request.getParameter("txtProductSize"));
		product.setUnit(request.getParameter("txtProductUnit"));
		product.setMp(Double.parseDouble(request.getParameter("txtProductMarketPrice")));
		product.setTotalQuantity(Double.parseDouble(request.getParameter("txtProductQuantity")));
		product.setThresholdQuantity(Double.parseDouble(request.getParameter("txtThresholdProductQuantity")));
		Double tempCP = Double.parseDouble(request.getParameter("txtTempCP"));
		Double tempSP = Double.parseDouble(request.getParameter("txtTempSP"));
		if(tempCP>tempSP){
			response.sendRedirect("add_product.jsp?productCpMoreThanSp=true");
		}
		else{
			product.setTempCP(tempCP);
			product.setTempSP(tempSP);
		}
		if("".equals(request.getParameter("txtThresholdSalePerDay"))){
			product.setThresholdSalePerDay(0.0);
		}
		else{
			product.setThresholdSalePerDay(Double.parseDouble(request.getParameter("txtThresholdSalePerDay")));			
		}
		if("".equals(request.getParameter("txtThresholdSalePerWeek"))){
			product.setThresholdSalePerWeek(0.0);
		}
		else
		product.setThresholdSalePerWeek(Double.parseDouble(request.getParameter("txtThresholdSalePerWeek")));
		if("".equals(request.getParameter("txtThresholdSalePerMonth"))){
			product.setThresholdSalePerMonth(0.0);
		}
		else
		product.setThresholdSalePerMonth(Double.parseDouble(request.getParameter("txtThresholdSalePerMonth")));
		if("".equals(request.getParameter("txtThresholdSalePerMiniSem"))){
			product.setThresholdSalePerMiniSem(0.0);
		}
		else
		product.setThresholdSalePerMiniSem(Double.parseDouble(request.getParameter("txtThresholdSalePerMiniSem")));
		if("".equals(request.getParameter("txtThresholdSalePerSem"))){
			product.setThresholdSalePerSem(0.0);
		}
		else
		product.setThresholdSalePerSem(Double.parseDouble(request.getParameter("txtThresholdSalePerSem")));
		if("".equals(request.getParameter("txtThresholdSalePerYear"))){
			product.setThresholdSalePerYear(0.0);
		}
		else
		product.setThresholdSalePerYear(Double.parseDouble(request.getParameter("txtThresholdSalePerYear")));
		
		if("".equals(request.getParameter("txtExpiry"))){
			product.setExpiry("DEFAULT");
		}
		else{
			try {
				Date date=new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtExpiry"));
				String finalExpiryDate=date.getDate()+"-"+(date.getMonth()+1)+"-"+(date.getYear()+1900);
				product.setExpiry(finalExpiryDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		product.setStatus('A');
		long flag = 0;
		System.out.println(product+" "+currentMall+"--------------------------------------->98 productservlet");
		flag = productServe.insert(product,currentMall);
		if(flag>0){
			
			AddProductQuantityService addProductQuantityServe = new AddProductQuantityService();
			LocalDate localDate = LocalDate.now();
			String finalTodayDate=localDate.getDayOfMonth()+"-"+(localDate.getMonthValue())+"-"+(localDate.getYear());
			System.out.println(product+""+addProductQuantityServe+"--------------------------->105 Product Servlet");
			addProductQuantityServe.insertProductQuantity(currentMall,product.getProductNumber(),finalTodayDate,product.getTotalQuantity(),product.getTempCP(),product.getTempSP());

			response.sendRedirect("add_product.jsp?productInsertedSuccessfully=true");
		}
		else if(flag==0){
			response.sendRedirect("add_product.jsp?productInsertedSuccessfully=false");
		}
		else if(flag==-999){
			response.sendRedirect("add_product.jsp?productAlreadyExist=true");
		}
	}
}