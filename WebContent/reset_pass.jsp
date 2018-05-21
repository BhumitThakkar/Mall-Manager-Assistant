<!--
Came from OtpServlet.java 
And from ResetPass.java when fails to update
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reset Password</title>
<%@include file="Cpanel/css.jsp" %>
<%@include file="Cpanel/js.jsp" %>
<!-- Favicon -->
<link rel="icon" href="img/favicon.png" sizes="16x16" type="image/png"/>
<!-- Apple devices Homescreen icon -->
<link rel="apple-touch-icon-precomposed" href="" />
</head>
<body class='login'>

<%
if(request.getParameter("fail")!=null)
if(request.getParameter("fail").equals("1")){%>
<script>
alert("Password Failed to update. Try Again");
</script>
<%
}%>

<div class="wrapper" style="top:37%;">
		<h1 style="text-align: center;"><a href="signup.jsp"> >>Sign Up<< </a>
		<br>
		<a href="login.jsp"> >>Log In<< </a>
		</h1>
		<div class="login-body">
			<h2>New Password</h2>
				<form action="ResetPass" method="post" class='form-validate'>
				
				<div class="control-group">
					<div class="email controls">

						<input type="password" id="pass" name="txtPass" onblur="validatePass('txtPassword')" min-length="9" placeholder="Enter New Password" class='input-block-level' data-rule-required="true">
					
					</div>
				</div>
				
				<div class="control-group">
					<div class="email controls">

						<input type="password"  onblur="confirmPass();" id="pass1" name="txtPass1" min-length="9" placeholder="Confirm New Password" class='input-block-level' data-rule-required="true">
					
					</div>
				</div>
				
				<div class="submit">
					<input type="submit" id="submit" value="Submit" class='btn btn-primary' onfocus="Shadow();" onblur="removeStyle('submit');"/>
				</div>
				
				<div class="forget">
				<a href=""><span id="otp">And Thats Done Manager, Smile :)</span></a>
				</div>
			</form>
			
		</div>
	</div>
</body>
</html>