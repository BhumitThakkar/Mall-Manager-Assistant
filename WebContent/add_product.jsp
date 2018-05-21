<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="controller.ProductFile"%>
<%@page import="service.CompanyService"%>
<%@page import="model.Company"%>
<%@page import="model.Unit"%>
<%@page import="service.UnitService"%>
<%@page import="model.Size"%>
<%@page import="service.SizeService"%>
<%@page import="model.Category"%>
<%@page import="service.CategoryService"%>
<%@page import="service.ProductService"%>
<%@page import="model.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product</title>
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
Product productEditSessionObj = null;
if("true".equals(request.getParameter("ExcelProductInsertionError"))){
ProductFile productFileObj = (ProductFile)request.getSession().getAttribute("ExcelFileProductObjSession");
if(productFileObj!=null){
	List<String> ErrorCells = productFileObj.getNonInsertedProductRowsCols();
	%>
	<div>
	<table>
	<th>Error Cells In Product Excel File:</th>
	<%
	for(String ErrorCell : ErrorCells){
	%>
	<th>~<%=ErrorCell%>~</th>
	<%}%>
	</table>
	</div>
	<%
	session.removeAttribute("ExcelFileProductObjSession");
	productFileObj=null;												//remaining to add in other files	
}
}
if("true".equals(request.getParameter("editProduct"))){
	productEditSessionObj = (Product) session.getAttribute("Edit Product");
}
String productDeletedSuccessfullyString = request.getParameter("productDeletedSuccessfully");
String productInsertedSuccessfullyString = request.getParameter("productInsertedSuccessfully");
String productUpdatedSuccessfullyString = request.getParameter("productUpdatedSuccessfully");
String productAlreadyExistString = request.getParameter("productAlreadyExist");
String productCpMoreThanSpString = request.getParameter("productCpMoreThanSp");
String productCpMoreThanSpFromUpdateString = request.getParameter("productCpMoreThanSpFromUpdate");
List<Product> productsOfCurrentMall;
ProductService productServe;
mall = (MallBranchGodown)session.getAttribute("Current Mall");
if("true".equals(productCpMoreThanSpString)){%>
<marquee style="color:red; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Product CP can't be more than SP, Failed to insert<<</marquee>
<%}
if("true".equals(productCpMoreThanSpFromUpdateString)){%>
<marquee style="color:red; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Product CP can't be more than SP, Failed to update<<</marquee>
<%}
if("true".equals(productAlreadyExistString)){%>
	<marquee style="color:red; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Product Already Exists<<</marquee>
<%}
if("true".equals(productDeletedSuccessfullyString)){%>
<marquee style="color:blue; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Deleted Successfully<<</marquee>	
<%}
if("true".equals(productUpdatedSuccessfullyString)){
%>
	<marquee style="color:blue; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Updated Successfully<<</marquee>
<%
}
if("true".equals(productInsertedSuccessfullyString)){
%>
	<marquee style="color:blue; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Inserted Successfully<<</marquee>
<%
}
else if("false".equals(productInsertedSuccessfullyString)){
%>
	<marquee style="color:red; font-size: 15px;" bgcolor="transparent" behavior="alternate" scrollamount=3>>>Insertion Failed<<</marquee>
<%
}
%>
	<div class="row-fluid">
		<div class="span12">
			<div class="box">
				<div class="box-title">
					<%if(productEditSessionObj==null){%>
					<h3><i class="icon-edit"></i> Add New Product </h3>
					<%	}else{%>
					<h3><i class="icon-edit"></i> Edit Product </h3>
					<%}%>
				</div>

				<div class="box-content">
					<form id="form" action="ProductServlet" method="Post" class='form-horizontal'>
						
						<div class="control-group">
							<label for="textfield" class="control-label">Product Number:</label>
							<div class="controls">
								<input type="text"  placeholder="Product Code" name="txtProductNumber" id="txtProductNumber" class="input-xlarge" value="<% if(productEditSessionObj != null)out.print(productEditSessionObj.getProductNumber()); %>" required/>
								<span class="help-block">Code of Product</span>
							</div>
						</div>
						
						<div class="control-group">
						<label for="radio" class="control-label">Product Company:</label>

						<div class="dropdown controls">
						<button class="btn btn-primary dropdown-toggle" style="width:284px;" type="button" data-toggle="dropdown">Select Company
							<span class="caret"></span></button>
						<ul class="dropdown-menu" style="width:282px; max-height: 15em; overflow: auto;">
							<li><a onclick="setValueTo('DEFAULT','txtProductCompany')"><span>DEFAULT</span></a></li>
							<%
							CompanyService companyServe = new CompanyService();
							List<Company> tblList = companyServe.selectAll(mall);
							for (Company ob : tblList) {
							%>
							<li><a onclick="setValueTo('<%=ob.getCompany()%>','txtProductCompany');"><span><%=ob.getCompany()%></span></a></li>
							<% } %>
						</ul>
							<a href="add_company.jsp"><input type="button" style="width:150px;" class="btn btn-primary" value="Add New Company"/></a>
						</div>

						<div class="control-group">
							<div class="controls">
								<input type="text"  placeholder="Product Company" name="txtProductCompany" id="txtProductCompany" class="input-xlarge" readonly="readonly" value="<% if(productEditSessionObj != null)out.print(productEditSessionObj.getCompany());else out.print("DEFAULT");%>" required/>
								<span class="help-block">Select "DEFAULT" if not applicable</span>
							</div>
						</div>
					</div>

						<div class="control-group">
							<label for="textfield" class="control-label">Product Name:</label>
							<div class="controls">
								<input type="text"  placeholder="Product Name" name="txtProductName" id="txtProductName" class="input-xlarge" value="<% if(productEditSessionObj != null)out.print(productEditSessionObj.getProductName()); %>" required/>
								<span class="help-block">Name of Product</span>
							</div>
						</div>
						
						<div class="control-group">
							<label for="textfield" class="control-label">Product Color:</label>
							<div class="controls">
								<input type="text"  placeholder="Product Color" name="txtProductColor" id="txtProductColor" class="input-xlarge" value="<%if(productEditSessionObj != null)out.print(productEditSessionObj.getColor());%>"/>
								<span class="help-block">Leave Blank If Not Applicable</span>
							</div>
						</div>
					
						<div class="control-group">
						<label for="radio" class="control-label">Product Category:</label>

							<div class="dropdown controls">
							<button class="btn btn-primary dropdown-toggle" style="width:284px;" type="button" data-toggle="dropdown">Select Category
								<span class="caret"></span></button>
							<ul class="dropdown-menu" style="width:282px; max-height: 15em; overflow: auto;">
								<li><a onclick="setValueTo('DEFAULT','txtProductCategory')"><span>DEFAULT</span></a></li>
								<%
								CategoryService categoryServe = new CategoryService();
								List<Category> tblList2 = categoryServe.selectAll(mall);
								for (Category ob : tblList2) {
								%>
								<li><a onclick="setValueTo('<%=ob.getCategory()%>','txtProductCategory');"><span><%=ob.getCategory()%></span></a></li>
								<% } %>
							</ul>
								<a href="add_category.jsp"><input type="button" style="width:150px;" class="btn btn-primary" value="Add New Category"/></a>
							</div>

						<div class="control-group">
							<div class="controls">
								<input type="text"  placeholder="Product Category" name="txtProductCategory" id="txtProductCategory" class="input-xlarge" readonly="readonly" value="<% if(productEditSessionObj != null)out.print(productEditSessionObj.getCategory());else out.print("DEFAULT");%>" required/>
								<span class="help-block">Select "DEFAULT" if not applicable</span>
							</div>
						</div>
					</div>
					
					<div class="control-group">
						<label for="radio" class="control-label">Product Size:</label>

						<div class="dropdown controls">
						<button class="btn btn-primary dropdown-toggle" style="width:284px;" type="button" data-toggle="dropdown">Select Size
							<span class="caret"></span></button>
						<ul class="dropdown-menu" style="width:282px; max-height: 15em; overflow: auto;">
							<li><a onclick="setValueTo('DEFAULT','txtProductSize');"><span>DEFAULT</span></a></li>
							<%
							SizeService sizeServe = new SizeService();
							List<Size> tblList3 = sizeServe.selectAll(mall);
							for (Size ob : tblList3) {
							%>
							<li><a onclick="setValueTo('<%=ob.getSize()%>','txtProductSize');"><span><%=ob.getSize()%></span></a></li>
							<% } %>
						</ul>
								<a href="add_size.jsp"><input type="button" style="width:150px;" class="btn btn-primary" value="Add New Size"/></a>
						</div>

						<div class="control-group">
							<div class="controls">
								<input type="text"  placeholder="Product Size" name="txtProductSize" id="txtProductSize" class="input-xlarge" readonly="readonly" value="<% if(productEditSessionObj != null)out.print(productEditSessionObj.getSize());else out.print("DEFAULT");%>" required/>
								<span class="help-block">Select "DEFAULT" if not applicable</span>
							</div>
						</div>
					</div>
					
					<div class="control-group">
						<label for="radio" class="control-label">Product Unit:</label>

						<div class="dropdown controls">
						<button class="btn btn-primary dropdown-toggle" style="width:284px;" type="button" data-toggle="dropdown">Select Unit
							<span class="caret"></span></button>
						<ul class="dropdown-menu" style="width:282px; max-height: 15em; overflow: auto;">
							<li><a onclick="setValueTo('DEFAULT','txtProductUnit');"><span>DEFAULT</span></a></li>
							<%
							UnitService unitServe = new UnitService();
							List<Unit> tblList4 = unitServe.selectAll(mall);
							for (Unit ob : tblList4) {
							%>
							<li><a onclick="setValueTo('<%=ob.getUnit()%>','txtProductUnit');"><span><%=ob.getUnit()%></span></a></li>
							<% } %>
						</ul>
								<a href="add_unit.jsp"><input type="button" style="width:150px;" class="btn btn-primary" value="Add New Unit"/></a>
						</div>

						<div class="control-group">
							<div class="controls">
								<input type="text"  placeholder="Product Unit" name="txtProductUnit" id="txtProductUnit" class="input-xlarge" readonly="readonly" value="<% if(productEditSessionObj != null)out.print(productEditSessionObj.getUnit());else out.print("DEFAULT");%>" required/>
								<span class="help-block">Select "DEFAULT" if not applicable</span>
							</div>
						</div>
					</div>
						
					<div class="control-group">
						<label for="textfield" class="control-label">Product Market Price:</label>
						<div class="controls">
							<input type="text"  placeholder="Product Rate" name="txtProductMarketPrice" id="txtProductMarketPrice" class="input-xlarge" value="<% if(productEditSessionObj != null)out.print(productEditSessionObj.getMp());%>"/>
							<span class="help-block">Leave Blank If Not Knowing</span>
						</div>
					</div>
					
					<div class="control-group">
						<label for="textfield" class="control-label">Product Quantity:</label>
						<div class="controls">
							<input type="text"  placeholder="Product Quantity" name="txtProductQuantity" id="txtProductQuantity" class="input-xlarge" value="<% if(productEditSessionObj != null)out.print(productEditSessionObj.getTotalQuantity()); %>" required/>
							<span class="help-block">Quantity of Product in Mall</span>
						</div>
					</div>

					<div class="control-group">
						<label for="textfield" class="control-label">Threshold Product Quantity:</label>
						<div class="controls">
							<input type="text"  placeholder="Threshold Product Quantity" name="txtThresholdProductQuantity" id="txtThresholdProductQuantity" class="input-xlarge" value="<% if(productEditSessionObj != null)out.print(productEditSessionObj.getThresholdQuantity()); %>" required/>
							<span class="help-block">Minimum Quantity of Product Required in Mall</span>
						</div>
					</div>
						
					<div class="control-group">
						<label for="textfield" class="control-label">Threshold Sale Per Day:</label>
						<div class="controls">
							<input type="text"  placeholder="Threshold Sale Per Day" name="txtThresholdSalePerDay" id="txtThresholdSalePerDay" class="input-xlarge" value="<% if(productEditSessionObj != null)out.print(productEditSessionObj.getThresholdSalePerDay()); %>"/>
							<span class="help-block">Minimum Quantity of Product Required to be Sold per Day</span>
							<span class="help-block">Leave Blank If Not Applicable</span>
						</div>
					</div>
					
					<div class="control-group">
						<label for="textfield" class="control-label">Threshold Sale Per Week:</label>
						<div class="controls">
							<input type="text"  placeholder="Threshold Sale Per Week" name="txtThresholdSalePerWeek" id="txtThresholdSalePerWeek" class="input-xlarge" value="<% if(productEditSessionObj != null)out.print(productEditSessionObj.getThresholdSalePerWeek()); %>"/>
							<span class="help-block">Minimum Quantity of Product Required to be Sold per Week</span>
							<span class="help-block">Leave Blank If Not Applicable</span>
						</div>
					</div>

					<div class="control-group">
						<label for="textfield" class="control-label">Threshold Sale Per Month:</label>
						<div class="controls">
							<input type="text"  placeholder="Threshold Sale Per Month" name="txtThresholdSalePerMonth" id="txtThresholdSalePerMonth" class="input-xlarge" value="<% if(productEditSessionObj != null)out.print(productEditSessionObj.getThresholdSalePerMonth()); %>"/>
							<span class="help-block">Minimum Quantity of Product Required to be Sold per Month</span>
							<span class="help-block">Leave Blank If Not Applicable</span>
						</div>
					</div>
					
					<div class="control-group">
						<label for="textfield" class="control-label">Threshold Sale for 3 Month:</label>
						<div class="controls">
							<input type="text"  placeholder="Threshold Sale for 3 Month" name="txtThresholdSalePerMiniSem" id="txtThresholdSalePerMiniSem" class="input-xlarge" value="<% if(productEditSessionObj != null)out.print(productEditSessionObj.getThresholdSalePerMiniSem()); %>"/>
							<span class="help-block">Minimum Quantity of Product Required to be Sold for 3 Months</span>
							<span class="help-block">Leave Blank If Not Applicable</span>
						</div>
					</div>

					<div class="control-group">
						<label for="textfield" class="control-label">Threshold Sale for 6 Month:</label>
						<div class="controls">
							<input type="text"  placeholder="Threshold Sale for 6 Month" name="txtThresholdSalePerSem" id="txtThresholdSalePerSem" class="input-xlarge" value="<% if(productEditSessionObj != null)out.print(productEditSessionObj.getThresholdSalePerSem()); %>"/>
							<span class="help-block">Minimum Quantity of Product Required to be Sold for 6 Months</span>
							<span class="help-block">Leave Blank If Not Applicable</span>
						</div>
					</div>

					<div class="control-group">
						<label for="textfield" class="control-label">Threshold Sale per Year:</label>
						<div class="controls">
							<input type="text"  placeholder="Threshold Sale per Year:" name="txtThresholdSalePerYear" id="txtThresholdSalePerYear" class="input-xlarge" value="<% if(productEditSessionObj != null)out.print(productEditSessionObj.getThresholdSalePerYear()); %>"/>
							<span class="help-block">Minimum Quantity of Product Required to be Sold per Year</span>
							<span class="help-block">Leave Blank If Not Applicable</span>
						</div>
					</div>

					<div class="control-group">
						<label for="textfield" class="control-label">Expiry Date:</label>
						<div class="controls">
							<input type="radio" id="txtExpiryRadioYes" name="txtExpiryRadio" value="yes" <%if(productEditSessionObj != null){if(!productEditSessionObj.getExpiry().equals("DEFAULT")){%>checked="checked"<%}}%> onclick="displayOn('txtExpiry');"/> Yes
							<input type="radio" id="txtExpiryRadioNo" name="txtExpiryRadio" value="no"  <%if(productEditSessionObj != null){if(productEditSessionObj.getExpiry().equals("DEFAULT")){%>checked="checked"<%}}%> onclick="displayOff('txtExpiry');" /> No
							<br/><br/>
							<%
							LocalDate localDate = LocalDate.now();
					        %>
							<input type="date" style="display:none;" name="txtExpiry" id="txtExpiry" class="input-xlarge" value="<% if(productEditSessionObj != null)out.print(productEditSessionObj.getExpiry());else out.print("null");%>" min="<%=DateTimeFormatter.ofPattern("yyyy").format(localDate)%>-<%=DateTimeFormatter.ofPattern("MM").format(localDate)%>-<%=DateTimeFormatter.ofPattern("dd").format(localDate)%>"/>
							<span class="help-block">Expiry Date for Product</span>
							<%
							if(productEditSessionObj != null){
								if(!productEditSessionObj.getExpiry().equals("DEFAULT")){
									%>
									<script>
									document.getElementById('txtExpiry').setAttribute("style","display:true;");
									</script>
									<%
								}
							}
							%>
						</div>
					</div>

					<div class="control-group">
						<label for="textfield" class="control-label">Add CP:</label>
						<div class="controls">
							<input type="text"  placeholder="Cost Price" name="txtTempCP" id="txtTempCP" class="input-xlarge" value="<% if(productEditSessionObj != null)out.print(productEditSessionObj.getTempCP()); %>" required/>
							<span class="help-block">CP of Product</span>
						</div>
					</div>
					
					<div class="control-group">
						<label for="textfield" class="control-label">Add Marked Up SP:</label>
						<div class="controls">
							<input type="text"  placeholder="Marked Up Sale Price" name="txtTempSP" id="txtTempSP" class="input-xlarge" value="<% if(productEditSessionObj != null)out.print(productEditSessionObj.getTempSP()); %>" required/>
							<span class="help-block">SP of Product</span>
						</div>
					</div>

						<%if(productEditSessionObj==null){%>
						<button type="submit" class="btn btn-primary">Add Product</button>
							<%}
							else{%>
							<script>
							document.getElementById("form").setAttribute("action","ProductChangesServlet");
							</script>
							<button type="submit" class="btn btn-primary"> Apply Changes </button>
						<%}%>
					</form>
				</div>
				
				
				<!--  Table Start   -->
				<%
				productServe = new ProductService();
				productsOfCurrentMall = productServe.getProductsOfCurrentMall(mall);
				%>
				
				<div class="row-fluid">
					<div class="span12" >
						<div class="box box-color box-bordered">
							<div class="box-title">
								<h3>Products</h3>
							</div>
							<div class="box-content nopadding" style="overflow:auto;">
								<table id="example" class="table table-hover table-nomargin table-bordered usertable">
									<thead>
										<tr>
											<th>Id </th>
											<th>Product Number</th>
											<th>Product Company</th>
											<th>Product Name</th>
											<th>Expiry</th>
											<th>Color</th>
											<th>Category</th>
											<th>Size</th>
											<th>Unit</th>
											<th>MP</th>
											<th>Total Quantity</th>
											<th>Threshold Quantity</th>
											<th>Threshold Sale Per Day</th>
											<th>Threshold Sale Per Week</th>
											<th>Threshold Sale Per Month</th>
											<th>Threshold Sale Per Mini Sem</th>
											<th>Threshold Sale Per Sem</th>
											<th>Threshold Sale Per Year</th>
											<th>Profit Till Date</th>
											<th class='hidden-480'>Options</th>
										</tr>
									</thead>
								<tbody>
								<%
								int x=1;
								for (Product ob : productsOfCurrentMall) {
								%>
								<tr>
									<td><%= x++ %></td>
									<td><%= ob.getProductNumber() %></td>
									<td><%= ob.getCompany() %></td>
									<td><%= ob.getProductName() %></td>
									<td><%= ob.getExpiry() %></td>
									<td><%= ob.getColor() %></td>
									<td><%= ob.getCategory() %></td>
									<td><%= ob.getSize() %></td>
									<td><%= ob.getUnit() %></td>
									<td><%= ob.getMp() %></td>
									<td><%= ob.getTotalQuantity() %></td>
									<td><%= ob.getThresholdQuantity() %></td>
									<td><%= ob.getThresholdSalePerDay() %></td>
									<td><%= ob.getThresholdSalePerWeek() %></td>
									<td><%= ob.getThresholdSalePerMonth() %></td>
									<td><%= ob.getThresholdSalePerMiniSem() %></td>
									<td><%= ob.getThresholdSalePerSem() %></td>
									<td><%= ob.getThresholdSalePerYear() %></td>
									<td><%= ob.getProfit()%></td>
									<td class='hidden-480'>
									<center>
										<a href="#" class="btn" rel="tooltip" title="View"><i class="icon-search"></i></a>
										<a href="ProductChangesServlet?id=<%= ob.getId()%>&flag=edit" class="btn" rel="tooltip" title="Edit"><i class="icon-edit"></i></a>
										<a href="ProductChangesServlet?id=<%= ob.getId()%>&flag=delete" class="btn" rel="tooltip" title="Delete"><i class="icon-remove"></i></a>
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