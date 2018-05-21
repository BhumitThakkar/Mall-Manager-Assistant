<%@page import="service.ReminderService"%>
<%@page import="model.Product"%>
<%@page import="service.ProductService"%>
<%@page import="model.Reminder"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Note</title>
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
Reminder reminderEditSessionObj = null;
if("true".equals(request.getParameter("editReminder"))){
	reminderEditSessionObj = (Reminder) session.getAttribute("Edit Reminder");
}
String reminderDeletedSuccessfullyString = request.getParameter("reminderDeletedSuccessfully");
String reminderInsertedSuccessfullyString = request.getParameter("reminderInsertedSuccessfully");
String reminderUpdatedSuccessfullyString = request.getParameter("reminderUpdatedSuccessfully");
if("true".equals(reminderDeletedSuccessfullyString)){%>
<marquee style="color:blue; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Deleted Successfully<<</marquee>	
<%}
if("true".equals(reminderUpdatedSuccessfullyString)){
%>
	<marquee style="color:blue; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Updated Successfully<<</marquee>
<%
}
if("true".equals(reminderInsertedSuccessfullyString)){
%>
	<marquee style="color:blue; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Inserted Successfully<<</marquee>
<%
}
else if("false".equals(reminderInsertedSuccessfullyString)){
%>
	<marquee style="color:red; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Insertion Failed<<</marquee>
<%
}
%>

<div class="row-fluid">
		<div class="span12">
			<div class="box">
				<div class="box-title">
				<%if(reminderEditSessionObj==null){%>
					<h3><i class="icon-edit"></i> Add New Note/Reminder </h3>
					<%	}else{%>
					<h3><i class="icon-edit"></i> Edit Note/Reminder </h3>
					<%}%>
				</div>

				<div class="box-content">
					<form id="form" action="ReminderServlet" method="Post" class='form-horizontal'>

					<div class="control-group">
						<label for="textfield" class="control-label">Product Number:</label>

						<div class="dropdown controls">
						<button class="btn btn-primary dropdown-toggle" style="width:284px;" type="button" data-toggle="dropdown">Select Product Number
							<span class="caret"></span></button>
						<ul class="dropdown-menu" style="width:282px; max-height: 15em; overflow: auto;">
							<li><a onclick="setValueTo('DEFAULT','txtProductNumber')"><span>DEFAULT</span></a></li>
							<%
							ReminderService reminderServe = new ReminderService();
							ProductService productServe = new ProductService();
							mall = (MallBranchGodown)session.getAttribute("Current Mall");
							List<Product> tblList = productServe.getProductsOfCurrentMall(mall);
							for (Product ob : tblList) {
							%>
							<li><a onclick="setValueTo('<%=ob.getProductNumber()%>','txtProductNumber');"><span><%=ob.getProductNumber()%></span></a></li>
							<% } %>
						</ul>
						</div>
						
						<div class="control-group">
							<div class="controls">
								<input type="text"  placeholder="Product Code" name="txtProductNumber" id="txtProductNumber" class="input-xlarge" readonly="readonly" value="<% if(reminderEditSessionObj != null)out.print(reminderEditSessionObj.getProductNumber());else out.print("DEFAULT");%>" required/>
								<span class="help-block">Code of Product that needs reminder</span>
								<span class="help-block">Select "DEFAULT" if not applicable</span>
							</div>
						</div>
						
					</div>
	
					<div class="control-group">
						<label for="textfield" class="control-label">Product Category:</label>
						<div class="controls">
							<input type="text"  placeholder="Reminder Category" name="txtReminderCategory" id="txtReminderCategory" class="input-xlarge" value="<% if(reminderEditSessionObj != null)out.print(reminderEditSessionObj.getReminderCategory());%>" required/>
							<span class="help-block">Category of Reminder</span>
						</div>
					</div>	
					
					<div class="control-group">
						<label for="textfield" class="control-label">Note/Reminder :</label>
						<div class="controls">
							<textarea rows="5" cols="15" placeholder="Note/Reminder Description" name="txtReminder" id="txtReminder" class="input-xlarge" required><%if(reminderEditSessionObj != null)
								{
								out.print(reminderEditSessionObj.getReminderDetails());
								}
							%></textarea>
							<span class="help-block">Note/Reminder Description</span>
						</div>
					</div>
					
						<%if(reminderEditSessionObj==null){%>
						<button type="submit" class="btn btn-primary">Add Reminder</button>
							<%}
							else{%>
							<script>
							document.getElementById("form").setAttribute("action","ReminderChangesServlet");
							</script>
							<button type="submit" class="btn btn-primary"> Apply Changes </button>
						<%}%>
					</form>
				</div>

				<center><a href="QuantityAvailableCheckServlet"><button type="button" class="btn btn-primary">Update Table</button></a></center>
				<!--  Table Start   -->
				<%
				List<Reminder> remindersOfCurrentMall;
				mall = (MallBranchGodown)session.getAttribute("Current Mall");
				remindersOfCurrentMall = reminderServe.getRemindersOfCurrentMall(mall);
				%>
				
				<div class="row-fluid">
					<div class="span12" >
						<div class="box box-color box-bordered">
							<div class="box-title">
								<h3>Notes/Reminder</h3>
							</div>
							<div class="box-content nopadding" style="overflow:auto;">
								<table id="example" class="table table-hover table-nomargin table-bordered usertable">
									<thead>
										<tr>
											<th>Id</th>
											<th>Product Number</th>
											<th>Product Category</th>
											<th>Reminder/Note</th>
											<th>Options</th>
										</tr>
									</thead>
								<tbody>
								<%
								int x=1;
								for (Reminder ob : remindersOfCurrentMall) {
								%>
								<tr>
									<td><%= x++ %></td>
									
									<td><%= ob.getProductNumber() %></td>
									
									<% if(null==ob.getReminderCategory()){%>
										<td>Default</td>
									<%}else{%>
										<td><%= ob.getReminderCategory() %></td>
									<%}%>
									
									<td><%= ob.getReminderDetails()%></td>
									<td class='hidden-480'>
									<center>
										<a href="ReminderChangesServlet?id=<%= ob.getId()%>&flag=edit" class="btn" rel="tooltip" title="Edit"><i class="icon-edit"></i></a>
										<a href="ReminderChangesServlet?id=<%= ob.getId()%>&flag=delete" class="btn" rel="tooltip" title="Delete"><i class="icon-remove"></i></a>
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