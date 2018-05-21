<%@page import="service.MallService"%>
<%@page import="model.MallBranchGodown"%>
<%@page import="java.util.List"%>
<%@page import="model.Manager"%>
<%@page import="service.ManagerService"%>
<%@page import="org.hibernate.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>

<%@include file="Cpanel/css.jsp" %>
<%@include file="Cpanel/js.jsp" %>
<!-- Favicon -->
<link rel="icon" href="img/favicon.png" sizes="16x16" type="image/png"/>
<!-- Apple devices Homescreen icon -->
<link rel="apple-touch-icon-precomposed" href="" />

<%
String currentLogin1 = (String) request.getSession().getAttribute("Login Session");			//When User wants to add partner manager for current Mall.
if(currentLogin1 != null){%>
<%@include file="Cpanel/header.jsp" %>
<%}%>

<!-- Favicon -->
<link rel="shortcut icon" href="" />
<!-- Apple devices Homescreen icon -->
<link rel="apple-touch-icon-precomposed" href="" />

</head>

<body>
<%
String EmailErrorString= (String) session.getAttribute("Email Exists Error");		/* Came from signupServlet when email already exists */
String InsertionErrorString= (String) session.getAttribute("Insertion Error");
String mailUser = request.getParameter("txtEmailId");			/* Came From Login */
String redirectedName = request.getParameter("txtName");			/* Came From "do post" of signupServlet */
String redirectedNumber = request.getParameter("txtNumber");			/* Came From "do post" of signupServlet */

ManagerService managerServe = new ManagerService();
MallService mallServe = new MallService();
%>

<div class="row-fluid" style="height:80%;">
		<div class="span12" style="height:100%;">
			<div class="box" style="height:100%;">
				<div class="box-title">
					<h3><i class="icon-edit"></i> Sign Up </h3>
				</div>
				<div class="box-content" style="height:100%;">
 						<form id="form" action="SignupServlet" method="Post" class='form-horizontal bg2' style="height:100%;">		<!-- bg2 class is in my.css -->
						<% if(InsertionErrorString != null){%>
								<span class="help-block" style="color: red;">There is some error in Insertion</span>
						<%
						session.removeAttribute("Insertion Error");
						}%>
						
						<!-- Will Be Executed Only if already user has logged in and needs to add new partner  -->
						<%
							if(currentLogin1 != null){
								Manager manager = managerServe.getManagerByEmail(currentLogin1);//Retrive Current User
							if(manager !=null){
							MallBranchGodown mall = (MallBranchGodown)session.getAttribute("Current Mall");
							List<Manager> colleaguesOfCurrentManager = mallServe.getColleaguesOfCurrentManager(manager,mall);			/* Mall will be needed to check weather the partners of current mall do not come in list to add partner*/
								if(colleaguesOfCurrentManager.size()!=0){
						%>
							<div class="control-group">
							<label for="radio" class="control-label" >Select Manager If Exist:</label>

								<div class="dropdown controls">
								<button class="btn btn-primary dropdown-toggle" style="width:22%;" type="button" data-toggle="dropdown">Select Manager
									<span class="caret"></span></button>
								<ul class="dropdown-menu" style="width:22%; max-height: 15em; overflow: auto;">
									<%
									for (Manager ob : colleaguesOfCurrentManager) {
										%>
										<li><a href="AddManagerServlet?txtManagerMail=<%=ob.getMail_id()%>"><%=ob.getName()%>-><%=ob.getMail_id()%></a></li>
										<% } %>
								</ul>
								</div>								
						</div>
						<%} } }%>
						<!--  -->
						<div class="control-group">
							<label for="textfield" class="control-label">Manager Name:</label>
							<div class="controls">
								<%
								String txtManagerName = "";
								if(redirectedName!=null){
									txtManagerName = redirectedName; 
								}
								else{
									txtManagerName = "";
								}
								%>
								<input type="text" value="<%=txtManagerName%>" placeholder="Eg: Anshuman Vora" name="txtManagerName" id="txtManagerName" class="input-xlarge" required/>
								<span class="help-block">Name of Manager</span>
							</div>
						</div>

						<div class="control-group">
							<label for="textfield" class="control-label">Mobile No:</label>
							<div class="controls">
							<%
								String txtManagerContact = "";
								if(redirectedNumber!=null){
									txtManagerContact = redirectedNumber; 
								}
								else{
									txtManagerContact = "";
								}
								%>
								<input type="text" name="txtMobile" id="txtMobile" value="<%=txtManagerContact%>" placeholder="Eg: 7383950897" onblur="validateMobile();" class="input-xlarge" required/>
								<span class="help-block">Contact No, 10 Digits </span>
							</div>
						</div>

						<div class="control-group">
							<label for="textfield" class="control-label" >Email Id:</label>
							<div class="controls">
							<%
								String txtManagerMail = "";
								if(mailUser!=null){
									txtManagerMail = mailUser; 
								}
								else{
									txtManagerMail = "";
								}
								%>
								<input type="text" name="txtMailId" id="txtMailId" value="<%=txtManagerMail%>" placeholder="Eg: xyz@pqr.com/co.in" onblur="validateMail();" class="input-xlarge" required/>
								<% if(EmailErrorString != null){%>
								<span class="help-block" style="color: red;">Email Already Exist, Enter new Email</span>
								<%
								session.removeAttribute("Email Exists Error");
								}%>
								<span class="help-block">You will be logging in with this e-mail id</span>
							</div>
						</div>
						
						<div class="control-group">
							<label for="password" class="control-label">Password</label>
							<div class="controls">
								<input type="password" minlength="9" name="txtPassword" id="txtPassword" onblur="validatePass('txtPassword')" placeholder="*********" class="input-xlarge" required/>
								<span class="help-block">Minimum length: 9</span>
							</div>
						</div>

						<div class="form-actions">
						<%
						if(currentLogin1 == null){
						%>
							<button type="submit" class="btn btn-primary">Add Mall</button>
						<%
						}
						else{
						%>
						<script>
						document.getElementById("form").setAttribute("action","AddManagerServlet");
						</script>
						<button type="submit" class="btn btn-primary">Submit</button>
						<%
						} %>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
<%	
if(currentLogin1 != null){%>
<script src="js/myjquery.js"></script>
<%}%>
</body>
</html>