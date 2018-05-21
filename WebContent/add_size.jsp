<%@page import="model.Size"%>
<%@page import="service.SizeService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Size</title>
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
List<Size> sizesOfCurrentMall;
SizeService sizeServe;
String sizeInsertedSuccessfulString = request.getParameter("sizeInsertedSuccessful");
String sizeDeletedSuccessfullyString = request.getParameter("sizeDeletedSuccessfully");
String sizeUpdatedSuccessfullyString = request.getParameter("sizeUpdatedSuccessfully");
if("true".equals(sizeInsertedSuccessfulString)){
%>
	<marquee style="color:blue; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Inserted Successfully<<</marquee>
<%
}
else if("false".equals(sizeInsertedSuccessfulString)){
%>
	<marquee style="color:red; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Insertion Failed<<</marquee>
<%
}
if("true".equals(sizeUpdatedSuccessfullyString)){
%>
	<marquee style="color:blue; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Updated Successfully<<</marquee>
<%
}
if("true".equals(sizeDeletedSuccessfullyString)){%>
	<marquee style="color:blue; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Deleted Successfully<<</marquee>	
<%}
Size sizeEditSessionObj = null;
if("true".equals(request.getParameter("editSize"))){
	sizeEditSessionObj = (Size) session.getAttribute("Edit Size");
}
%>
	<div class="row-fluid">
		<div class="span12">
			<div class="box">
				<div class="box-title">
					<%if(sizeEditSessionObj==null){%>
					<h3><i class="icon-edit"></i> Add New Size </h3>
					<%	}else{%>
					<h3><i class="icon-edit"></i> Edit Size </h3>
					<%}%>
				</div>

				<div class="box-content">
					<form id="form" action="SizeServlet" method="Post" class='form-horizontal' style="height:100%;">					
						<div class="control-group">
							<label for="textfield" class="control-label">Size Name:</label>
							<div class="controls">
								<input type="text"  placeholder="Size Name" name="txtSizeName" id="txtSizeName" class="input-xlarge" value="<% if(sizeEditSessionObj!= null)out.print(sizeEditSessionObj.getSize());%>" required/>
								<%if(sizeEditSessionObj==null){%>
								<span class="help-block">Size of Product</span>
								<%}else{%>
								<span class="help-block">Change Product Size</span>
								<%}%>
							</div>
						</div>
						<%if(sizeEditSessionObj==null){%>
							<button type="submit" class="btn btn-primary">Add Size</button>
							<%}
							else{%>
							<script>
							document.getElementById("form").setAttribute("action","SizeChangesServlet");
							</script>
							<button type="submit" class="btn btn-primary"> Apply Changes </button>
						<%}%>
					</form>
				</div>

				<!--  Table Start   -->
				<%
				mall = (MallBranchGodown)session.getAttribute("Current Mall");
				sizeServe = new SizeService();
				sizesOfCurrentMall = sizeServe.getSizesOfCurrentMall(mall);
				%>

				<div class="row-fluid">
					<div class="span12">
						<div class="box box-color box-bordered">
							<div class="box-title">
								<h3>Sizes</h3>
							</div>
							<div class="box-content nopadding">
								<table id="example" class="table table-hover table-nomargin table-bordered usertable">
									<thead>
										<tr>
											<th>Id</th>
											<th>Size Name</th>
											<th class='hidden-350'>Status</th>
											<th class='hidden-480'>Options</th>
										</tr>
									</thead>
									<tbody>
									<%
										int x=1;
										for (Size ob : sizesOfCurrentMall) {
									%>
									<tr>
										<th><%=x++%></th>
										<th><%= ob.getSize() %></th>
										<th class='hidden-350'><span class="label label-satgreen">Active</span></th>
										<th class='hidden-480'>
										<center>
											<a href="SizeChangesServlet?id=<%= ob.getId()%>&flag=edit" class="btn" rel="tooltip" title="Edit"><i class="icon-edit"></i></a>
											<a href="SizeChangesServlet?id=<%= ob.getId()%>&flag=delete" class="btn" rel="tooltip" title="Delete"><i class="icon-remove"></i></a>
										</center>
										</th>
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