package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.MallBranchGodown;
import model.Manager;

public class CommonService {
	
	public List<MallBranchGodown> sortMallBranchGodownList(List<MallBranchGodown> list){
		int i = 0,j = 0;
		 for (i = 0; i < ( list.size() - 1 ); i++) {
		      for (j = 0; j < list.size() - i - 1; j++) {
		    if (list.get(j).getId() > list.get(j+1).getId())
		        {
		    		MallBranchGodown temp;
		           temp = list.get(j);
		           list.set(j, list.get(j+1));
		           list.set(j+1, temp);
		        }
		      }
		    }
		return list;
	}
	
	public long insertThroughManager(MallBranchGodown mall, Manager manager) {		
		manager.getMallBranchGodowns().add(mall);
		long flag=0;
		try {
			Session s = HibernateUtil.getConnection().openSession();
			Transaction tr = s.beginTransaction();
			flag = (long) s.save(manager);
			tr.commit();
			s.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
			return flag;
	}

	public long insertCommon(MallBranchGodown mall, Manager manager,HttpServletRequest request,HttpServletResponse response) {
		long flag=0;
		try{
			/* step 1 */	Class.forName("com.mysql.jdbc.Driver");
			/* step 2 */	Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			/* step 3 */	Statement st = cn.createStatement();
			/* step 4 */	int a = st.executeUpdate("insert into manager_mall (manager_id, mall_branch_godown_id) values ("+manager.getId()+","+mall.getId()+")");
			if(a==0){
				request.getSession().setAttribute("Insertion Error","There is some error in insertion");	
				response.sendRedirect("signup.jsp");
			}
			/* step 5 */	cn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	public long insertThroughMall(MallBranchGodown mall, Manager manager) {
		mall.getManagers().add(manager);
		long flag=0;
		try {
			Session s = HibernateUtil.getConnection().openSession();
			Transaction tr = s.beginTransaction();
			flag = (long) s.save(mall);
			tr.commit();
			s.close();							// this statement was commented before
		}
		catch (Exception e) {
			e.printStackTrace();
		}
			return flag;
	}
}