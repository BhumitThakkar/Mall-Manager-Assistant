package service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.AddProductQuantity;
import model.MallBranchGodown;
import model.Product;
import model.Reminder;

public class AddProductQuantityService {
	
	public long insert(AddProductQuantity addProductQuantity) {
		long flag = 0;
		try{
		Session session = HibernateUtil.getConnection().openSession();
		Transaction tr = session.beginTransaction();
		flag = (long) session.save(addProductQuantity);
		if(flag>0){
			ProductService productServe = HibernateUtil.getProductService();
			Product	product = productServe.getProductByMallAndProductNumber(addProductQuantity.getMallBranchGodown().getId(), addProductQuantity.getProductNumber());
			if(product.getMp()<addProductQuantity.getSp()){
				Reminder reminder = HibernateUtil.getReminder();
				ReminderService reminderServe = HibernateUtil.getReminderService();
				reminder.setMallBranchGodown(product.getMallBranchGodown());
				reminder.setProductNumber(product.getProductNumber());
				reminder.setReminderDetails("Product's Sale Price:"+addProductQuantity.getSp()+" is more than Market Price"+product.getMp());
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

	public List<AddProductQuantity> getAddedProductQuantitiesOfCurrentMall(MallBranchGodown mall){
		List<AddProductQuantity> list = null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			list = (List<AddProductQuantity>) session.createQuery("FROM AddProductQuantity where mallBranchGodown.id='"+mall.getId()+"' order by id").list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<AddProductQuantity> getProductQuantityByProductNumber(Long id, String productNumber) {
		List<AddProductQuantity> addProductQuantityList = null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction tr = session.beginTransaction();
			Query qu = session.createQuery("from AddProductQuantity where mallBranchGodown.id = "+id+" and productNumber='"+productNumber+"' and sold>0 order by id");
			addProductQuantityList = (List<AddProductQuantity>) qu.list();
			tr.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addProductQuantityList;
	}

	public void insertProductQuantity(MallBranchGodown currentMall, String productNumber, String Date, double quantity, double tempCP,double tempSP) {
		AddProductQuantity addProductQuantity = HibernateUtil.getAddProductQuantity();
		addProductQuantity.setMallBranchGodown(currentMall);
		addProductQuantity.setCp(tempCP);
		addProductQuantity.setSp(tempSP);
		addProductQuantity.setProductEntryDate(Date);
		addProductQuantity.setAddProductQuantity(quantity);
		addProductQuantity.setProductNumber(productNumber);
		addProductQuantity.setSold(0.0);
		addProductQuantity.setSpoil(0.0);
		insert(addProductQuantity);						//see on top 1st method.
	}

	public void update(AddProductQuantity addProductQuantity) {
		try{
		Session session = HibernateUtil.getConnection().openSession();
		Transaction tr = session.beginTransaction();
		session.update(addProductQuantity);
		ProductService productServe = HibernateUtil.getProductService();
		Product	product = productServe.getProductByMallAndProductNumber(addProductQuantity.getMallBranchGodown().getId(), addProductQuantity.getProductNumber());
		if(product.getMp()<addProductQuantity.getSp()){
			Reminder reminder = HibernateUtil.getReminder();
			ReminderService reminderServe = HibernateUtil.getReminderService();
			reminder.setMallBranchGodown(product.getMallBranchGodown());
			reminder.setProductNumber(product.getProductNumber());
			reminder.setReminderDetails("Product's Sale Price:"+addProductQuantity.getSp()+" is more than Market Price"+product.getMp());
			reminder.setStatus('A');
			reminderServe.insert(reminder);
		}
		tr.commit();
		session.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public List<AddProductQuantity> getAddedProductQuantitiesOfGivenProductNumber_Quantity_CP_SP(
			MallBranchGodown mallBranchGodown, Double totalQuantity, String productNumber, Double tempCP,
			Double tempSP) {
		List<AddProductQuantity> addProductQuantityList = null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction tr = session.beginTransaction();
			Query qu = session.createQuery("from AddProductQuantity where mallBranchGodown.id = "+mallBranchGodown.getId()+" and productNumber='"+productNumber+"' and addProductQuantity='"+totalQuantity+"' and cp='"+tempCP+"' and sp='"+tempSP+"' order by id");
			addProductQuantityList = (List<AddProductQuantity>) qu.list();
			tr.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addProductQuantityList;
	}
}