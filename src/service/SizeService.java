package service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.MallBranchGodown;
import model.Size;

public class SizeService {

	public List<Size> selectAll(MallBranchGodown mall) {
		List<Size> list = null;
		try {
			Session s = HibernateUtil.getConnection().openSession();
			Transaction tr = s.beginTransaction();
			Query qu = s.createQuery("from Size where mallBranchGodown.id='"+mall.getId()+"' order by size asc");
			list = (ArrayList<Size>) qu.list();
			tr.commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Size> getSizesOfCurrentMall(MallBranchGodown mall){
		List<Size> list = null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			list = (List<Size>) session.createQuery("FROM Size where mallBranchGodown.id='"+mall.getId()+"' order by size asc").list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public long insert(Size size) {
		long flag = 0;
		try{
		Session session = HibernateUtil.getConnection().openSession();
		Transaction tr = session.beginTransaction();
		flag = (long) session.save(size);
		tr.commit();
		session.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	public Size getSizeById(int id) {
		Size size= null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction tr = session.beginTransaction();
			Query qu = session.createQuery("from Size where id = "+id+"");
			size = (Size) qu.uniqueResult();
			tr.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	return size;
	}

	public void deleteUnit(Size size) {
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(size);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Size size) {
		try{
			Session session = HibernateUtil.getConnection().openSession();
			Transaction tr = session.beginTransaction();
			session.update(size);
			tr.commit();
			session.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}		
	}
}