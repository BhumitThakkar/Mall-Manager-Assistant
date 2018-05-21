<%@page import="java.util.ArrayList"%>
<%@page import="model.Product"%>
<%@page import="service.ProductService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Compare Products</title>
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
	<div class="row-fluid">
		<div class="span12">
			<div class="box">
				<div class="box-title">
					<h3><i class="icon-edit"></i> Compare Products</h3>
				</div>

				<div class="box-content">
					<form id="form" action="" method="Post" class='form-horizontal'>

					<div class="control-group">
						<label for="textfield" class="control-label">Product Number 1:</label>

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
							<li><a onclick="setValueTo('<%=ob.getProductNumber()%>','txtProductNumber1');"><span><%=ob.getProductNumber()%></span></a></li>
							<% } %>
						</ul>
						</div>
						
						<div class="control-group">
							<div class="controls">
								<input type="text"  placeholder="Product Code" name="txtProductNumber1" id="txtProductNumber1" class="input-xlarge" readonly="readonly" required/>
								<span class="help-block">Code of Product to Compare</span>
							</div>
						</div>
					</div>
					
					<br/>
					
					<div class="control-group">
						<label for="textfield" class="control-label">Product Number 2:</label>

						<div class="dropdown controls">
						<button class="btn btn-primary dropdown-toggle" style="width:284px;" type="button" data-toggle="dropdown">Select Product Number
							<span class="caret"></span></button>
						<ul class="dropdown-menu" style="width:282px; max-height: 15em; overflow: auto;">
							<%
							for (Product ob : tblList) {
							%>
							<li><a onclick="setValueTo('<%=ob.getProductNumber()%>','txtProductNumber2');"><span><%=ob.getProductNumber()%></span></a></li>
							<% } %>
						</ul>
						</div>
						
						<div class="control-group">
							<div class="controls">
								<input type="text"  placeholder="Product Code" name="txtProductNumber2" id="txtProductNumber2" class="input-xlarge" readonly="readonly" required/>
								<span class="help-block">Code of Product to Compare</span>
							</div>
						</div>
					</div>
					
						<div class="control-group">
							<div class="controls">
							<button type="submit" class="btn btn-primary">Compare</button>
							</div>
						</div>
					</form>
					
				<!--  Table Start   -->
				<%
				String txtProductNumber1 = request.getParameter("txtProductNumber1");
				String txtProductNumber2 = request.getParameter("txtProductNumber2");
				productServe = new ProductService();
				List<Product> productsForCompare = new ArrayList();
				mall = (MallBranchGodown)session.getAttribute("Current Mall");
				if(txtProductNumber1 != null && txtProductNumber2 != null){
				productsForCompare.add(productServe.getProductByMallAndProductNumber(mall.getId(), txtProductNumber1));
				productsForCompare.add(productServe.getProductByMallAndProductNumber(mall.getId(), txtProductNumber2));
				%>
				
				<div class="row-fluid">
					<div class="span12" >
						<div class="box box-color box-bordered">
							<div class="box-title">
								<h3>Comparison of Selected Products</h3>
							</div>
							<div class="box-content nopadding" style="overflow:auto;">
								<table id="example" class="table table-hover table-nomargin table-bordered usertable">
									<thead>
										<tr>
											<th>No</th>
											<th>Product Number</th>
											<% for(Product p : productsForCompare) {%>
											<td><%= p.getProductNumber() %></td>
											<%}%>
										</tr>
									</thead>
									<tbody>

										<tr>
											<th>1</th>
											<th>Product Company</th>
											<% for(Product p : productsForCompare) {%>
											<td><%= p.getCompany() %></td>
											<%}%>
										</tr>

										<tr>
											<th>2</th>
											<th>Product Name</th>
											<% for(Product p : productsForCompare) {%>
											<td><%= p.getProductName() %></td>
											<%}%>
										</tr>
										
										<tr>
											<th>3</th>
											<th>Expiry</th>
											<% for(Product p : productsForCompare) {%>
											<td><%= p.getExpiry()%></td>
											<%}%>
										</tr>

										<tr>
											<th>4</th>
											<th>Color</th>
											<% for(Product p : productsForCompare) {%>
											<td><%= p.getColor()%></td>
											<%}%>
										</tr>
										
										<tr>
											<th>5</th>
											<th>Category</th>
											<% for(Product p : productsForCompare) {%>
											<td><%= p.getCategory() %></td>
											<%}%>
										</tr>
										
										<tr>
											<th>6</th>
											<th>Size</th>
											<% for(Product p : productsForCompare) {%>
											<td><%= p.getSize() %></td>
											<%}%>
										</tr>

										<tr>
											<th>7</th>
											<th>Unit</th>
											<% for(Product p : productsForCompare) {%>
											<td><%= p.getUnit() %></td>
											<%}%>
										</tr>

										<tr>
											<th>8</th>
											<th>MP</th>
											<% for(Product p : productsForCompare) {%>
											<td><%= p.getMp() %></td>
											<%}%>
										</tr>

										<tr>
											<th>9</th>
											<th>Total Quantity</th>
											<% for(Product p : productsForCompare) {%>
											<td><%= p.getTotalQuantity() %></td>
											<%}%>
										</tr>

										<tr>
											<th>10</th>
											<th>Threshold Quantity</th>
											<% for(Product p : productsForCompare) {%>
											<td><%= p.getThresholdQuantity() %></td>
											<%}%>
										</tr>

										<tr>
											<th>11</th>
											<th>Threshold Sale Per Day</th>
											<% for(Product p : productsForCompare) {%>
											<td><%= p.getThresholdSalePerDay() %></td>
											<%}%>
										</tr>

										<tr>
											<th>12</th>
											<th>Threshold Sale Per Week</th>
											<% for(Product p : productsForCompare) {%>
											<td><%= p.getThresholdSalePerDay() %></td>
											<%}%>
										</tr>

										<tr>
											<th>13</th>
											<th>Threshold Sale Per Month</th>
											<% for(Product p : productsForCompare) {%>
											<td><%= p.getThresholdSalePerMonth() %></td>
											<%}%>
										</tr>

										<tr>
											<th>14</th>
											<th>Threshold Sale Per 3 months</th>
											<% for(Product p : productsForCompare) {%>
											<td><%= p.getThresholdSalePerMiniSem() %></td>
											<%}%>
										</tr>

										<tr>
											<th>15</th>
											<th>Threshold Sale Per Sem</th>
											<% for(Product p : productsForCompare) {%>
											<td><%= p.getThresholdSalePerSem() %></td>
											<%}%>
										</tr>

										<tr>
											<th>16</th>
											<th>Threshold Sale Per Year</th>
											<% for(Product p : productsForCompare) {%>
											<td><%= p.getThresholdSalePerYear() %></td>
											<%}%>
										</tr>

										<tr>
											<th>17</th>
											<th>Profit</th>
											<% for(Product p : productsForCompare) {%>
											<td><%= p.getProfit() %></td>
											<%}%>
										</tr>

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<!-- table end  -->
			<%}%>
				</div>
			</div>
		</div>
	</div>
	<script src="js/myjquery.js"></script>
</body>
</html>