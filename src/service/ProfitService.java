package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.MallBranchGodown;
import model.Profit;
import model.Reminder;

public class ProfitService {

	public List<Profit> getProfitsOfCurrentMall(MallBranchGodown mall){
		List<Profit> list = null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			list = (List<Profit>) session.createQuery("FROM Profit where mallBranchGodown.id='"+mall.getId()+"' order by id desc").list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Profit> getProfitsOfProduct(MallBranchGodown mall,String productNumber){
		List<Profit> list = null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			list = (List<Profit>) session.createQuery("FROM Profit where mallBranchGodown.id='"+mall.getId()+"' and productNumber='"+productNumber+"' order by id desc").list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Profit> 	getProfitsByDate(MallBranchGodown mall,String fromDate, String toDate){
		List<Profit> list = null;
		Date from;
		Date to;
		/*String finalDate=date.getDate()+"-"+(date.getMonth()+1)+"-"+(date.getYear()+1900);*/
		try {
			from = new SimpleDateFormat("dd-MM-yyyy").parse(fromDate);
			to = new SimpleDateFormat("dd-MM-yyyy").parse(toDate);
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			list = (List<Profit>) session.createQuery("FROM Profit where mallBranchGodown.id='"+mall.getId()+"' order by id desc").list();
			List<Profit> removeList = new ArrayList<Profit>();
			removeList.clear();
			for(Profit ob : list){
				System.out.println(list.size()+"------------------------------->57 ProfitService");
				Date obDate = new SimpleDateFormat("dd-MM-yyyy").parse(ob.getTodayDate());
				System.out.println(obDate+" is before "+from+"-->"+obDate.before(from)+"------------------------------->59 ProfitService");
				System.out.println(obDate+" is after "+to+"-->"+obDate.after(to)+"------------------------------->60 ProfitService");
				if( (obDate.before(from)) || (obDate.after(to)) ){
					removeList.add(ob);
					System.out.println(list.size()+"------------------------------->63 ProfitService");
					continue;
				}
			}
			list.removeAll(removeList);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public long insert(Profit profit){
		long flag = 0;
		try{
		Session session = HibernateUtil.getConnection().openSession();
		Transaction tr = session.beginTransaction();
		if(profit.getSpoilQuantity()==null){
		profit.setSpoilQuantity(0.0);	
		}
		else if(profit.getSoldQuantity()==null){
		profit.setSoldQuantity(0.0);				
		}
		flag = (long) session.save(profit);
		if(flag>0){
			if(profit.getProfit()<=0){
				ReminderService reminderServe = new ReminderService();
				Reminder reminder = new Reminder();
				reminder.setMallBranchGodown(profit.getMallBranchGodown());
				reminder.setProductNumber(profit.getProductNumber());
				if(profit.getProfit()<0)
					reminder.setReminderDetails("Product: "+profit.getProductNumber()+" is Making Loss of: "+profit.getProfit()+" on date: "+profit.getTodayDate());
				else
					reminder.setReminderDetails("Product: "+profit.getProductNumber()+" is Making no Loss no Profit on date: "+profit.getTodayDate());
				reminder.setStatus('A');
				reminderServe.insert(reminder);
			}
		}
		tr.commit();
		session.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	public void update(Profit profitObj) {
		try{
		Session session = HibernateUtil.getConnection().openSession();
		Transaction tr = session.beginTransaction();
		session.update(profitObj);
		tr.commit();
		session.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	
}