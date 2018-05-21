package service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.*;

public class HibernateUtil {

	public static SessionFactory getConnection() {
		return new Configuration().configure().buildSessionFactory();
	}
	
/*Services*/
	public static AddProductQuantityService getAddProductQuantityService(){
		return new AddProductQuantityService();
	}
	
	public static CategoryService getCategoryService(){
		return new CategoryService();
	}
	
	public static CommonService getCommonService(){
		return new CommonService();
	}
	
	public static MallService getMallService(){
		return new MallService();
	}
	
	public static ManagerService getManagerService(){
		return new ManagerService();
	}
	
/*	public static OTPService getOTPService(){
		return new OTPService();
	}*/

	public static ProductService getProductService(){
		return new ProductService();
	}
	
	public static ProfitService getProfitService(){
		return new ProfitService();
	}
	
	public static ReminderService getReminderService(){
		return new ReminderService();
	}
	
	public static SaleService getSaleService(){
		return new SaleService();
	}
	
	public static SizeService getSizeService(){
		return new SizeService();
	}

	public static SpoilService getSpoilService(){
		return new SpoilService();
	}

	public static StatisticService getStatisticService(){
		return new StatisticService();
	}

	public static UnitService getUnitService(){
		return new UnitService();
	}

	/*Models*/
	public static AddProductQuantity getAddProductQuantity(){
		return new AddProductQuantity();
	}
	
	public static Category getCategory(){
		return new Category();
	}
	
	public static Company getCompany(){
		return new Company();
	}
	
	public static MallBranchGodown getMallBranchGodown(){
		return new MallBranchGodown();
	}
	
	public static Manager getManager(){
		return new Manager();
	}
	
	public static Product getProduct(){
		return new Product();
	}
	
	public static Profit getProfit(){
		return new Profit();
	}
	
	public static Reminder getReminder(){
		return new Reminder();
	}
	
	public static Sale getSale(){
		return new Sale();
	}
	
	public static Size getSize(){
		return new Size();
	}
	
	public static Spoil getSpoil(){
		return new Spoil();
	}
	
	public static Unit getUnit(){
		return new Unit();
	}
		
}