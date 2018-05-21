<%@page import="model.Product"%>
<%@page import="service.ProductService"%>
<%@page import="service.SpoilService"%>
<%@page import="model.Spoil"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spoilage</title>
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
String unknownProductSpoilErrorString = request.getParameter("unknownProductSpoilError");
if("true".equals(unknownProductSpoilErrorString)){
%>
	<marquee style="color:red; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Unknown Product Can't Be Spoil<<</marquee>
<%
}
String productLessThanSpoiledString = request.getParameter("productLessThanSpoiled");
if("true".equals(productLessThanSpoiledString)){
%>
	<marquee style="color:red; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Product Quantity Less Than Spoiled, Which Is Not Possible, So Spoilage Can't Be Accepted<<</marquee>
<%
}
String spoilageInsertedSuccessfullyString = request.getParameter("spoilageInsertedSuccessfully");
if("true".equals(spoilageInsertedSuccessfullyString)){
%>
	<marquee style="color:blue; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Inserted Successfully<<</marquee>
<%
}
else if("false".equals(spoilageInsertedSuccessfullyString)){
%>
	<marquee style="color:red; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Insertion Failed<<</marquee>
<%}%>
	<div class="row-fluid">
		<div class="span12">
			<div class="box">
				<div class="box-title">
					<h3><i class="icon-edit"></i> Add New Spoilage </h3>
				</div>

				<div class="box-content">
					<form id="form" action="SpoilServlet" method="Post" class='form-horizontal'>

					<div class="control-group">
						<label for="textfield" class="control-label">Product Number:</label>

						<div class="dropdown controls">
						<button class="btn btn-primary dropdown-toggle" style="width:284px;" type="button" data-toggle="dropdown">Select Product Number
							<span class="caret"></span></button>
						<ul class="dropdown-menu" style="width:282px; max-height: 15em; overflow: auto;">
							<%
							ProductService productServe = new ProductService();
							mall = (MallBranchGodown)session.getAttribute("Current Mall");
							List<Product> tblList = productServe.getProductsOfCurrentMall(mall);
							for (Product ob : tblList) {
							%>
							<li><a onclick="setValueTo('<%=ob.getProductNumber()%>','txtProductNumber');"><span><%=ob.getProductNumber()%></span></a></li>
							<% } %>
						</ul>
						</div>
						
						<div class="control-group">
							<div class="controls">
								<input type="text"  placeholder="Product Code" name="txtProductNumber" id="txtProductNumber" class="input-xlarge" readonly="readonly" required/>
								<span class="help-block">Code of Product for Profit</span>
							</div>
						</div>
						
					</div>

					<div class="control-group">
						<label for="textfield" class="control-label">Spoilage Date:</label>
						<div class="controls">
						<%
						LocalDate localDate = LocalDate.now();
				        %>
							<input type="date" name="txtSpoilDate" id="txtSpoilDate" class="input-xlarge" max="<%=DateTimeFormatter.ofPattern("yyyy").format(localDate)%>-<%=DateTimeFormatter.ofPattern("MM").format(localDate)%>-<%=DateTimeFormatter.ofPattern("dd").format(localDate)%>" required/>
							<span class="help-block">Spoilage Date of Product</span>
						</div>
					</div>

					<div class="control-group">
						<label for="textfield" class="control-label">Spoilage Quantity:</label>
						<div class="controls">
							<input type="text"  placeholder="Spoilage Quantity" name="txtSpoilQuantity" id="txtSpoilQuantity" class="input-xlarge" required/>
							<span class="help-block">Quantity of Product Spoiled</span>
						</div>
					</div>

					<div class="control-group">
						<label for="textfield" class="control-label">Spoilage Reason:</label>
						<div class="controls">
							<textarea rows="5" cols="15" placeholder="Spoilage Reason" name="txtSpoilReason" id="txtSpoilReason" class="input-xlarge" required></textarea>
							<span class="help-block">Reason for Product Spoiled</span>
						</div>
					</div>
					
						<button type="submit" class="btn btn-primary">Add Spoilage</button>
					</form>
				</div>

				<!--  Table Start   -->
				<%
				List<Spoil> spoilsOfCurrentMall;
				SpoilService spoilServe;
				mall = (MallBranchGodown)session.getAttribute("Current Mall");
				spoilServe = new SpoilService();
				spoilsOfCurrentMall = spoilServe.getSpoilsOfCurrentMall(mall);
				%>
				
				<div class="row-fluid">
					<div class="span12" >
						<div class="box box-color box-bordered">
							<div class="box-title">
								<h3>Spoils</h3>
							</div>
							<div class="box-content nopadding" style="overflow:auto;">
								<table id="example" class="table table-hover table-nomargin table-bordered usertable">
									<thead>
										<tr>
											<th>Id</th>
											<th>Product Number</th>
											<th>Sale Date</th>
											<th>Spoil Quantity</th>
											<th>Spoil Reason</th>
										</tr>
									</thead>
								<tbody>
								<%
								int x=1;
								for (Spoil ob : spoilsOfCurrentMall) {
								%>
								<tr>
									<td><%= x++ %></td>
									<td><%= ob.getProductNumber() %></td>
									<td><%= ob.getSpoilDate() %></td>
									<td><%= ob.getSpoilQuantity() %></td>
									<td><%= ob.getSpoilReason() %></td>
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