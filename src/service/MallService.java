package service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.MallBranchGodown;
import model.Manager;

public class MallService {
	
	public List<MallBranchGodown> selectAll() {
		List<MallBranchGodown> list = null;
		try {
			Session s = HibernateUtil.getConnection().openSession();
			Transaction tr = s.beginTransaction();
			Query qu = s.createQuery("from MallBranchGodown group by mall_name order by mall_name asc");
			list = (ArrayList<MallBranchGodown>) qu.list();
			tr.commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public long insert(MallBranchGodown MallObj) {
		long i = 0;
		try {
			Session s = HibernateUtil.getConnection().openSession();
			Transaction t = s.beginTransaction();
			i = (long) (s.save(MallObj));
			t.commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public List<MallBranchGodown> selectMallsOfCurrentManager(Manager manager){
		List<MallBranchGodown> list = new ArrayList(manager.getMallBranchGodowns());
			CommonService commonServe = new CommonService();
			commonServe.sortMallBranchGodownList(list);
			return list;
	}

	public String getMallCategory(String m_b_g_mb){
		String categoryDetails=null;
		if(m_b_g_mb == "M"){
			categoryDetails = "Mall";
		}
		else if(m_b_g_mb.equals("B")){
			categoryDetails = "Branch";
		}
		else if(m_b_g_mb.equals("G")){
			categoryDetails = "Godown";			
		}
		else if(m_b_g_mb.equals("MB")){
			categoryDetails = "Mall&Branch";
		}
		else{
			categoryDetails=null;
		}
		return categoryDetails;
	}
	
	public MallBranchGodown getCurrentMall(String mallName,String mallPin,String mallCat){
		MallBranchGodown currentMall = null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			currentMall = (MallBranchGodown) session.createQuery("FROM MallBranchGodown where mall_name='"+mallName+"' AND pincode="+Long.parseLong(mallPin)+" AND m_b_g_mb='"+mallCat+"'").uniqueResult();		//Create Criteria instead of create Query is easy
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentMall;
	}
	
	public List<Manager> getColleaguesOfCurrentManager(Manager manager, MallBranchGodown mall){
		List<Manager> groupManagers =  new ArrayList<Manager>();
		groupManagers.clear();
		List<MallBranchGodown> managerMallList = new ArrayList(manager.getMallBranchGodowns());
		List<Manager> currentMallManagers = new ArrayList(mall.getManagers());
		List<MallBranchGodown> mallsByName = null;
		for(MallBranchGodown ob : managerMallList){
			MallService mallServe = new MallService();
			mallsByName = mallServe.getMallsByName(ob.getMall_name());
		}
		for(MallBranchGodown ob : mallsByName){
		List<Manager> mallManagerList = new ArrayList(ob.getManagers());
			for(Manager ob1 : mallManagerList){
				int flag = 0;
				for(Manager ob2 : currentMallManagers){
					if(ob2.getMail_id().equals(ob1.getMail_id())){
						flag = 1;
					}
				}
				if(flag == 1){
					// Do not insert
				}
				else{
					for(Manager m : groupManagers)
					if(ob1.getMail_id().equals(m.getMail_id()))
					flag=1;
					if(flag!=1)
					groupManagers.add(ob1);
				}
			}
		}
		return groupManagers;
	}

	private List<MallBranchGodown> getMallsByName(String mallName) {
		List<MallBranchGodown> mallsByName = new ArrayList<MallBranchGodown>();
		mallsByName.clear();
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			mallsByName =(List<MallBranchGodown>) session.createQuery("FROM MallBranchGodown where mall_name='"+mallName+"'").list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mallsByName;
	}

	public MallBranchGodown getMallById(Long mallId) {
		MallBranchGodown mall = null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			mall =(MallBranchGodown) session.createQuery("FROM MallBranchGodown where id="+mallId).uniqueResult();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mall;
	}

	public void DeleteMall(MallBranchGodown mall) {
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(mall);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}