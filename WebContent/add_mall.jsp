<%@page import="service.MallService"%>
<%@page import="model.MallBranchGodown"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Mall</title>
<%@include file="Cpanel/css.jsp" %>
<%@include file="Cpanel/js.jsp" %>
</head>
<!-- Favicon -->
<link rel="icon" href="img/favicon.png" sizes="16x16" type="image/png"/>
<!-- Apple devices Homescreen icon -->
<link rel="apple-touch-icon-precomposed" href="" />

<body>
<%
String txtDropdownMallName = request.getParameter("txtDropdownMallName");			/* Came after selecting a item from dropdown on the same page */
%>
 <div class="row-fluid">
		<div class="span12">
			<div class="box">
				<div class="box-title">
					<h3><i class="icon-edit"></i> Add Mall </h3>
				</div>
				<div class="box-content">
 						<form action="SignupServlet" method="get" class='form-horizontal bg2'>		<!-- bg2 class is in my.css -->
						
						<div class="control-group">
							<label for="radio" class="control-label" >Mall Name:</label>

								<div class="dropdown controls">
								<button class="btn btn-primary dropdown-toggle" style="width:22%;" type="button" data-toggle="dropdown">Select Mall
									<span class="caret"></span></button>
								<ul class="dropdown-menu" style="width:22%; max-height: 15em; overflow: auto;">
									<%
									MallService ms = new MallService();
									List<MallBranchGodown> tblList = ms.selectAll();
									for (MallBranchGodown ob : tblList) {
									%>
									<li><a href="?txtDropdownMallName=<%=ob.getMall_name()%>"><%=ob.getMall_name()%></a></li>
									<% } %>
								</ul>
								</div>
								
								<div class="controls">
									<input type="text" name="txtMallName" id="txtMallName" onblur="checkAndSign();" value="<%if(txtDropdownMallName!=null){%><%=txtDropdownMallName%><%}%>" placeholder="Eg: Big Bazar" class="input-xlarge" required/>
									<span class="help-block" style="color:red;">Select your Mall Name from Drop Down Menu<br/>If not in list, Enter Mall Name in above Text Box</span>
								</div>
								
						</div>

<hr width="35%" size="3" color="black"/>

						<div class="control-group">
						<label style="font-size: 20px; width: 15%;">Select appropriate:</label>
						</div>

						<div class="control-group">
							<label for="radio" class="control-label" ><center><input type="radio" name="txtMBG" value="MB" class='icheck-me' data-skin="square" data-color="blue"/></center></label>
							<div class="controls">
								<span class="help-block" style="margin-top:4px; font-size:18px;">Mall & Branch both are one and the same</span>
							</div>
						</div>
						
						<div class="control-group">
							<label for="radio" class="control-label"><center><input type="radio" name="txtMBG" value="M" class='icheck-me' data-skin="square" data-color="blue"/></center></label>
							<div class="controls">
								<span class="help-block" style="margin-top:0px; font-size:18px;">Mall</span>
								<span class="help-block">(Add Details of the Main Office of your Mall below)</span>
							</div>
						</div>

						<div class="control-group">
							<label for="radio" class="control-label" ><center><input type="radio" name="txtMBG" value="B" class='icheck-me' data-skin="square" data-color="blue"/></center></label>
							<div class="controls">
								<span class="help-block" style="margin-top:4px; font-size:18px;">Branch of Mall</span>
							</div>
						</div>
						
						<div class="control-group">
							<label for="radio" class="control-label" ><center><input type="radio" name="txtMBG" value="G" class='icheck-me' data-skin="square" data-color="blue"/></center></label>
							<div class="controls">
								<span class="help-block" style="margin-top:4px; font-size:18px;">Godown of Mall</span>
							</div>
						</div>

<hr width="35%" size="3" color="black"/>

						<div class="control-group">
							<label for="textfield" class="control-label" >Landmark: </label>
							<div class="controls">
								<input type="text" name="txtLandmark" id="txtLandmark" placeholder="Eg: Opp/Near xyz complex/school" class="input-xlarge" required/>
								<span class="help-block">Nearby/Opposite Landmark</span>
							</div>
						</div>

						<div class="control-group">
							<label for="textfield" class="control-label" >Area: </label>
							<div class="controls">
								<input type="text" name="txtArea" id="txtArea" placeholder="Eg: Rambaug" class="input-xlarge" required/>
								<span class="help-block">Area of your Mall Office/Branch/Godown</span>
							</div>
						</div>

						<div class="control-group">
							<label for="textfield" class="control-label" >City: </label>
							<div class="controls">
								<input type="text" name="txtCity" id="txtCity" placeholder="Eg: Ahmedabad" class="input-xlarge" required/>
								<span class="help-block">City of your Mall Office/Branch/Godown</span>
							</div>
						</div>

						<div class="control-group">
							<label for="textfield" class="control-label" >Pin code:</label>
							<div class="controls">
								<input type="number" min="000000" max="999999" name="txtPin" id="txtPin" onblur="validatePin();" placeholder="Eg: 380008" class="input-xlarge" required/>
								<span class="help-block">Pin code,where your Mall Office/Branch/Godown is</span>
							</div>
						</div>

						<div class="control-group">
							<label for="textfield" class="control-label" >State: </label>
							<div class="controls">
								<input type="text" name="txtState" id="txtState" placeholder="Gujarat" class="input-xlarge" required/>
								<span class="help-block">State of your Mall Office/Branch/Godown</span>
							</div>
						</div>
						
						<div class="control-group">
							<label for="textfield" class="control-label" >Country: </label>
							<div class="controls">
								<input type="text" name="txtCountry" id="txtCountry" placeholder="India" class="input-xlarge" required/>
								<span class="help-block">Country of your Mall Office/Branch/Godown</span>
							</div>
						</div>
						
						<div class="control-group">
							<label for="textfield" class="control-label">Mobile No:</label>
							<div class="controls">
								<input type="text" name="txtMobile" id="txtMobile" placeholder="Eg: 7383950897" onblur="validateMobile();" class="input-xlarge" required/>
								<span class="help-block">Contact No of your Mall Office/Branch/Godown, 10 Digits </span>
							</div>
						</div>
												
						<div class="form-actions">
							<a href="add_mall.jsp"><button type="button" class="btn btn-warning">Reset</button></a>
							<button type="submit" class="btn btn-primary">Submit</button>
						</div>

					</form>

				</div>
			</div>
		</div>
	</div>
</body>
</html>