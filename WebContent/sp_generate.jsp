<%@page import="model.Product"%>
<%@page import="service.ProductService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SP Generate</title>
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
					<h3><i class="icon-edit"></i>Generate SP for Product</h3>
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
								<span class="help-block">Code of Product for SP Generation</span>
							</div>
						</div>
						
						<div class="control-group">
						<label for="textfield" class="control-label">Percentage Profit Required:</label>
							<div class="controls">
								<input type="number" max="250" placeholder="Profit % required" name="txtProfitPercent" id="txtProfitPercent" class="input-xlarge" required/>
								<span class="help-block">Write only value, do not write '%' in end</span>
							</div>
						</div>
					</div>
					<button type="submit" class="btn btn-primary">Find SP</button>
					<br/>
				</form>
				
				<%
				if(request.getParameter("txtProductNumber")!=null && request.getParameter("txtProfitPercent")!=null){
					String productNumber = request.getParameter("txtProductNumber");
					Integer profitPercentage = Integer.parseInt(request.getParameter("txtProfitPercent"));
					ProductService ps = new ProductService();
	/* 				MallBranchGodown currentMall = (MallBranchGodown) request.getSession().getAttribute("Current Mall");   */
	 				Product product = ps.getProductByMallAndProductNumber(mall.getId(), productNumber);
					Double cp = product.getTempCP();
					Double sp = cp * (1 + (profitPercentage / 100.0));
					%>
					<div class="statlinks">
							Generated SP according to <%=profitPercentage%>% on cp=<%=cp%> is <%=sp%>
					</div>					
				<%}%>
			
			</div>
		</div>
	</div>
</div>
<script src="js/myjquery.js"></script>
</body>
</html>