package service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.MallBranchGodown;
import model.Unit;

public class UnitService {
	
	public List<Unit> selectAll(MallBranchGodown mall) {
		List<Unit> list = null;
		try {
			Session s = HibernateUtil.getConnection().openSession();
			Transaction tr = s.beginTransaction();
			Query qu = s.createQuery("from Unit where mallBranchGodown.id='"+mall.getId()+"' order by unit asc ");
			list = (ArrayList<Unit>) qu.list();
			tr.commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Unit> getUnitsOfCurrentMall(MallBranchGodown mall){
		List<Unit> list = null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			list = (List<Unit>) session.createQuery("FROM Unit where mallBranchGodown.id='"+mall.getId()+"' order by unit asc").list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public long insert(Unit unit) {
		long flag = 0;
		try{
		Session session = HibernateUtil.getConnection().openSession();
		Transaction tr = session.beginTransaction();
		flag = (long) session.save(unit);
		tr.commit();
		session.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	public Unit getUnitById(int id) {
		Unit unit= null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction tr = session.beginTransaction();
			Query qu = session.createQuery("from Unit where id = "+id+"");
			unit = (Unit) qu.uniqueResult();
			tr.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	return unit;
	}

	public void deleteUnit(Unit unit) {
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(unit);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Unit unit) {
		try{
		Session session = HibernateUtil.getConnection().openSession();
		Transaction tr = session.beginTransaction();
		session.update(unit);
		tr.commit();
		session.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}