package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.MallBranchGodown;
import model.Manager;

public class ManagerService {

	public long checkMailExistancy(Manager ManagerObj) {
		List<Manager> tblList = selectAll();
		if (tblList != null) {
			for (Manager ob : tblList) {
				if (ManagerObj.getMail_id().equals(ob.getMail_id())) {
					return (long) 1;
				}
			}
		} else {
			return (long) 0;
		}
		return (long) 0;
	}

	public long checkMailExistancyForChanges(Manager ManagerObj,String oldMail) {
		List<Manager> tblList = selectAll();
		if (tblList != null) {
			for (Manager ob : tblList) {
				if (ManagerObj.getMail_id().equals(ob.getMail_id())  && !ManagerObj.getMail_id().equals(oldMail)) {
					/*System.out.println("Locha");*/
					return (long) 1;
				}
			}
		} else {
			return (long) 0;
		}
		return (long) 0;
	}


	public List<Manager> selectAll() {
		List<Manager> list = null;
		try {
			Session s = HibernateUtil.getConnection().openSession();
			Transaction tr = s.beginTransaction();
			Query qu = s.createQuery("from Manager");
			list = (ArrayList<Manager>) qu.list();
			tr.commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public long insert(Manager ManagerObj) {
		long i = 0;
		List<Manager> tblList = selectAll();
		for (Manager ob : tblList) {
			if (ManagerObj.getMail_id().equals(ob.getMail_id())) {
				return -1;
			}
		}
		try {
			Session s = HibernateUtil.getConnection().openSession();
			Transaction t = s.beginTransaction();
			i = (long) (s.save(ManagerObj));
			t.commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public Manager getManagerByEmail(String currentLogin) {
		Manager manager = null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			manager = (Manager) session.createQuery("FROM Manager where mail_id ='"+currentLogin+"'").uniqueResult();		//Create Criteria instead of create Query is easy
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return manager;
	}
	
	public void updatePass(String Pass, String mail) {
		Manager manager = null;
		Session session = HibernateUtil.getConnection().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query qu = session.createQuery("from Manager where mail_id = '"+mail+"'");
			manager = (Manager) qu.uniqueResult();
			if(manager != null){
				manager.setPassword(Pass);
				session.update(manager);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public List<Manager> selectManagersOfCurrentMall(MallBranchGodown mall){
		List<Manager> list = new ArrayList(mall.getManagers());
		return list;
	}

	public void updateManager(Manager manager) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session s = factory.openSession();
		Transaction tr = s.beginTransaction();
		s.update(manager);
		tr.commit();
		s.close();
	}

	public void deteteManager(Manager manager) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session s = factory.openSession();
		Transaction tr = s.beginTransaction();
		s.delete(manager);
		tr.commit();
		s.close();
	}

	public long resignSelectedMall(Manager manager, MallBranchGodown mall) {
		long flag=0;
		try{
			/* step 1 */	Class.forName("com.mysql.jdbc.Driver");
			/* step 2 */	Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			/* step 3 */	Statement st = cn.createStatement();
			/* step 4 */	flag = st.executeUpdate("delete from manager_mall where manager_id='"+manager.getId()+"' and mall_branch_godown_id='"+mall.getId()+"'");
			/* step 5 */	cn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

}