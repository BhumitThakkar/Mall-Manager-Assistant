package service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.MallBranchGodown;
import model.Product;
import model.Reminder;

public class ReminderService {

	public Reminder getReminderById(int id){
		Reminder reminder = null;
			try {
				Session session = HibernateUtil.getConnection().openSession();
				Transaction tr = session.beginTransaction();
				Query qu = session.createQuery("from Reminder where id = "+id+"");
				reminder = (Reminder) qu.uniqueResult();
				tr.commit();
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return reminder;
	}

	public List<Reminder> getRemindersOfCurrentMall(MallBranchGodown mall){
		List<Reminder> list = null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			list = (List<Reminder>) session.createQuery("FROM Reminder where mallBranchGodown.id='"+mall.getId()+"' order by id desc").list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public long insert(Reminder reminder){		
		long flag = 0;
		try{
		Session session = HibernateUtil.getConnection().openSession();
		Transaction tr = session.beginTransaction();
		flag = (long) session.save(reminder);
		tr.commit();
		session.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	public void update(Reminder reminder){
		try{
		Session session = HibernateUtil.getConnection().openSession();
		Transaction tr = session.beginTransaction();
		session.update(reminder);
		tr.commit();
		session.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void delete(Reminder reminder){
		try{
		Session session = HibernateUtil.getConnection().openSession();
		Transaction tr = session.beginTransaction();
		session.delete(reminder);
		tr.commit();
		session.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
