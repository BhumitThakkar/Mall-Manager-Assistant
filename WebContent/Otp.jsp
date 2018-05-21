<!-- 
Came from MailServlet.java
 -->
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OTP</title>
<%@include file="Cpanel/css.jsp" %>
<%@include file="Cpanel/js.jsp" %>
<!-- Favicon -->
<link rel="icon" href="img/favicon.png" sizes="16x16" type="image/png"/>
<!-- Apple devices Homescreen icon -->
<link rel="apple-touch-icon-precomposed" href="" />
</head>
<body class='login'>

<div class="wrapper" style="top:37%;">
		<h1 style="text-align: center;"><a href="signup.jsp"> >>Sign Up<< </a>
		<br>
		<a href="login.jsp"> >>Log In<< </a>
		</h1>
		<div class="login-body">
			<h2>OTP</h2>
				<form action="OtpServlet" method="post" class='form-validate' id="otpForm" name="otpForm">
				
				<div class="control-group">
					<div class="email controls">

						<input type="text" id="txtOtp" name="txtOtp" placeholder="6 Digit Code in Mail" class='input-block-level' data-rule-required="true">
					
					</div>
				</div>
				
				<div class="submit">
					<input type="submit" id="submit" value="Submit OTP" class='btn btn-primary' onfocus="Shadow();" onblur="removeStyle('submit');"/>
				</div>
			</form>
			<div class="forget">
				<a href="MailServlet?resend=yes" onfocus="otpResend();" onblur="removeStyle('otp');"><span id="otp">Re-Send OTP</span></a>
			</div>
			
		</div>
	</div>
</body>
</html>