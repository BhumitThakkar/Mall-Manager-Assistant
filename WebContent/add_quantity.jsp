<%@page import="controller.AddProductQuantityFile"%>
<%@page import="service.AddProductQuantityService"%>
<%@page import="model.AddProductQuantity"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Quantity</title>
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
String unknownProductString = request.getParameter("unknownProduct");
String productCpMoreThanSpString = request.getParameter("productCpMoreThanSp");

if("true".equals(unknownProductString)){
%>
	<marquee style="color:red; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Unknown Product Can't add Quantity, first Add Product Details--><a href="add_product.jsp">Add New Product</a><<</marquee>
<%
}
if("true".equals(productCpMoreThanSpString)){%>
<marquee style="color:red; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Product CP can't be more than SP, Failed to insert<<</marquee>
<%
}
String productQuantityInsertedSuccessfullyString = request.getParameter("productQuantityInsertedSuccessfully");
if("true".equals(productQuantityInsertedSuccessfullyString)){
%>
	<marquee style="color:blue; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Inserted Successfully<<</marquee>
<%
}
else if("false".equals(productQuantityInsertedSuccessfullyString)){
%>
	<marquee style="color:red; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Insertion Failed<<</marquee>
<%
}
else if("true".equals(request.getParameter("ExcelQuantityInsertionError"))){
	AddProductQuantityFile addProductQuantityFileObj = (AddProductQuantityFile)request.getSession().getAttribute("ExcelFileAddProductQuantityObjSession");
if(addProductQuantityFileObj!=null){
	List<String> ErrorCells = addProductQuantityFileObj.getNonInsertedProductQuantityRowsCols();
	%>
	<div>
	<table>
	<tr><th>Error Cells In Sales Excel File:</th></tr>
	<%
	for(String ErrorCell : ErrorCells){
	%>
	<tr><th>~<%=ErrorCell%>~</th></tr>
	<%}%>
	</table>
	</div>
	<%
	session.removeAttribute("ExcelFileAddProductQuantityObjSession");
	}
}	%>
	<div class="row-fluid">
		<div class="span12">
			<div class="box">
				<div class="box-title">
					<h3><i class="icon-edit"></i> Add Product Quantity </h3>
				</div>

				<div class="box-content">
					<form id="form" action="AddProductQuantityServlet" method="Post" class='form-horizontal'>

						<div class="control-group">
							<label for="textfield" class="control-label">Product Number:</label>
							<div class="controls">
								<input type="text"  placeholder="Product Code" name="txtProductNumber" id="txtProductNumber" class="input-xlarge" required/>
								<span class="help-block">Code of Product</span>
							</div>
						</div>

					<div class="control-group">
						<label for="textfield" class="control-label">Product Entry Date:</label>
						<div class="controls">
						<%
						LocalDate localDate = LocalDate.now();
				        %>
							<input type="date" name="txtProductEntryDate" id="txtProductEntryDate" class="input-xlarge" max="<%=DateTimeFormatter.ofPattern("yyyy").format(localDate)%>-<%=DateTimeFormatter.ofPattern("MM").format(localDate)%>-<%=DateTimeFormatter.ofPattern("dd").format(localDate)%>" required/>
							<span class="help-block">Entry Date for Product in Mall</span>
						</div>
					</div>

					<div class="control-group">
						<label for="textfield" class="control-label">Product Quantity:</label>
						<div class="controls">
							<input type="text"  placeholder="Product Quantity" name="txtProductQuantity" id="txtProductQuantity" class="input-xlarge" required/>
							<span class="help-block">Quantity of Product to be Added</span>
						</div>
					</div>

					<div class="control-group">
						<label for="textfield" class="control-label">Product Cost Price:</label>
						<div class="controls">
							<input type="text"  placeholder="Product Rate" name="txtProductCostPrice" id="txtProductCostPrice" class="input-xlarge" required/>
							<span class="help-block">Enter CP of the product</span>
						</div>
					</div>
					
					<div class="control-group">
						<label for="textfield" class="control-label">Product Marked Up Sale Price:</label>
						<div class="controls">
							<input type="text"  placeholder="Product Marked Up Sale Rate" name="txtProductSalePrice" id="txtProductSalePrice" class="input-xlarge" required/>
							<span class="help-block">Enter Marked Up SP of the product</span>
						</div>
					</div>

						<button type="submit" class="btn btn-primary">Add Product Quantity</button>
					</form>
				</div>

				<!--  Table Start   -->
				<%
				List<AddProductQuantity> addProductQuantitiesOfCurrentMall;
				AddProductQuantityService addProductQuantityServe;
				mall = (MallBranchGodown)session.getAttribute("Current Mall");
				addProductQuantityServe = new AddProductQuantityService();
				addProductQuantitiesOfCurrentMall = addProductQuantityServe.getAddedProductQuantitiesOfCurrentMall(mall);
				%>
				
				<div class="row-fluid">
					<div class="span12" >
						<div class="box box-color box-bordered">
							<div class="box-title">
								<h3>Added Product Quantities</h3>
							</div>
							<div class="box-content nopadding" style="overflow:auto;">
								<table id="example" class="table table-hover table-nomargin table-bordered usertable">
									<thead>
										<tr>
											<th>Id</th>
											<th>Product Number</th>
											<th>Added Product Quantity</th>
											<th>CP</th>
											<th>Added Product Quantity Date</th>
											<th>SP</th>
											<th>Sold</th>
											<th>Spoil</th>
										</tr>
									</thead>
								<tbody>
								<%
								int x=1;
								for (AddProductQuantity ob : addProductQuantitiesOfCurrentMall) {
								%>
								<tr>
									<td><%= x++ %></td>
									<td><%= ob.getProductNumber() %></td>
									<td><%= ob.getAddProductQuantity() %></td>
									<td><%= ob.getCp() %></td>
									<td><%= ob.getProductEntryDate()%></td>
									<td><%= ob.getSp() %></td>
									<td><%= ob.getSold() %></td>
									<td><%= ob.getSpoil() %></td>
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