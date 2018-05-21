<%@page import="service.StatisticService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Statistics</title>
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
<br/><br/><br/>
<div class="statlinks">
<a href="profit.jsp">>>Profit-Loss till Date</a>
</div>
<br/>
<div class="statlinks">
<a href="product_profit.jsp">>>Profit-Loss for Specific Product</a>
</div>
<br/>
<div class="statlinks" >
<a href="date_profit.jsp">>>Profit-Loss by Date</a>
</div>
<br/>
<div class="statlinks" >
<a href="compare_products.jsp">>>Compare Products</a>
</div>
<br/>
<div class="statlinks" >
<a href="sp_generate.jsp">>>Auto Generate SP Algorithms</a>
</div>
<br/>
<!-- <center><img src="img/wip.png"></center> -->

<script src="js/myjquery.js"></script>
</body>
</html>