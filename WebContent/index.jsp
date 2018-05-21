<%@page import="java.util.List"%>
<%@page import="model.Manager"%>
<%@page import="model.MallBranchGodown"%>
<%@page import="service.ManagerService"%>
<%@page import="service.MallService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mall Manager Assistant</title>
<%@include file="Cpanel/css.jsp" %>
<%@include file="Cpanel/js.jsp" %>
<%@include file="Cpanel/common.jsp" %>
<%@include file="Cpanel/header.jsp" %>

<!-- Favicon -->
<link rel="icon" href="img/favicon.png" sizes="16x16" type="image/png"/>
<!-- Apple devices Homescreen icon -->
<link rel="apple-touch-icon-precomposed" href="" />

<style>
body{
background-color: #8fbdf7;
}

#stat{
background: blue url("img/5.jpg");
}

#saleExcel{
background: blue url("img/1.jpg");
}

#sale{
background: blue url("img/2.jpg");
}

#productExcel{
background: blue url("img/3.jpg");
}

#product{
background: blue url("img/9.jpg");
}

#partner{
background: blue url("img/4.jpg");
}

#addProductQuantity{
background: blue url("img/7.jpg");
}

#addProductQuantityFile{
background: blue url("img/6.jpg");
}

#spoil{
background: blue url("img/8.jpg");
}

#needOpacity{
color:red;
}

#stat,#saleExcel,#productExcel,#product,#partner,#sale,#addProductQuantity,#addProductQuantityFile,#spoil{
height:350px;
border-radius:25px 0px;
width:90%;
font-size:50px;
margin:10px auto;
background-size: 100% 100%;
background-repeat: no-repeat;
}
</style>
</head>
<body>
<div id="container">
<script>
	function DisplayInner(setId,setValue){
		document.getElementById(setId).innerHTML="<span id='needOpacity'><br/><br/><br/><br/><br/><br/><br/>"+setValue+"</span>";
		document.getElementById('needOpacity').setAttribute("style","opacity:1");
	}

	function RemoveDisplayInner(setId){
		document.getElementById(setId).innerHTML="";
	}
	</script>
<a href="statistics.jsp">
     <center><span id="stat" class="btn btn-info btn-lg" onmouseover="DisplayInner('stat','Statistics');" onmouseleave="RemoveDisplayInner('stat');"></span></center>
</a>
<a href="SalesFile">
     <center><span id="saleExcel" class="btn btn-info btn-lg" onmouseover="DisplayInner('saleExcel','Add Today\'s Sales Report');" onmouseleave="RemoveDisplayInner('saleExcel');"></center>
</a>
<a href="sales.jsp">
     <center><span id="sale" class="btn btn-info btn-lg" onmouseover="DisplayInner('sale','Add Single Sale');" onmouseleave="RemoveDisplayInner('sale');"></center>
</a>
<a href="AddProductQuantityFile">
     <center><span id="addProductQuantityFile" class="btn btn-info btn-lg" onmouseover="DisplayInner('addProductQuantityFile','Add Product Quantities');" onmouseleave="RemoveDisplayInner('addProductQuantityFile');"></span></center>
</a>
<a href="add_quantity.jsp">
     <center><span id="addProductQuantity" class="btn btn-info btn-lg" onmouseover="DisplayInner('addProductQuantity','Add Single Product Quantity');" onmouseleave="RemoveDisplayInner('addProductQuantity');"></span></center>
</a>
<a href="ProductFile">
     <center><span id="productExcel" class="btn btn-info btn-lg" onmouseover="DisplayInner('productExcel','Add New Product List');" onmouseleave="RemoveDisplayInner('productExcel');"></span></center>
</a>
<a href="add_product.jsp">
     <center><span id="product" class="btn btn-info btn-lg" onmouseover="DisplayInner('product','Add New Product');" onmouseleave="RemoveDisplayInner('product');"></span></center>
</a>
<a href="signup.jsp">
     <center><span id="partner" class="btn btn-info btn-lg" onmouseover="DisplayInner('partner','Add New Partner');" onmouseleave="RemoveDisplayInner('partner');"></span></center>
</a>
<a href="spoilage.jsp">
     <center><span id="spoil" class="btn btn-info btn-lg" onmouseover="DisplayInner('spoil','Add Spoilage');" onmouseleave="RemoveDisplayInner('spoil');"></span></center>
</a>
</div>
<script src="js/myjquery.js"></script>
</body>
</html>