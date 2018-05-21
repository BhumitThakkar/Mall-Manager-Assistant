package service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.AddProductQuantity;
import model.MallBranchGodown;
import model.Product;
import model.Reminder;
import model.Sale;
import model.Spoil;

public class ProductService {

	public List<Product> getProductsOfCurrentMall(MallBranchGodown mall){
		List<Product> list = null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			list = (List<Product>) session.createQuery("FROM Product where mallBranchGodown.id='"+mall.getId()+"' order by productNumber asc").list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public long insert(Product product,MallBranchGodown mall){
		List<Product> tblList = getProductsOfCurrentMall(mall);
		for(Product tempProduct : tblList)
		{
		if(tempProduct.getProductNumber().equals(product.getProductNumber()))
		return -999;
		}
		long flag = 0;
		try{
		Session session = HibernateUtil.getConnection().openSession();
		Transaction tr = session.beginTransaction();
		if(product.getProfit()==null)
			product.setProfit(0.0);
		
		flag = (long) session.save(product);
		if(flag>0){
			if(product.getMp()<product.getTempSP()){
				Reminder reminder = new Reminder();
				ReminderService reminderServe = new ReminderService();
				reminder.setMallBranchGodown(product.getMallBranchGodown());
				reminder.setProductNumber(product.getProductNumber());
				reminder.setReminderDetails("Product's Sale Price:"+product.getTempSP()+" is more than Market Price"+product.getMp());
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

	public long update(Product product,MallBranchGodown mall, String oldProductNumber){				//mall is just to check weather the product already exists or not
		if(mall !=null){
			if(!product.getProductNumber().equals(oldProductNumber))
			{
				List<Product> tblList = getProductsOfCurrentMall(mall);
				for(Product tempProduct : tblList)
				{
				if(tempProduct.getProductNumber().equals(product.getProductNumber()))
				return -999;
				}
			}
		}
		try{
		Session session = HibernateUtil.getConnection().openSession();
		Transaction tr = session.beginTransaction();
		session.update(product);
		if(product.getMp()<product.getTempSP()){
			Reminder reminder = new Reminder();
			ReminderService reminderServe = new ReminderService();
			reminder.setMallBranchGodown(product.getMallBranchGodown());
			reminder.setProductNumber(product.getProductNumber());
			reminder.setReminderDetails("Product's Sale Price:"+product.getTempSP()+" is more than Market Price"+product.getMp());
			reminder.setStatus('A');
			reminderServe.insert(reminder);
		}
		tr.commit();
		session.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return 1;
	}
	
	public Product selectById(int id){
		Product product = null;
			try {
				Session session = HibernateUtil.getConnection().openSession();
				Transaction tr = session.beginTransaction();
				Query qu = session.createQuery("from Product where id = "+id+"");
				product = (Product) qu.uniqueResult();
				tr.commit();
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return product;
	}
	
	public Product getProductById(int id) {
		Product product = null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction tr = session.beginTransaction();
			Query qu = session.createQuery("from Product where id = "+id+"");
			product = (Product) qu.uniqueResult();
			tr.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	return product;
	}

	
	public void delete(Product product) {
		Session session = HibernateUtil.getConnection().openSession();
		Transaction tr = session.beginTransaction();
		session.delete(product);
		tr.commit();
		session.close();
	}

	public void disable(Product product) {
		Session session = HibernateUtil.getConnection().openSession();
		Transaction tr = session.beginTransaction();
		Query qu = session.createQuery("update Product set status='D' where id = "+product.getId()+"");
		qu.executeUpdate();
		tr.commit();
		session.close();
	}
	
	public void deleteProduct(Product product) {
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(product);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public long updateBySales(Sale sale, MallBranchGodown currentMall) {
	Product product = getProductByMallAndProductNumber(currentMall.getId(),sale.getProductNumber());
	if(product != null){
		if(product.getTotalQuantity()-sale.getSaleQuantity()<0)
			return 0;
		else
			product.setTotalQuantity(product.getTotalQuantity()-sale.getSaleQuantity());
			try{
				Session session = HibernateUtil.getConnection().openSession();
				Transaction transaction = session.beginTransaction();
				update(product,null,null);
				transaction.commit();
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 1;	
	}
	else{
		return -1;
	}
	}

	public Product getProductByMallAndProductNumber(Long id, String productNumber) {
		Product product = null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction tr = session.beginTransaction();
			Query qu = session.createQuery("from Product where mallBranchGodown = "+id+" and productNumber='"+productNumber+"'");
			product = (Product) qu.uniqueResult();
			tr.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	public long updateByAddProductQuantity(AddProductQuantity addProductQuantity, MallBranchGodown currentMall) {
		Product product = getProductByMallAndProductNumber(currentMall.getId(),addProductQuantity.getProductNumber());
		if(product != null){
				product.setTotalQuantity(product.getTotalQuantity()+addProductQuantity.getAddProductQuantity());
				try{
					Session session = HibernateUtil.getConnection().openSession();
					Transaction transaction = session.beginTransaction();
					update(product,null,null);
					transaction.commit();
					session.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return 1;	
		}
		else{
			return -1;
		}
	}

	public long updateBySpoils(Spoil spoil, MallBranchGodown currentMall) {
		Product product = getProductByMallAndProductNumber(currentMall.getId(),spoil.getProductNumber());
		if(product != null){
			if(product.getTotalQuantity()-spoil.getSpoilQuantity()<0)
				return 0;
				else
				product.setTotalQuantity(product.getTotalQuantity()-spoil.getSpoilQuantity());
				try{
					Session session = HibernateUtil.getConnection().openSession();
					Transaction transaction = session.beginTransaction();
					update(product,null,null);
					transaction.commit();
					session.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return 1;	
		}
		else{
			return -1;
		}
	}
}