<%@page import="java.util.List"%>
<%@page import="service.CategoryService"%>
<%@page import="model.Category"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Category</title>
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
List<Category> categoriesOfCurrentMall;
CategoryService categoryServe;
String categoryDeletedSuccessfullyString = request.getParameter("categoryDeletedSuccessfully");
String categoryInsertedSuccessfullyString = request.getParameter("categoryInsertedSuccessfully");
String categoryUpdatedSuccessfullyString = request.getParameter("categoryUpdatedSuccessfully");
if("true".equals(categoryInsertedSuccessfullyString)){
%>
	<marquee style="color:blue; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Inserted Successfully<<</marquee>
<%
}
else if("false".equals(categoryInsertedSuccessfullyString)){
%>
	<marquee style="color:red; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Insertion Failed<<</marquee>
<%
}
if("true".equals(categoryUpdatedSuccessfullyString)){
%>
	<marquee style="color:blue; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Updated Successfully<<</marquee>
<%
}
if("true".equals(categoryDeletedSuccessfullyString)){%>
	<marquee style="color:blue; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Deleted Successfully<<</marquee>	
<%}
Category categoryEditSessionObj = null;
if("true".equals(request.getParameter("editCategory"))){
	categoryEditSessionObj = (Category) session.getAttribute("Edit Category");
}
%>
	<div class="row-fluid">
		<div class="span12">
			<div class="box">
				<div class="box-title">
					<%if(categoryEditSessionObj==null){%>
					<h3><i class="icon-edit"></i> Add New Category </h3>
					<%	}else{%>
					<h3><i class="icon-edit"></i> Edit Category </h3>
					<%}%>
				</div>
				
				<div class="box-content">
					<form id="form" action="CategoryServlet" method="Post" class='form-horizontal'>					
						<div class="control-group">
							<label for="textfield" class="control-label">Category Name:</label>
							<div class="controls">
								<input type="text"  placeholder="Category Name" name="txtCategoryName" id="txtCategoryName" class="input-xlarge" value="<% if(categoryEditSessionObj!= null)out.print(categoryEditSessionObj.getCategory());%>" required/>
								<%if(categoryEditSessionObj==null){%>
								<span class="help-block">Category of Product</span>
								<%}else{%>
								<span class="help-block">Change Name Of Product Category</span>
								<%}%>
							</div>
						</div>
						<%if(categoryEditSessionObj==null){%>
							<button type="submit" class="btn btn-primary">Add Category </button>
							<%}
							else{%>
							<script>
							document.getElementById("form").setAttribute("action","CategoryChangesServlet");
							</script>
							<button type="submit" class="btn btn-primary"> Apply Changes </button>
						<%}%>
					</form>
				</div>

				<!--  Table Start   -->				
				<%
				mall = (MallBranchGodown)session.getAttribute("Current Mall");
				categoryServe = new CategoryService();
				categoriesOfCurrentMall = categoryServe.getCategoriesOfCurrentMall(mall);
				%>

				<div class="row-fluid">
					<div class="span12">
						<div class="box box-color box-bordered">
							<div class="box-title">
								<h3>Categories</h3>
							</div>
							<div class="box-content nopadding">
								<table id="example" class="table table-hover table-nomargin table-bordered usertable">
									<thead>
										<tr>
											<th>Id</th>
											<th>Category Name</th>
											<th class='hidden-350'>Status</th>
											<th class='hidden-480'>Options</th>
										</tr>
									</thead>
									<tbody>		
									<%
									int x=1;
									for (Category ob : categoriesOfCurrentMall) {
										%>
									<tr>
										<td><%= x++ %></td>
										<td><%= ob.getCategory() %></td>
										<td class='hidden-350'><span class="label label-satgreen">Active</span></td>
										<td class='hidden-480'>
											<center>
											<a href="CategoryChangesServlet?id=<%= ob.getId()%>&flag=edit" class="btn" rel="tooltip" title="Edit"><i class="icon-edit"></i></a>
											<a href="CategoryChangesServlet?id=<%= ob.getId()%>&flag=delete" class="btn" rel="tooltip" title="Delete"><i class="icon-remove"></i></a>
											</center>
										</td>
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