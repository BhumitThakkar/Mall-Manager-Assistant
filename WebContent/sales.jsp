<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="service.SaleService"%>
<%@page import="controller.SalesFile"%>
<%@page import="model.Sale"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sales</title>
<%@include file="Cpanel/css.jsp" %>
<%@include file="Cpanel/js.jsp" %>
<%@include file="Cpanel/common.jsp" %>
<%@include file="Cpanel/header.jsp" %>

<!-- Favicon -->
<link rel="icon" href="img/favicon.png" sizes="16x16" type="image/png"/>
<!-- Apple devices Homescreen icon -->
<link rel="apple-touch-icon-precomposed" href="" />
</head>
<body>
<%
String unknownProductSoldErrorString = request.getParameter("unknownProductSoldError");
if("true".equals(unknownProductSoldErrorString)){
%>
	<marquee style="color:red; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Unknown Product Can't Be Sold<<</marquee>
<%
}
String productLessThanSoldString = request.getParameter("productLessThanSold");
if("true".equals(productLessThanSoldString)){
%>
	<marquee style="color:red; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Product Quantity Less Than Sold, Which Is Not Possible, So Sales Can't Be Accepted<<</marquee>
<%
}
String saleInsertedSuccessfullyString = request.getParameter("saleInsertedSuccessfully");
if("true".equals(saleInsertedSuccessfullyString)){
%>
	<marquee style="color:blue; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Inserted Successfully<<</marquee>
<%
}
else if("false".equals(saleInsertedSuccessfullyString)){
%>
	<marquee style="color:red; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Insertion Failed<<</marquee>
<%
}
if("true".equals(request.getParameter("ExcelSaleInsertionError"))){
SalesFile saleFileObj = (SalesFile)request.getSession().getAttribute("ExcelFileSaleObjSession");
if(saleFileObj != null){
	List<String> ErrorCells = saleFileObj.getNonInsertedSalesRowsCols();
	%>
	<div>
	<table>
	<tr>	<th>Error Cells In Sales Excel File:</th></tr>
	<%
	for(String ErrorCell : ErrorCells){
	%>
	<tr>	<th>~<%=ErrorCell%>~</th></tr>
	<%}%>
	</table>
	</div>
	<%}
	session.removeAttribute("ExcelFileSaleObjSession");
}
%>
	<div class="row-fluid">
		<div class="span12">
			<div class="box">
				<div class="box-title">
					<h3><i class="icon-edit"></i> Add New Sale </h3>
				</div>

				<div class="box-content">
					<form id="form" action="SaleServlet" method="Post" class='form-horizontal'>

						<div class="control-group">
							<label for="textfield" class="control-label">Product Number:</label>
							<div class="controls">
								<input type="text"  placeholder="Product Code" name="txtProductNumber" id="txtProductNumber" class="input-xlarge" required/>
								<span class="help-block">Code of Product that Sold</span>
							</div>
						</div>

					<div class="control-group">
						<label for="textfield" class="control-label">Sale Date:</label>
						<div class="controls">
						<%
						LocalDate localDate = LocalDate.now();
				        %>
							<input type="date" name="txtSaleDate" id="txtSaleDate" class="input-xlarge" max="<%=DateTimeFormatter.ofPattern("yyyy").format(localDate)%>-<%=DateTimeFormatter.ofPattern("MM").format(localDate)%>-<%=DateTimeFormatter.ofPattern("dd").format(localDate)%>" required/>
							<span class="help-block">Sale Date for Product</span>
						</div>
					</div>

					<div class="control-group">
						<label for="textfield" class="control-label">Sale Quantity:</label>
						<div class="controls">
							<input type="text"  placeholder="Sale Quantity" name="txtSaleQuantity" id="txtSaleQuantity" class="input-xlarge" required/>
							<span class="help-block">Quantity of Product Sold</span>
						</div>
					</div>

					<div class="control-group">
						<label for="textfield" class="control-label">Product Sale Price:</label>
						<div class="controls">
							<input type="text"  placeholder="Product Rate" name="txtProductSalePrice" id="txtProductSalePrice" class="input-xlarge" required/>
							<span class="help-block">Enter SP of the product Sold</span>
						</div>
					</div>

						<button type="submit" class="btn btn-primary">Add Sale</button>
					</form>
				</div>

				<!--  Table Start   -->
				<%
				List<Sale> salesOfCurrentMall;
				SaleService saleServe;
				mall = (MallBranchGodown)session.getAttribute("Current Mall");
				saleServe = new SaleService();
				salesOfCurrentMall = saleServe.getSalesOfCurrentMall(mall);
				%>
				
				<div class="row-fluid">
					<div class="span12" >
						<div class="box box-color box-bordered">
							<div class="box-title">
								<h3>Sales</h3>
							</div>
							<div class="box-content nopadding" style="overflow:auto;">
								<table id="example" class="table table-hover table-nomargin table-bordered usertable">
									<thead>
										<tr>
											<th>Id</th>
											<th>Product Number</th>
											<th>Sale Date</th>
											<th>Sale Quantity</th>
											<th>SP</th>
										</tr>
									</thead>
								<tbody>
								<%
								int x=1;
								for (Sale ob : salesOfCurrentMall) {
								%>
								<tr>
									<td><%= x++ %></td>
									<td><%= ob.getProductNumber() %></td>
									<td><%= ob.getSaleDate() %></td>
									<td><%= ob.getSaleQuantity() %></td>
									<td><%= ob.getSp() %></td>
								</tr>
									<% } %>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<!-- table end  -->

		</div>
	</div>
</div>
<script src="js/myjquery.js"></script>
</body>
</html>