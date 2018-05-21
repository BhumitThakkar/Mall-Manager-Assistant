package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AddProductQuantity;
import model.Product;
import service.AddProductQuantityService;
import service.ProductService;

@WebServlet("/ProductChangesServlet")
public class ProductChangesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService productServe = new ProductService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String flagString = request.getParameter("flag");
		Product product = productServe.getProductById(id);
		if(flagString.equals("edit")){
			request.getSession().setAttribute("Edit Product",product);
			response.sendRedirect("add_product.jsp?editProduct=true");
		}
		if(flagString.equals("delete")){
			productServe.deleteProduct(product);
			response.sendRedirect("add_product.jsp?productDeletedSuccessfully=true");
	}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product product = new Product();
		Product productEditSessionObj = (Product) request.getSession().getAttribute("Edit Product");
		product.setId(productEditSessionObj.getId());
		product.setMallBranchGodown(productEditSessionObj.getMallBranchGodown());
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
			response.sendRedirect("add_product.jsp?productCpMoreThanSpFromUpdate=true");
		}
		else{
			product.setTempCP(tempCP);
			product.setTempSP(tempSP);
			if(productEditSessionObj.getTempCP() != product.getTempCP() || productEditSessionObj.getTempSP() != product.getTempSP()){
				AddProductQuantityService addProductQuantityServe = new AddProductQuantityService();
				List<AddProductQuantity> quantities = addProductQuantityServe.getAddedProductQuantitiesOfGivenProductNumber_Quantity_CP_SP(productEditSessionObj.getMallBranchGodown(),productEditSessionObj.getTotalQuantity(),productEditSessionObj.getProductNumber(),productEditSessionObj.getTempCP(),productEditSessionObj.getTempSP());
				for(AddProductQuantity quantity :quantities)
				{
					quantity.setCp(tempCP);
					quantity.setSp(tempSP);
					quantity.setProductNumber(product.getProductNumber());
					addProductQuantityServe.update(quantity);
				}
			}
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
				String finalExpiryDate=date.getDate()+"-"+date.getMonth()+"-"+(date.getYear()+1900);
				product.setExpiry(finalExpiryDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		product.setStatus(productEditSessionObj.getStatus());
		
		long flag = productServe.update(product,product.getMallBranchGodown(),productEditSessionObj.getProductNumber());
		if(flag==-999){
			response.sendRedirect("add_product.jsp?productAlreadyExist=true");
		}
		else{
			request.removeAttribute("Edit Product");
			response.sendRedirect("add_product.jsp?productUpdatedSuccessfully=true");
		}
	}

}