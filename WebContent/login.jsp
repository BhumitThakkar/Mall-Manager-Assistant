<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>
<head>

	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<!-- Apple devices fullscreen -->
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<!-- Apple devices fullscreen -->
	<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />

<title>LogIn</title>

<%@include file="Cpanel/css.jsp" %>
<%-- <%@include file="Cpanel/js.jsp" %> --%>
		<!--  My own jquery-->
	<script src="js/myjquery.js"></script>
		<!--  My own js-->
	<script type="text/javascript" src="js/myjs.js"></script>

	<script src="js/jquery.min.js"></script>
	<script src="js/plugins/nicescroll/jquery.nicescroll.min.js"></script>
	<script src="js/plugins/validation/jquery.validate.min.js"></script>
	<script src="js/plugins/validation/additional-methods.min.js"></script>
	<script src="js/plugins/icheck/jquery.icheck.min.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<script src="js/Moment.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script src="js/bootstrap-datepicker.min.js"></script>
	<script src="js/eakroko.js"></script>
	<!-- Nice Scroll -->
	<script src="js/plugins/nicescroll/jquery.nicescroll.min.js"></script>
	<!-- imagesLoaded -->
	<script src="js/plugins/imagesLoaded/jquery.imagesloaded.min.js"></script>
	<!-- jQuery UI -->
	<script src="js/plugins/jquery-ui/jquery.ui.core.min.js"></script>
	<script src="js/plugins/jquery-ui/jquery.ui.widget.min.js"></script>
	<script src="js/plugins/jquery-ui/jquery.ui.mouse.min.js"></script>
	<script src="js/plugins/jquery-ui/jquery.ui.resizable.min.js"></script>
	<script src="js/plugins/jquery-ui/jquery.ui.sortable.min.js"></script>
	<script src="js/plugins/jquery-ui/jquery.ui.datepicker.min.js"></script>
	<!-- slimScroll -->
	<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<!-- Bootbox -->
	<script src="js/plugins/bootbox/jquery.bootbox.js"></script>
	<!-- dataTables -->
	<script src="js/plugins/datatable/jquery.dataTables.min.js"></script>
	<script src="js/plugins/datatable/TableTools.min.js"></script>
	<script src="js/plugins/datatable/ColReorderWithResize.js"></script>
	<script src="js/plugins/datatable/ColVis.min.js"></script>
	<script src="js/plugins/datatable/jquery.dataTables.columnFilter.js"></script>
	<script src="js/plugins/datatable/jquery.dataTables.grouping.js"></script>
	<!-- Chosen -->
	<script src="js/plugins/chosen/chosen.jquery.min.js"></script>
	<!-- Theme framework -->
	<script src="js/eakroko.min.js"></script>
	<!-- Theme scripts -->
	<script src="js/application.min.js"></script>
	<!-- Just for demonstration -->
	<script src="js/demonstration.min.js"></script>



<!-- Favicon -->
<link rel="icon" href="img/favicon.png" sizes="16x16" type="image/png"/>
<!-- Apple devices Homescreen icon -->
<link rel="apple-touch-icon-precomposed" href="" />

	<%
			Cookie cookie[] = request.getCookies();
			Cookie cookie2 = null;
			if (cookie != null) {
				for (int i = cookie.length; i > 0; i--) {
					if (cookie[i-1].getName().equals("txtMailID")) {
						cookie2 = cookie[i-1];
					}
				}
			}
	%>
</head>

<body class="login bg">
<%
String passChangedSuccessfully = request.getParameter("passChanged");
if(passChangedSuccessfully!=null){
	if(passChangedSuccessfully.equals("yes")){%>
<marquee style="color:white;" bgcolor="transparent" behavior="alternate">Password Changed Successfully</marquee>
<%}
else if(passChangedSuccessfully.equals("no")){
%>
<div><marquee style="color:white;" bgcolor="transparent" behavior="alternate">OTP didn't match. Password Change Fail</marquee></div>
<%}
}
String UserMail = request.getParameter("txtMailId");
String PassErrorString = (String) session.getAttribute("Pass Error");
String UnknownUserErrorString = (String) session.getAttribute("Unknown User Error");
%>
	<div class="wrapper" style="top:37%;">
		<div class="login-body">
		<br/>
		<h1 style="text-align: center; color:black">
		 Mall Manager Assistant<br/>~~~~~~~~~
		<hr/>
		<a href="signup.jsp" id="jquerySignup" style="color:#368ee0"> >>Sign Up !<< </a>
		<hr/>
		</h1>
			<h2 align="center" style=" margin-top:-25px;  color:#368ee0; font-size: 35px;">LOG IN</h2>
				<form onsubmit="save();" action="LoginServlet" method="post" class='form-validate' id="loginForm" name="loginForm">
				
				<div class="control-group">
					<div class="email controls">

						<input type="text" id="txtMailId" name="txtMailId" onblur="chk();" value="<%if(UserMail!=null){%><%=UserMail%><%} %>" placeholder="xyz@abc.com" class='input-block-level' data-rule-required="true" data-rule-email="true">
						<% if(UnknownUserErrorString != null){
						%>
							<span class="help-block" style="color: red;">Wrong Email, No Such User Exists <a id="tmpSignup" href="" onclick="attachHref('txtMailId','tmpSignup','signup.jsp','txtEmailId');">signup</a></span>
						<%
						session.removeAttribute("Unknown User Error");
						}
						if(cookie2 != null){
								cookie2.setMaxAge(0);
								response.addCookie(cookie2);
							}
						%>
					</div>
				</div>
				<div class="control-group">
					<div class="pw controls">
						<input type="password" id="txtPassword" name="txtPassword" placeholder="*********" class='input-block-level' data-rule-required="true">
						<% if(PassErrorString != null){
						%>
							<span class="help-block" style="color: red;">Wrong Password</span>
						<%
						session.removeAttribute("Pass Error");
						}%>
						</div>
				</div>
				<div class="submit">
					<div class="remember" id="remember">
 						<input type="checkbox" id="txtCheckbox" onfocus="borderBlue();" onblur="removeStyle('remember');" name="remember" value="checked" class='icheck-me' data-skin="square" data-color="blue"/>
						<label for="remember">Remember Me</label>
					</div>
					
					<input type="submit" id="submit" value="Log In" class='btn btn-primary' onfocus="Shadow();" onblur="removeStyle('submit');"/>
				</div>
			</form>
			<div class="forget">
				<a href="" id="fp" onclick="temp1();" onfocus="forgetPass();" onblur="removeStyle('fp2');"><span id="fp2">Forgot Password</span></a>
			</div>
		</div>
	</div>
<script src="js/myjquery.js"></script>
</body>
</html>