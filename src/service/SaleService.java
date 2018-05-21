package service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.AddProductQuantity;
import model.MallBranchGodown;
import model.Product;
import model.Profit;
import model.Sale;

public class SaleService {

	public long insert(Sale sale) {
		long flag = 0;
		try{
		Session session = HibernateUtil.getConnection().openSession();
		Transaction tr = session.beginTransaction();
		flag = (long) session.save(sale);
		if(flag>0){
			ProfitService profitServe = new ProfitService();
			MallBranchGodown mall = sale.getMallBranchGodown();
			List<AddProductQuantity> list = (List<AddProductQuantity>) session.createQuery("FROM AddProductQuantity where mallBranchGodown.id='"+mall.getId()+"' and addProductQuantity>(sold+spoil) and productNumber='"+sale.getProductNumber()+"' order by id").list();
			List<Profit> profitObjFromDatabase = (List<Profit>) session.createQuery("FROM Profit where mallBranchGodown.id='"+mall.getId()+"' and todayDate='"+sale.getSaleDate()+"' and productNumber='"+sale.getProductNumber()+"' order by id").list();
			double newSold=sale.getSaleQuantity();
			AddProductQuantityService quantityServe = new AddProductQuantityService();
			Product product = (Product) session.createQuery("FROM Product where mallBranchGodown.id='"+mall.getId()+"' and productNumber='"+sale.getProductNumber()+"'").uniqueResult();
			ProductService productServe = new ProductService();
			for(AddProductQuantity quantity : list)
			{
				double diff=quantity.getAddProductQuantity()-(quantity.getSold()+quantity.getSpoil());
				if(newSold<=diff)
				{
					quantity.setSold(quantity.getSold()+newSold);
					quantityServe.update(quantity);
					if(profitObjFromDatabase.size()==0){
						Profit profitObj = new Profit();
						profitObj.setMallBranchGodown(mall);
						profitObj.setProductNumber(sale.getProductNumber());
						profitObj.setSp(sale.getSp());
						profitObj.setTodayDate(sale.getSaleDate());
						profitObj.setCp(quantity.getCp());
						profitObj.setSoldQuantity(newSold);
						profitObj.setProfit((profitObj.getSp()-profitObj.getCp())*newSold);

						product.setProfit(product.getProfit() + ((profitObj.getSp()-profitObj.getCp())*newSold));
						productServe.update(product, null, null);

						profitServe.insert(profitObj);
						break;
					}
					else{
						int tempFlag = 0;
						for(Profit ob : profitObjFromDatabase)
						{
							if(sale.getSp()==ob.getSp() && quantity.getCp()==ob.getCp()){
								ob.setSoldQuantity(ob.getSoldQuantity()+newSold);
								ob.setProfit(((ob.getSp()-ob.getCp())*newSold)+ob.getProfit());

								product.setProfit(product.getProfit() + ((ob.getSp()-ob.getCp())*newSold));
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
							if(sale.getSp()!=ob.getSp()){
								ob.setSp(sale.getSp());
								ob.setSoldQuantity(newSold);
								ob.setProfit(((ob.getSp()-ob.getCp())*newSold));

								product.setProfit(product.getProfit() + ((ob.getSp()-ob.getCp())*newSold));
								productServe.update(product, null, null);
								
								profitServe.insert(ob);
								tempFlag = 1;
								break;
							}
						}
						if(tempFlag == 1){
							break;
						}
						for(Profit ob : profitObjFromDatabase)
						{
							if(quantity.getCp()!=ob.getCp()){
								ob.setCp(quantity.getCp());
								ob.setSoldQuantity(newSold);
								ob.setProfit(((ob.getSp()-ob.getCp())*newSold));

								product.setProfit(product.getProfit() + ((ob.getSp()-ob.getCp())*newSold));
								productServe.update(product, null, null);

								profitServe.insert(ob);
								tempFlag = 1;
								break;
							}
						}
						if(tempFlag == 1){
							break;
						}
						for(Profit ob : profitObjFromDatabase)
						{
							ob.setSp(sale.getSp());
							ob.setCp(quantity.getCp());
							ob.setSoldQuantity(newSold);
							ob.setProfit(((ob.getSp()-ob.getCp())*newSold));

							product.setProfit(product.getProfit() + ((ob.getSp()-ob.getCp())*newSold));
							productServe.update(product, null, null);

							profitServe.insert(ob);
							break;
						}
						break;
					}
				}
				else {
					quantity.setSold(quantity.getSold()+diff);
					quantityServe.update(quantity);
					
					if(profitObjFromDatabase.size()==0){
						Profit profitObj = new Profit();
						profitObj.setMallBranchGodown(mall);
						profitObj.setProductNumber(sale.getProductNumber());
						profitObj.setSp(sale.getSp());
						profitObj.setTodayDate(sale.getSaleDate());
						profitObj.setCp(quantity.getCp());
						profitObj.setSoldQuantity(diff);
						profitObj.setProfit((profitObj.getSp()-profitObj.getCp())*diff);

						product.setProfit(product.getProfit() + ((profitObj.getSp()-profitObj.getCp())*diff));
						productServe.update(product, null, null);
						
						profitServe.insert(profitObj);
						newSold = newSold - diff;
						continue;
					}
					else{
						int tempFlag = 0;
						for(Profit ob : profitObjFromDatabase)
						{
							if(sale.getSp()==ob.getSp() && quantity.getCp()==ob.getCp()){
								ob.setSoldQuantity(ob.getSoldQuantity()+diff);
								ob.setProfit(((ob.getSp()-ob.getCp())*diff)+ob.getProfit());

								product.setProfit(product.getProfit() + ((ob.getSp()-ob.getCp())*diff));
								productServe.update(product, null, null);
								
								profitServe.update(ob);
								tempFlag = 1;
								break;
							}
						}
						if(tempFlag == 1){
							newSold = newSold - diff;
							continue;
						}
						for(Profit ob : profitObjFromDatabase)
						{
							if(sale.getSp()!=ob.getSp()){
								ob.setSp(sale.getSp());
								ob.setSoldQuantity(diff);
								ob.setProfit(((ob.getSp()-ob.getCp())*diff));

								product.setProfit(product.getProfit() + ((ob.getSp()-ob.getCp())*diff));
								productServe.update(product, null, null);
								
								profitServe.insert(ob);
								tempFlag = 1;
								break;
							}
						}
						if(tempFlag == 1){
							newSold = newSold - diff;
							continue;
						}
						for(Profit ob : profitObjFromDatabase)
						{
							if(quantity.getCp()!=ob.getCp()){
								ob.setCp(quantity.getCp());
								ob.setSoldQuantity(diff);
								ob.setProfit(((ob.getSp()-ob.getCp())*diff));

								product.setProfit(product.getProfit() + ((ob.getSp()-ob.getCp())*diff));
								productServe.update(product, null, null);

								profitServe.insert(ob);
								tempFlag = 1;
								break;
							}
						}
						if(tempFlag == 1){
							newSold = newSold - diff;	
							continue;
						}
						for(Profit ob : profitObjFromDatabase)
						{
							ob.setSp(sale.getSp());
							ob.setCp(quantity.getCp());
							ob.setSoldQuantity(diff);
							ob.setProfit(((ob.getSp()-ob.getCp())*diff));

							product.setProfit(product.getProfit() + ((ob.getSp()-ob.getCp())*diff));
							productServe.update(product, null, null);

							profitServe.insert(ob);
							break;
						}
						newSold = newSold - diff;						
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
	
	public List<Sale> getSalesOfCurrentMall(MallBranchGodown mall){
		List<Sale> list = null;
		try {
			Session session = HibernateUtil.getConnection().openSession();
			Transaction transaction = session.beginTransaction();
			list = (List<Sale>) session.createQuery("FROM Sale where mallBranchGodown.id='"+mall.getId()+"' order by id desc").list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}