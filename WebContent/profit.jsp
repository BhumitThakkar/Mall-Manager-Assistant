<%@page import="model.Profit"%>
<%@page import="service.ProfitService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profit</title>
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
				
				<!--  Table Start   -->
				<%
				mall = (MallBranchGodown)session.getAttribute("Current Mall");
				ProfitService profitServe = new ProfitService();
				List<Profit> profitsOfCurrentMall = profitServe.getProfitsOfCurrentMall(mall);
				%>

				<div class="row-fluid">
					<div class="span12">
						<div class="box box-color box-bordered">
							<div class="box-title">
								<h3>Profits</h3>
							</div>
							<div class="box-content nopadding">
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
										for (Profit ob : profitsOfCurrentMall) {
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
										<th>Total Profit:</th>
										<th><%= profit %></th>
									</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- table end  -->
<script src="js/myjquery.js"></script>
</body>
</html>