<%@page import="service.ProfitService"%>
<%@page import="model.Profit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profit By Date</title>

<%@include file="Cpanel/css.jsp" %>
<%@include file="Cpanel/js.jsp" %>
<%@include file="Cpanel/common.jsp" %>
<%@include file="Cpanel/header.jsp" %>
<!-- Favicon -->
<link rel="icon" href="img/favicon.png" sizes="16x16" type="image/png"/>
<!-- Apple devices Homescreen icon -->
<link rel="apple-touch-icon-precomposed" href="" />

<!-- Include Required Prerequisites -->
<!-- <script type="text/javascript" src="//cdn.jsdelivr.net/jquery/1/jquery.min.js"></script>
<script type="text/javascript" src="//cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script> -->			<!-- not needed bcz already exists-->
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/bootstrap/3/css/bootstrap.css" />

<!-- Include Date Range Picker -->
<script type="text/javascript" src="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css" />
</head>
<body>
	<div class="row-fluid">
		<div class="span12">
			<div class="box">
				<div class="box-title">
					<h3><i class="icon-edit"></i>Find Profits between Dates</h3>
				</div>

				<div class="box-content">
					<form id="form" action="" method="post" onsubmit="setSpecialCaseValueFromTo('spanDateFrom','txtDateFrom','spanDateTo','txtDateTo');" class='form-horizontal'>

						<div id="reportrange" style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc; width: 20%">
						    <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>&nbsp;
						    <span id="spanDateFrom" ></span> to
						    <span id="spanDateTo"></span>
						    <input type="hidden" name="txtDateFrom" id="txtDateFrom">
						    <input type="hidden" name="txtDateTo" id="txtDateTo">
						    <b class="caret"></b>
						</div>
						<br/>
						<script type="text/javascript">
						$(function() {
						
						    var start = moment().subtract(29, 'days');
						    var end = moment();
						
						    function cb(start, end) {
/* 						        $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY')); */
						        $('#reportrange #spanDateFrom').html(start.format('DD-MM-YYYY'));
						        $('#reportrange #spanDateTo').html(end.format('DD-MM-YYYY'));
						    }
						
						    $('#reportrange').daterangepicker({
						        startDate: start,
						        endDate: end,
						        ranges: {
						           'Today': [moment(), moment()],
						           'Yesterday': [moment().subtract(1, 'days'), moment()],
						           'Last 7 Days': [moment().subtract(6, 'days'), moment()],
						           'Last 30 Days': [moment().subtract(29, 'days'), moment()],
						           'This Month': [moment().startOf('month'), moment().endOf('month')],
						           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
						        }
						    }, cb);
						
						    cb(start, end);
						    
						});
						</script>
						<button type="submit" class="btn btn-primary">Find Profits</button>
					</form>
				</div>

				<%
				mall = (MallBranchGodown)session.getAttribute("Current Mall");
				String fromDate = request.getParameter("txtDateFrom");
				String toDate = request.getParameter("txtDateTo");
				%>
 				<!--  Table Start   -->
				<%
				if(fromDate != null && toDate != null)
				{
					ProfitService profitServe = new ProfitService();
					List<Profit> ProfitsByDate = profitServe.getProfitsByDate(mall,fromDate,toDate);
				%>
				
				<div class="row-fluid">
					<div class="span12">
						<div class="box box-color box-bordered">
							<div class="box-title">
								<h3>Profits by Date:<%=fromDate%> to <%=toDate%></h3>
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
										for (Profit ob : ProfitsByDate) {
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
									<th>Total Profit from Date <%=fromDate%> to <%=toDate%></th>
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
