package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MallBranchGodown;
import model.Product;
import model.Reminder;
import service.ProductService;
import service.ReminderService;

@WebServlet("/QuantityAvailableCheckServlet")
public class QuantityAvailableCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Reminder r = new Reminder();
		ReminderService rs = new ReminderService();
		ProductService ps = new ProductService();
		MallBranchGodown currentMall = (MallBranchGodown) request.getSession().getAttribute("Current Mall");
		List<Reminder> reminders = rs.getRemindersOfCurrentMall(currentMall);
		List<Product> products = ps.getProductsOfCurrentMall(currentMall);
		Date date=new Date();
		Date expiryDate;
		for(Product p : products){
		try {
			expiryDate = new SimpleDateFormat("dd-MM-yyyy").parse(p.getExpiry());
			if(p.getTotalQuantity()<p.getThresholdQuantity()){
				int flag=0;
				for(Reminder reminder : reminders){
					if( (p.getProductNumber().equals(reminder.getProductNumber()) && ("Quantity Less than Minimum".equals(reminder.getReminderCategory())))){
						flag = 1;
						break;
					}
				}
			if(flag == 1)
			{
				r.setMallBranchGodown(currentMall);
				r.setProductNumber(p.getProductNumber());
				r.setReminderCategory("Quantity Less than Minimum");
				r.setReminderDetails("Quantity Left: "+p.getTotalQuantity()+" Must Have Minimum: "+p.getThresholdQuantity());
				r.setStatus('A');
				rs.update(r);
			}
			else{
				r.setMallBranchGodown(currentMall);
				r.setProductNumber(p.getProductNumber());
				r.setReminderCategory("Quantity Less than Minimum");
				r.setReminderDetails("Quantity Left: "+p.getTotalQuantity()+" Must Have Minimum: "+p.getThresholdQuantity());
				r.setStatus('A');
				rs.insert(r);				
			}
			}
			
			
			if( (expiryDate.compareTo(date))<0 ){
				int flag=0;
				for(Reminder reminder : reminders){
					if( (p.getProductNumber().equals(reminder.getProductNumber()) && ("Product Expired".equals(reminder.getReminderCategory())))){
						flag = 2;
						break;
					}
				}
				if(flag == 2)
				{
				r.setMallBranchGodown(currentMall);
				r.setProductNumber(p.getProductNumber());
				r.setReminderCategory("Product Expired");
				r.setReminderDetails("Product Expiry Date: "+p.getExpiry()+" Today Date: "+date);
				r.setStatus('A');
				rs.update(r);
				}
				else{
					r.setMallBranchGodown(currentMall);
					r.setProductNumber(p.getProductNumber());
					r.setReminderCategory("Product Expired");
					r.setReminderDetails("Product Expiry Date: "+p.getExpiry()+" Today Date: "+date);
					r.setStatus('A');
					rs.insert(r);
				}
				}
			
			
			if( (expiryDate.compareTo(date))>=0 && (expiryDate.compareTo(date))<30){
				
				int flag=0;
				for(Reminder reminder : reminders){
					if( (p.getProductNumber().equals(reminder.getProductNumber()) && ("Product Expiring within a month".equals(reminder.getReminderCategory())))){
						flag = 3;
						break;
					}
				}
				if(flag == 3)
				{
				r.setMallBranchGodown(currentMall);
				r.setProductNumber(p.getProductNumber());
				r.setReminderCategory("Product Expiring within a month");
				r.setReminderDetails("Product Expiry Date: "+p.getExpiry()+" Today Date: "+date+" Days left: "+(expiryDate.compareTo(date)));
				r.setStatus('A');
				rs.update(r);
				}
				else{
					r.setMallBranchGodown(currentMall);
					r.setProductNumber(p.getProductNumber());
					r.setReminderCategory("Product Expiring within a month");
					r.setReminderDetails("Product Expiry Date: "+p.getExpiry()+" Today Date: "+date+" Days left: "+(expiryDate.compareTo(date)));
					r.setStatus('A');
					rs.insert(r);	
					}
				}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
		
		response.sendRedirect("reminder.jsp");
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
