package mail;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ManagerService;

// came from reset_pass.jsp

/**
 * Servlet implementation class ResetPass
 */
@WebServlet("/ResetPass")
public class ResetPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManagerService managerServe= new ManagerService(); 
	    String mail;
		mail=(String) request.getSession().getAttribute("OTP mail Session");
		String pass = request.getParameter("txtPass");
		System.out.println(pass+" "+mail);
		if(pass!= null && mail != null)
		{
			managerServe.updatePass(pass ,mail);
		request.getSession().removeAttribute("OTP mail Session");
		//Display about password successfully changed.
			response.sendRedirect("login.jsp?passChanged=yes");
		}
		else{
			//update fail
			response.sendRedirect("reset_pass.jsp?fail=1");
		}
	}
}