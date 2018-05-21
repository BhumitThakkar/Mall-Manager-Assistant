package service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.AddProductQuantity;
import model.MallBranchGodown;
import model.Sale;

public class StatisticService {
	
	ProductService productServe = new ProductService();
	AddProductQuantityService addProductQuantityServe = new AddProductQuantityService();
	
/*	public double calculateProfitLoss(MallBranchGodown mall){
		double profit = 0;
		double totalCP = 0;
		double totalSP = 0;
		
		List<Sale> salesList=(List<Sale>)mall.getSales();
		for(Sale sale : salesList){
			totalSP = sale.getSaleQuantity()*sale.getSp() + totalSP;
			String productNumber = sale.getProductNumber();
			List<AddProductQuantity> productQuantityList = addProductQuantityServe.getProductQuantityByProductNumber(mall.getId(),productNumber);
			double temp=0;
			for(AddProductQuantity ob : productQuantityList)
			{
				if(sale.getSaleQuantity()>ob.getAddProductQuantity()){
					
				}
			}
			totalCP = sale.getSaleQuantity()*ob.getCp() + totalCP;
			}
			profit = sale.getSp() - totalCP + profit;
		}
		return profit;
	}
*/
	
/*	String getNextDate(String date){
		String nextDate;
		Date dateFormat=new SimpleDateFormat("dd/MM/yyyy").parse(date);
		dateFormat.
		return nextDate;
	}*/
}