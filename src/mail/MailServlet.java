/*
 * Came from login after forget Password with email id in the text box as compulsion.
 * Or from Otp.jsp after resend Otp is clicked
*/
package mail;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Manager;
import model.OTPModel;
import service.ManagerService;
import service.OTPService;
import service.RandomNumber;

@WebServlet("/MailServlet")
public class MailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public int otp ;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean emailMatchFlag = false;
		String resend = request.getParameter("resend");
	    OTPService otpServe = new OTPService();
	    System.out.println(resend);
	    if(resend != null){
	    	if(resend.equals("yes")){
	    		String mailFromSession = (String) request.getSession().getAttribute("OTP mail Session");
		    	System.out.println(mailFromSession);
			    otpServe.deleteOtp(mailFromSession);
			}
	    }
		ManagerService managerServe=new ManagerService();
		String mail = request.getParameter("txtEmailId");
		if(mail == null){									//Needed when otp-Resend
			String mailFromSession = (String) request.getSession().getAttribute("OTP mail Session");
			mail = mailFromSession;
			System.out.println(mail);
		}
		List<Manager> tblList = managerServe.selectAll();
		for(Manager ob : tblList){
			if( mail.equals(ob.getMail_id()) ){
				emailMatchFlag = true;
			}
		}
		if(emailMatchFlag==false)
		{
			request.getSession().setAttribute("Unknown User Error","Wrong Email");
			response.sendRedirect("login.jsp?txtMailId="+mail);
		}
		else{
			otp = RandomNumber.randInt(100000, 999999);
			OTPModel otpObj = new OTPModel();
			otpObj.setMail(mail);
			otpObj.setOtp(otp);
			service.SendMail.main(otpObj.getMail(),otpObj.getOtp(),null);
			request.getSession().setAttribute("OTP mail Session",mail);
			otpServe.insertOTP(otpObj);
			response.sendRedirect("Otp.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}