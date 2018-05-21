package service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Company;
import model.MallBranchGodown;

public class CompanyService {

	public List<Company> selectAll(MallBranchGodown mall) {
		List<Company> list = null;
		try {
			Session s = HibernateUtil.getConnection().openSession();
			Transaction tr = s.beginTransaction();
			Query qu = s.createQuery("from Company where mallBranchGodown.id='"+mall.getId()+"' order by company asc");		//order by comes after where token
			list = (ArrayList<Company>) qu.list();
			tr.commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Company> getCompaniesOfCurrentMall(MallBranchGodown mall){
		List<Company> list = null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			list = (List<Company>) session.createQuery("FROM Company where mallBranchGodown.id='"+mall.getId()+"' order by company asc ").list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public long insert(Company company) {
		long flag = 0;
		try{
		Session session = HibernateUtil.getConnection().openSession();
		Transaction tr = session.beginTransaction();
		flag = (long) session.save(company);
		tr.commit();
		session.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	public Company getCompanyById(int id) {
		Company company = null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction tr = session.beginTransaction();
			Query qu = session.createQuery("from Company where id = "+id+"");
			company = (Company) qu.uniqueResult();
			tr.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	return company;
	}

	public void deleteCompany(Company company) {
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(company);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void update(Company company) {
		try{
		Session session = HibernateUtil.getConnection().openSession();
		Transaction tr = session.beginTransaction();
		session.update(company);
		tr.commit();
		session.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	
}