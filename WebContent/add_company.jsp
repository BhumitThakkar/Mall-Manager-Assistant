<%@page import="service.CompanyService"%>
<%@page import="model.Company"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company</title>
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
List<Company> companiesOfCurrentMall;
CompanyService companyServe;
String companyInsertedSuccessfullyString = request.getParameter("companyInsertedSuccessfully");
String companyDeletedSuccessfullyString = request.getParameter("companyDeletedSuccessfully");
String companyUpdatedSuccessfullyString = request.getParameter("companyUpdatedSuccessfully");
if("true".equals(companyInsertedSuccessfullyString)){
%>
	<marquee style="color:blue; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Inserted Successfully<<</marquee>
<%
}
else if("false".equals(companyInsertedSuccessfullyString)){
%>
	<marquee style="color:red; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Insertion Failed<<</marquee>
<%
}
if("true".equals(companyUpdatedSuccessfullyString)){
%>
	<marquee style="color:blue; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Updated Successfully<<</marquee>
<%
}
if("true".equals(companyDeletedSuccessfullyString)){%>
	<marquee style="color:blue; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Deleted Successfully<<</marquee>	
<%}
Company companyEditSessionObj = null;
if("true".equals(request.getParameter("editCompany"))){
	companyEditSessionObj = (Company) session.getAttribute("Edit Company");
}
%>
	<div class="row-fluid">
		<div class="span12">
			<div class="box">
				<div class="box-title">
					<%if(companyEditSessionObj==null){%>
					<h3><i class="icon-edit"></i> Add New Company </h3>
					<%	}else{%>
					<h3><i class="icon-edit"></i> Edit Company </h3>
					<%}%>
				</div>

				<div class="box-content">
					<form id="form" action="CompanyServlet" method="Post" class='form-horizontal'>					
						<div class="control-group">
							<label for="textfield" class="control-label">Company Name:</label>
							<div class="controls">
								<input type="text"  placeholder="Company Name" name="txtCompanyName" id="txtCompanyName" class="input-xlarge" value="<% if(companyEditSessionObj!= null)out.print(companyEditSessionObj.getCompany());%>" required/>
								<%if(companyEditSessionObj==null){%>
								<span class="help-block">Company of Product</span>
								<%}else{%>
								<span class="help-block">Change Name Of Product Company</span>
								<%}%>
							</div>
						</div>
						<%if(companyEditSessionObj==null){%>
							<button type="submit" class="btn btn-primary">Add Company</button>
							<%}
							else{%>
							<script>
							document.getElementById("form").setAttribute("action","CompanyChangesServlet");
							</script>
							<button type="submit" class="btn btn-primary"> Apply Changes </button>
						<%}%>
					</form>
				</div>

				<!--  Table Start   -->
				<%
				mall = (MallBranchGodown)session.getAttribute("Current Mall");
				companyServe = new CompanyService();
				companiesOfCurrentMall = companyServe.getCompaniesOfCurrentMall(mall);
				%>

				<div class="row-fluid">
					<div class="span12">
						<div class="box box-color box-bordered">
							<div class="box-title">
								<h3>Companies</h3>
							</div>
							<div class="box-content nopadding">
								<table id="example" class="table table-hover table-nomargin table-bordered usertable">
									<thead>
										<tr>
											<th>Id</th>
											<th>Company Name</th>
											<th class='hidden-350'>Status</th>
											<th class='hidden-480'>Options</th>
										</tr>
									</thead>
									<tbody>
									<%
									int x=1;
									for (Company ob : companiesOfCurrentMall) {
									%>
									<tr>
										<th><%=x++%></th>
										<th><%= ob.getCompany() %></th>
										<th class='hidden-350'><span class="label label-satgreen">Active</span></th>
										<th class='hidden-480'>
										<center>
											<a href="CompanyChangesServlet?id=<%= ob.getId()%>&flag=edit" class="btn" rel="tooltip" title="Edit"><i class="icon-edit"></i></a>
											<a href="CompanyChangesServlet?id=<%= ob.getId()%>&flag=delete" class="btn" rel="tooltip" title="Delete"><i class="icon-remove"></i></a>
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