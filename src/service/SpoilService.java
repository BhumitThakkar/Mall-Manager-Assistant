package service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.AddProductQuantity;
import model.MallBranchGodown;
import model.Product;
import model.Profit;
import model.Spoil;

public class SpoilService {

	public long insert(Spoil spoil) {
		long flag = 0;
		try{
		Session session = HibernateUtil.getConnection().openSession();
		Transaction tr = session.beginTransaction();
		flag = (long) session.save(spoil);
		if(flag>0){
			List<AddProductQuantity> list = null;
			ProfitService profitServe = new ProfitService();
			MallBranchGodown mall = spoil.getMallBranchGodown();
			list = (List<AddProductQuantity>) session.createQuery("FROM AddProductQuantity where mallBranchGodown.id='"+mall.getId()+"' and addProductQuantity>(sold+spoil) and productNumber='"+spoil.getProductNumber()+"' order by id").list();
			double newSpoil=spoil.getSpoilQuantity();
			double profit =0.0;
			AddProductQuantityService quantityServe = new AddProductQuantityService();
			List<Profit> profitObjFromDatabase = (List<Profit>) session.createQuery("FROM Profit where mallBranchGodown.id='"+mall.getId()+"' and todayDate='"+spoil.getSpoilDate()+"' and productNumber='"+spoil.getProductNumber()+"' order by id").list();

			Product product = (Product) session.createQuery("FROM Product where mallBranchGodown.id='"+mall.getId()+"' and productNumber='"+spoil.getProductNumber()+"'").uniqueResult();
			ProductService productServe = new ProductService();

			for(AddProductQuantity quantity : list)
			{
				double diff=quantity.getAddProductQuantity()-(quantity.getSold()+quantity.getSpoil());
				if(newSpoil<=diff)
				{
					quantity.setSpoil(quantity.getSpoil()+newSpoil);
					quantityServe.update(quantity);

					if(profitObjFromDatabase.size()==0){
						Profit profitObj = new Profit();
						profitObj.setMallBranchGodown(mall);
						profitObj.setProductNumber(spoil.getProductNumber());
						profitObj.setSp(0.0);
						profitObj.setTodayDate(spoil.getSpoilDate());
						profitObj.setCp(quantity.getCp());
						profitObj.setSpoilQuantity(newSpoil);
						profitObj.setProfit((profitObj.getSp()-profitObj.getCp())*newSpoil);

						product.setProfit(product.getProfit() + ((profitObj.getSp()-profitObj.getCp())*newSpoil));
						productServe.update(product, null, null);

						profitServe.insert(profitObj);
						break;
					}
					else{
						int tempFlag = 0;
						for(Profit ob : profitObjFromDatabase)
						{
							if(quantity.getCp()==ob.getCp()){
								ob.setSpoilQuantity(ob.getSpoilQuantity()+newSpoil);
								ob.setProfit(((ob.getSp()-ob.getCp())*newSpoil)+ob.getProfit());

								product.setProfit(product.getProfit() + ((ob.getSp()-ob.getCp())*newSpoil));
								productServe.update(product, null, null);

								profitServe.update(ob);
								tempFlag = 1;
								break;
							}
						}
						if(tempFlag == 1){
							break;
						}
						for(Profit ob : profitObjFromDatabase)
						{
							ob.setSp(0.0);
							ob.setCp(quantity.getCp());
							ob.setSpoilQuantity(newSpoil);
							ob.setProfit(((ob.getSp()-ob.getCp())*newSpoil));

							product.setProfit(product.getProfit() + ((ob.getSp()-ob.getCp())*newSpoil));
							productServe.update(product, null, null);

							profitServe.insert(ob);
							break;
						}
						break;
					}
				} else {
					quantity.setSpoil(quantity.getSpoil()+diff);
					quantityServe.update(quantity);
					
					if(profitObjFromDatabase.size()==0){
						Profit profitObj = new Profit();
						profitObj.setMallBranchGodown(mall);
						profitObj.setProductNumber(spoil.getProductNumber());
						profitObj.setSp(0.0);
						profitObj.setTodayDate(spoil.getSpoilDate());
						profitObj.setCp(quantity.getCp());
						profitObj.setSpoilQuantity(diff);
						profitObj.setProfit((profitObj.getSp()-profitObj.getCp())*diff);
						
						product.setProfit(product.getProfit() + ((profitObj.getSp()-profitObj.getCp())*diff));
						productServe.update(product, null, null);

						profitServe.insert(profitObj);
						newSpoil = newSpoil - diff;
						continue;
					}
					else{
						int tempFlag = 0;
						for(Profit ob : profitObjFromDatabase)
						{
							if(quantity.getCp()==ob.getCp()){
								ob.setSpoilQuantity(ob.getSpoilQuantity()+diff);
								ob.setProfit(((ob.getSp()-ob.getCp())*diff)+ob.getProfit());

								product.setProfit(product.getProfit() + ((ob.getSp()-ob.getCp())*diff));
								productServe.update(product, null, null);

								profitServe.update(ob);
								tempFlag = 1;
								break;
							}
						}
						if(tempFlag == 1){
							newSpoil = newSpoil - diff;
							continue;
						}
						for(Profit ob : profitObjFromDatabase)
						{
							ob.setSp(0.0);
							ob.setCp(quantity.getCp());
							ob.setSpoilQuantity(diff);
							ob.setProfit(((ob.getSp()-ob.getCp())*diff));

							product.setProfit(product.getProfit() + ((ob.getSp()-ob.getCp())*diff));
							productServe.update(product, null, null);

							profitServe.insert(ob);
							break;
						}
						newSpoil = newSpoil - diff;						
						continue;
					}
				}
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
	
	public List<Spoil> getSpoilsOfCurrentMall(MallBranchGodown mall){
		List<Spoil> list = null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			list = (List<Spoil>) session.createQuery("FROM Spoil where mallBranchGodown.id='"+mall.getId()+"' order by spoilDate desc").list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}