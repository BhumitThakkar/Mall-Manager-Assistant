<%@page import="service.ManagerService"%>
<%@page import="java.util.List"%>
<%@page import="service.MallService"%>
<%@page import="model.MallBranchGodown"%>
<%@page import="model.Manager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Settings</title>
<%@include file="Cpanel/css.jsp" %>
<%@include file="Cpanel/js.jsp" %>
<%@include file="Cpanel/common.jsp" %>

<!-- Favicon -->
<link rel="icon" href="img/favicon.png" sizes="16x16" type="image/png"/>
<!-- Apple devices Homescreen icon -->
<link rel="apple-touch-icon-precomposed" href="" />

</head>
<body>
<jsp:include page="Cpanel/header.jsp"></jsp:include>

<%
String currentLogin = (String) request.getSession().getAttribute("Login Session");
ManagerService managerServe = new ManagerService();
Manager manager = managerServe.getManagerByEmail(currentLogin);
MallBranchGodown mallBranchGodown = null;
MallService mallServe = new MallService();
List<MallBranchGodown> managerMallList = null;
managerMallList = mallServe.selectMallsOfCurrentManager(manager);

String EmailErrorString= (String) session.getAttribute("Email Exists Error");		/* Came from ManagerChangesServlet when email already exists */
String ChangesResult= (String) request.getParameter("changesSuccessfull");		/* Came from ManagerChangesServlet when email already exists */
if("yes".equals(ChangesResult)){
%>
	<marquee style="color:blue; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Changes Done Successfully<<</marquee>
<%
}
else if("no".equals(ChangesResult)){
%>
	<marquee style="color:red; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Changes Failed<<</marquee>
<%
}
%>
<div class="row-fluid">
		<div class="span12">
			<div class="box">
				<form id="form1" action="DeleteManager" method="Post">
					<div>
						<button type="submit" style="width:100%" class="btn btn-primary">Delete Account</button>
					</div>
				</form>
				
				<div class="box-title">
					<h3><i class="icon-edit"></i> Account Settings </h3>
				</div>
				<div class="box-content">
					<form id="form2" action="ManagerChangesServlet" method="Post" class='form-horizontal' style="height:100%;">
						<div class="control-group">
							<label for="textfield" class="control-label">Manager Name:</label>
							<div class="controls">
								<input type="text" value="<%=manager.getName()%>" placeholder="Eg: Anshuman Vora" name="txtManagerName" id="txtManagerName" class="input-xlarge" required/>
								<span class="help-block">Name of Manager</span>
							</div>
						</div>

						<div class="control-group">
							<label for="textfield" class="control-label">Mobile No:</label>
							<div class="controls">
							<%
							%>
								<input type="text" name="txtMobile" id="txtMobile" value="<%=manager.getMobile_number()%>" placeholder="Eg: 7383950897" onblur="validateMobile();" class="input-xlarge" required/>
								<span class="help-block">Contact No, 10 Digits </span>
							</div>
						</div>

						<div class="control-group">
							<label for="textfield" class="control-label" >Email Id:</label>
							<div class="controls">
							<%
							%>
							<input type="hidden" name="txtMailIdOld" id="txtMailIdOld" value="<%=manager.getMail_id()%>"/><!-- Needed when email is not changed and any other details is changed -->
							<input type="text" name="txtMailId" id="txtMailId" value="<%=manager.getMail_id()%>" placeholder="Eg: xyz@pqr.com/co.in" onblur="validateMail();" class="input-xlarge" required/>
							<% if(EmailErrorString != null){%>
							<span class="help-block" style="color: red;">Email Already Exist, Enter new Email</span>
							<%
							session.removeAttribute("Email Exists Error");
							}%>
								<span class="help-block">You will be logging in with this e-mail id</span>
							</div>
						</div>						
						
						<div class="control-group">
							<label for="password" class="control-label">Password:</label>
							<div class="controls">
								<input type="password" minlength="9" name="txtPassword" id="txtPassword" value="<%=manager.getPassword()%>" onblur="validatePass('txtPassword')" placeholder="*********" class="input-xlarge" required/>
								<span class="help-block">Minimum length: 9</span>
							</div>
						</div>

						<div class="form-actions">
							<button type="submit" class="btn btn-primary">Apply Changes</button>
						</div>
					</form>
				</div>
				
				<div class="box-title">
					<h3><i class="icon-edit"></i> Leave Mall </h3>
				</div>
				<div class="box-content" id="OnlyOneMall">
						<table id="example" class="table table-hover table-nomargin table-bordered">
							<thead>
								<tr>
									<th>No</th>
									<th>Mall Name</th>
									<th>Mall Area</th>
									<th>Mall Category</th>
									<th>Mall Pin</th>
									<th class='hidden-480'>Leave Mall</th>
								</tr>
							</thead>
							<tbody>		
							<%
							int x=1;
							for (MallBranchGodown ob : managerMallList) {
								String mallCategory = ob.getM_b_g_mb();
								String mallCategoryFull = mallServe.getMallCategory(ob.getM_b_g_mb());
							%>
							<tr >
								<td><input type="hidden"><%=x++%></td>
								<td ><%= ob.getMall_name() %></td>
								<td><%= ob.getArea() %></td>
								<td><%= mallCategoryFull %></td>
								<td><%= ob.getPincode() %></td>
								<%if(manager.getMallBranchGodowns().size()!=1)
								{%>
								<td class='hidden-480'>
									<div style="text-align: center;"><a href="ResignMall?txtMallId=<%=ob.getId()%>" class="btn" rel="tooltip" title="Delete"><i class="icon-remove"></i></a></div>
								</td>
								<%	}
								else{%>
								<td class='hidden-480'>
									<b style="color:red;">Can't Resign. Must Manage At Least 1 Mall</b>
								</td>
								<%	}%>
								</tr>
							<% } %>
							</tbody>
						</table>
				</div>
			</div>
		</div>
	</div>
<script src="js/myjquery.js"></script>
</body>
</html>