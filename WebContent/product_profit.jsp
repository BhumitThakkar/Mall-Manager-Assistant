<%@page import="model.Product"%>
<%@page import="service.ProductService"%>
<%@page import="service.ProfitService"%>
<%@page import="model.Profit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Profits</title>
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
					<h3><i class="icon-edit"></i>Find Profits for Product</h3>
				</div>

				<div class="box-content">
					<form id="form" action="" method="post" class='form-horizontal'>

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
												
						<button type="submit" class="btn btn-primary">Find Profits</button>
					</form>
				</div>

				<!--  Table Start   -->
				<%
				String currentProduct = request.getParameter("txtProductNumber");
				if(currentProduct != null){
				ProfitService profitServe = new ProfitService();
				List<Profit> ProfitsOfProduct = profitServe.getProfitsOfProduct(mall,currentProduct);
				%>
				
				<div class="row-fluid">
					<div class="span12" >
						<div class="box box-color box-bordered">
							<div class="box-title">
								<h3>Profits of Product:<%=currentProduct%></h3>
							</div>
							<div class="box-content nopadding" style="overflow:auto;">
								<table id="example" class="table table-hover table-nomargin table-bordered usertable">
									<thead>
										<tr>
											<th>Id</th>
											<th>Product Number</th>
											<th>CP</th>
											<th>SP</th>
											<th>Date</th>
											<th>Sold Quantity</th>
											<th>Spoil Quantity</th>
											<th>Profit</th>
										</tr>
									</thead>
									<tbody>
									<%
										int x=1;
										double profit = 0;
										for (Profit ob : ProfitsOfProduct) {
											profit = ob.getProfit()+profit;
									%>
									<tr>
										<th><%=x++%></th>
										<th><%= ob.getProductNumber() %></th>
										<th><%= ob.getCp() %></th>
										<th><%= ob.getSp() %></th>
										<th><%= ob.getTodayDate() %></th>
										<th><%= ob.getSoldQuantity() %></th>
										<th><%= ob.getSpoilQuantity() %></th>
										<th><%= ob.getProfit() %></th>
									</tr>
									<% } %>
									<tr>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th>Total Profit of Product <%=currentProduct%> :</th>
										<th><%= profit %></th>
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
<script src="js/myjquery.js"></script>
</body>
</html>