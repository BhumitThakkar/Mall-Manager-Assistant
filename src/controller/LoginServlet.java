package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Manager;
import service.ManagerService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ManagerService managerServe = new ManagerService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean emailMatchFlag = false;
		List<Manager> tblList = managerServe.selectAll();
		String mail=request.getParameter("txtMailId");
		String pass=request.getParameter("txtPassword");
		for(Manager ob : tblList){
			if( mail.equals(ob.getMail_id()) ){
				emailMatchFlag = true;
				if(pass.equals(ob.getPassword())){

					if("checked".equals(request.getParameter("remember"))){
						// Add cookie of remember password if does not exist.
						Cookie cookieArray[] = request.getCookies();
						Cookie cookie1 = null;
						if (cookieArray != null) {
							for (int i = cookieArray.length; i > 0; i--) {
								if (cookieArray[i-1].getName().equals(mail) && cookieArray[i-1].getValue().equals(pass)) {
									//Already added so do nothing.
								}
								else{
									cookie1 = new Cookie(mail, pass);
									cookie1.setMaxAge(60*60*24*365*5);
									response.addCookie(cookie1);
								}
							}
						}
					}
					request.getSession().setAttribute("Login Session",mail);				// remove session at log out
					response.sendRedirect("index.jsp");											//welcome page
				}
				else{
					request.getSession().setAttribute("Pass Error","Wrong Password");
					response.sendRedirect("login.jsp?txtMailId="+mail);
				}
			}
		}
		if(emailMatchFlag != true)
		{
			request.getSession().setAttribute("Unknown User Error","Wrong Email");
			response.sendRedirect("login.jsp?txtMailId="+mail);
		}
	}
}