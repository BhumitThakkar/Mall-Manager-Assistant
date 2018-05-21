<% String check = (String) session.getAttribute("Login Session");
	if(check == null)
	{
		response.sendRedirect("login.jsp");
	}
%>	