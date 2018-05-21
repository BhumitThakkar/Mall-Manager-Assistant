package service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Category;
import model.MallBranchGodown;

public class CategoryService {

	public List<Category> selectAll(MallBranchGodown mall) {
		List<Category> list = null;
		try {
			Session s = HibernateUtil.getConnection().openSession();
			Transaction tr = s.beginTransaction();
			Query qu = s.createQuery("from Category where mallBranchGodown.id='"+mall.getId()+"' order by category asc ");		//order by comes after where token
			list = (ArrayList<Category>) qu.list();
			tr.commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Category> getCategoriesOfCurrentMall(MallBranchGodown mall){
		List<Category> list = null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			list = (List<Category>) session.createQuery("FROM Category where mallBranchGodown.id='"+mall.getId()+"' order by category asc ").list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public long insert(Category category) {
		long flag = 0;
		try{
		Session session = HibernateUtil.getConnection().openSession();
		Transaction tr = session.beginTransaction();
		flag = (long) session.save(category);
		tr.commit();
		session.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	public Category getCategoryById(int id) {
		Category category = null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction tr = session.beginTransaction();
			Query qu = session.createQuery("from Category where id = "+id+"");
			category = (Category) qu.uniqueResult();
			tr.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	return category;
	}

	public void deleteCategory(Category category) {
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(category);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Category category) {
		try{
		Session session = HibernateUtil.getConnection().openSession();
		Transaction tr = session.beginTransaction();
		session.update(category);
		tr.commit();
		session.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}