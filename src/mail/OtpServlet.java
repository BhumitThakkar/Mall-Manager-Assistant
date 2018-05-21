// Came from otp.jsp

package mail;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OTPModel;
import service.OTPService;

@WebServlet("/OtpServlet")
public class OtpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    OTPModel otpObj = new OTPModel();
	    OTPService otpServe = new OTPService();
	    int otpFromDatabase;
	    String mailFromSession = (String) request.getSession().getAttribute("OTP mail Session");
	    otpObj.setOtp(Integer.parseInt(request.getParameter("txtOtp")));
	    otpFromDatabase = otpServe.getOtpFromDB(mailFromSession);
	    System.out.println(otpFromDatabase+"-"+otpObj.getOtp());
	    if(otpFromDatabase==otpObj.getOtp())
	    {
	    	otpServe.deleteOtp(mailFromSession);
	    	response.sendRedirect("reset_pass.jsp");
	    }
	    else{
	    	otpServe.deleteOtp(mailFromSession);
	    	response.sendRedirect("login.jsp?passChanged=no");
	    }
	}
}