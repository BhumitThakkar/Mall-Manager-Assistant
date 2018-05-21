package service;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	public static void main(String s,int otp,String[] args) {
		final String fromEmail = "bhumit13@gecg28.ac.in";
		final String password = "bhumit123";
		Properties props = new Properties();
		
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		
		Session session = Session.getDefaultInstance(props,new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(fromEmail,password);
			}
		});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
		    message.addRecipient(Message.RecipientType.TO,new InternetAddress(s));
		    message.setSubject("OTP");
		    message.setText("OTP: "+otp);
		    //send the message
		     Transport.send(message);
		     System.out.println("message sent successfully...");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}