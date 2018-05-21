package service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.OTPModel;

public class OTPService {

	
	public void insertOTP(OTPModel OTPObj) {
		// TODO Auto-generated method stub
		try{
			Session s = HibernateUtil.getConnection().openSession();
			Transaction t = s.beginTransaction();
			s.save(OTPObj);
			t.commit();
			s.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	public int getOtpFromDB(String mailFromSession) {
		int otpFromDB = 0;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			OTPModel otpModelObj = (OTPModel) session.createQuery("FROM OTPModel where mail='"+mailFromSession+"'").uniqueResult();		//Create Criteria instead of create Query is easy
			System.out.println(otpModelObj.getOtp());
			otpFromDB = otpModelObj.getOtp();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return otpFromDB;
	}

	public void deleteOtp(String otpMail) {
		OTPModel otpModel = null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			otpModel = (OTPModel) session.createQuery("FROM OTPModel where mail='"+otpMail+"'").uniqueResult();		//Create Criteria instead of create Query is easy			
			session.delete(otpModel);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
