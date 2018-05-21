<%@page import="service.UnitService"%>
<%@page import="model.Unit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Unit</title>
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
List<Unit> unitsOfCurrentMall;
UnitService unitServe;
String unitInsertedSuccessfulString = request.getParameter("unitInsertedSuccessful");
String unitDeletedSuccessfullyString = request.getParameter("unitDeletedSuccessfully");
String unitUpdatedSuccessfullyString = request.getParameter("unitUpdatedSuccessfully");
if("true".equals(unitInsertedSuccessfulString)){
%>
	<marquee style="color:blue; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Inserted Successfully<<</marquee>
<%
}
else if("false".equals(unitInsertedSuccessfulString)){
%>
	<marquee style="color:red; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Insertion Failed<<</marquee>
<%
}
if("true".equals(unitUpdatedSuccessfullyString)){
%>
	<marquee style="color:blue; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Updated Successfully<<</marquee>
<%
}
if("true".equals(unitDeletedSuccessfullyString)){%>
	<marquee style="color:blue; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Deleted Successfully<<</marquee>	
<%}
Unit unitEditSessionObj = null;
if("true".equals(request.getParameter("editUnit"))){
	unitEditSessionObj = (Unit) session.getAttribute("Edit Unit");
}
%>
	<div class="row-fluid" style="height:80%;">
		<div class="span12" style="height:100%;">
			<div class="box" style="height:100%;">
				<div class="box-title">
					<%if(unitEditSessionObj==null){%>
					<h3><i class="icon-edit"></i> Add New Unit </h3>
					<%	}else{%>
					<h3><i class="icon-edit"></i> Edit Unit </h3>
					<%}%>
				</div>
				<div class="box-content">
					<form id="form" action="UnitServlet" method="Post" class='form-horizontal' style="height:100%;">					
						<div class="control-group">
							<label for="textfield" class="control-label">Unit Name:</label>
							<div class="controls">
								<input type="text"  placeholder="Unit Name" name="txtUnitName" id="txtUnitName" class="input-xlarge" value="<% if(unitEditSessionObj!= null)out.print(unitEditSessionObj.getUnit());%>" required/>
								<%if(unitEditSessionObj==null){%>
								<span class="help-block">Unit of Product</span>
								<%}else{%>
								<span class="help-block">Change Name Of Product Unit</span>
								<%}%>
							</div>
						</div>
						<%if(unitEditSessionObj==null){%>
							<button type="submit" class="btn btn-primary">Add Unit</button>
							<%}
							else{%>
							<script>
							document.getElementById("form").setAttribute("action","UnitChangesServlet");
							</script>
							<button type="submit" class="btn btn-primary"> Apply Changes </button>
						<%}%>
					</form>
				</div>

				<!--  Table Start   -->
				<%
				mall = (MallBranchGodown)session.getAttribute("Current Mall");
				unitServe = new UnitService();
				unitsOfCurrentMall = unitServe.getUnitsOfCurrentMall(mall);
				%>
				<div class="row-fluid">
					<div class="span12">
						<div class="box box-color box-bordered">
							<div class="box-title">
								<h3>Units</h3>
							</div>
							<div class="box-content nopadding">
								<table id="example" class="table table-hover table-nomargin table-bordered usertable">
									<thead>
										<tr>
											<th>Id</th>
											<th>Unit Name</th>
											<th class='hidden-350'>Status</th>
											<th class='hidden-480'>Options</th>
										</tr>
									</thead>
									<tbody>
									<%
									int x=1;
									for (Unit ob : unitsOfCurrentMall) {
										%>
									<tr>
										<td><%=x++%></td>
										<td><%= ob.getUnit() %></td>
										<td class='hidden-350'><span class="label label-satgreen">Active</span></td>
										<td class='hidden-480'>
										<center>
											<a href="UnitChangesServlet?id=<%= ob.getId()%>&flag=edit" class="btn" rel="tooltip" title="Edit"><i class="icon-edit"></i></a>
											<a href="UnitChangesServlet?id=<%= ob.getId()%>&flag=delete" class="btn" rel="tooltip" title="Delete"><i class="icon-remove"></i></a>
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