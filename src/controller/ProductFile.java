package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import model.MallBranchGodown;
import model.Product;
import service.AddProductQuantityService;
import service.ProductService;

@WebServlet("/ProductFile")
public class ProductFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private List<String> nonInsertedProductRowsCols=new ArrayList<String>();
	
	public List<String> getNonInsertedProductRowsCols() {
		return nonInsertedProductRowsCols;
	}
	public void setNonInsertedProductRowsCols(List<String> nonInsertedProductRowsCols) {
		this.nonInsertedProductRowsCols = nonInsertedProductRowsCols;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		AddProductQuantityService addProductQuantityServe = new AddProductQuantityService();
		nonInsertedProductRowsCols.clear();
		try {
			LocalDate localDate = LocalDate.now();
			String FilePath = "D://Way2web java/Mall Manager Assistant/WebContent/excel/Add Products.xls";
			FileInputStream fs = new FileInputStream(FilePath);
			Workbook wb = Workbook.getWorkbook(fs);

			// TO get the access to the sheet
			Sheet sh = wb.getSheet("Sheet1");

			// To get the number of rows present in sheet
			int totalNoOfRows = sh.getRows();

			// To get the number of columns present in sheet
			Product product = new Product();
			ProductService productServe = new ProductService();
			MallBranchGodown currentMall = (MallBranchGodown) request.getSession().getAttribute("Current Mall");
			product.setMallBranchGodown(currentMall);
			for (int row = 1; row < totalNoOfRows; row++) {						//Starts from 0, but 1st row is heading so,....
			System.out.println(sh.getCell(0, row).getContents()+"------------------------------------------------------"+row);
			if("".equals(sh.getCell(0, row).getContents()))
			{
				nonInsertedProductRowsCols.add("("+(row+1)+",1)");
				continue;
			}else
			product.setProductNumber(sh.getCell(0, row).getContents());

			if("".equals(sh.getCell(1, row).getContents()))
			{
				nonInsertedProductRowsCols.add("("+(row+1)+",2)");
				continue;
			}else
			product.setCompany(sh.getCell(1, row).getContents());
			
			if("".equals(sh.getCell(2, row).getContents()))
			{
				nonInsertedProductRowsCols.add("("+(row+1)+",3)");
				continue;
			}else
			product.setProductName(sh.getCell(2, row).getContents());
			
			if("".equals(sh.getCell(3, row).getContents()))
			{
				nonInsertedProductRowsCols.add("("+(row+1)+",4)");
				continue;
			}else
			product.setColor(sh.getCell(3, row).getContents());
			
			if("".equals(sh.getCell(4, row).getContents()))
			{
				nonInsertedProductRowsCols.add("("+(row+1)+",5)");
				continue;
			}else
			product.setCategory(sh.getCell(4, row).getContents());
			
			if("".equals(sh.getCell(5, row).getContents()))
			{
				nonInsertedProductRowsCols.add("("+(row+1)+",6)");
				continue;
			}else
			product.setSize(sh.getCell(5, row).getContents());
			
			if("".equals(sh.getCell(6, row).getContents()))
			{
				nonInsertedProductRowsCols.add("("+(row+1)+",7)");
				continue;
			}else
			product.setUnit(sh.getCell(6, row).getContents());

			if("".equals(sh.getCell(7, row).getContents()))
			{
				nonInsertedProductRowsCols.add("("+(row+1)+",8)");
				continue;
			}else
			product.setMp(Double.parseDouble(sh.getCell(7, row).getContents()));
			
			if("".equals(sh.getCell(8, row).getContents()))
			{
				nonInsertedProductRowsCols.add("("+(row+1)+",9)");
				continue;
			}else
			product.setTotalQuantity(Double.parseDouble(sh.getCell(8, row).getContents()));
			if("".equals(sh.getCell(9, row).getContents()))
			{
				nonInsertedProductRowsCols.add("("+(row+1)+",10)");
				continue;
			}else
			product.setThresholdQuantity(Double.parseDouble(sh.getCell(9, row).getContents()));
			
			if("".equals(sh.getCell(10, row).getContents()))
				product.setThresholdSalePerDay(0.0);
			else
			product.setThresholdSalePerDay(Double.parseDouble(sh.getCell(10, row).getContents()));
			
			if("".equals(sh.getCell(11, row).getContents()))
				product.setThresholdSalePerWeek(0.0);
			else
			product.setThresholdSalePerWeek(Double.parseDouble(sh.getCell(11, row).getContents()));
			
			if("".equals(sh.getCell(12, row).getContents()))
				product.setThresholdSalePerMonth(0.0);
			else
			product.setThresholdSalePerMonth(Double.parseDouble(sh.getCell(12, row).getContents()));
			
			if("".equals(sh.getCell(13, row).getContents()))
				product.setThresholdSalePerMiniSem(0.0);
			else
			product.setThresholdSalePerMiniSem(Double.parseDouble(sh.getCell(13, row).getContents()));
			
			if("".equals(sh.getCell(14, row).getContents()))
				product.setThresholdSalePerSem(0.0);
			else
			product.setThresholdSalePerSem(Double.parseDouble(sh.getCell(14, row).getContents()));
			
			if("".equals(sh.getCell(15, row).getContents()))
				product.setThresholdSalePerYear(0.0);
			else
			product.setThresholdSalePerYear(Double.parseDouble(sh.getCell(15, row).getContents()));
			
			if(("".equals(sh.getCell(16, row).getContents())) || ("DEFAULT".equals(sh.getCell(16, row).getContents())))
				product.setExpiry("DEFAULT");
			else
			{
				try {
					Date date=formatter.parse(sh.getCell(16, row).getContents());
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println(sh.getCell(16, row).getContents());
					System.out.println(date);
					System.out.println(localDate);
					System.out.println(date.getYear()+1900+" "+localDate.getYear());
					System.out.println(date.getMonth()+1+" "+localDate.getMonthValue());
					System.out.println(date.getDate()+" "+localDate.getDayOfMonth());
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					if((date.getYear()+1900)<localDate.getYear()){
						nonInsertedProductRowsCols.add("("+(row+1)+",17)--->Expiry Date Can't Be In Past");
						continue;
					}
					else if((date.getYear()+1900)==localDate.getYear()){
						if((date.getMonth()+1)<localDate.getMonthValue()){
							nonInsertedProductRowsCols.add("("+(row+1)+",17)--->Expiry Date Can't Be In Past");
							continue;
						}
						else if((date.getMonth()+1)==localDate.getMonthValue()){
							if(date.getDate()<localDate.getDayOfMonth()){
								nonInsertedProductRowsCols.add("("+(row+1)+",17)--->Expiry Date Can't Be In Past");
								continue;
							}
							else{
								String finalDate=date.getDate()+"-"+(date.getMonth()+1)+"-"+(date.getYear()+1900);
								product.setExpiry(finalDate);
							}
						}
						else{
							String finalDate=date.getDate()+"-"+(date.getMonth()+1)+"-"+(date.getYear()+1900);
							product.setExpiry(finalDate);
						}
					}
					else{
						String finalDate=date.getDate()+"-"+(date.getMonth()+1)+"-"+(date.getYear()+1900);
						product.setExpiry(finalDate);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			if("".equals(sh.getCell(17, row).getContents()))
			{
				nonInsertedProductRowsCols.add("("+(row+1)+",18)");
				continue;
			}
			else
			product.setTempCP(Double.parseDouble(sh.getCell(17, row).getContents()));
			
			if("".equals(sh.getCell(18, row).getContents()))
			{
				nonInsertedProductRowsCols.add("("+(row+1)+",19)");
				continue;
			}
			else
			product.setTempSP(Double.parseDouble(sh.getCell(18, row).getContents()));
			
			if(product.getTempCP()>product.getTempSP()){
				nonInsertedProductRowsCols.add("("+(row+1)+",19) and ("+(row+1)+",18)--->CP should not be more than SP");
				continue;
			}
			
			product.setStatus('A');
			long flag=0;
			flag = productServe.insert(product,currentMall);
			if(flag==-999){
				nonInsertedProductRowsCols.add("("+(row+1)+",1)--->Product Already Exist,can't be Added Again");
				continue;
				}
			else if(flag > 0){
				String finalTodayDate=localDate.getDayOfMonth()+"-"+(localDate.getMonthValue())+"-"+(localDate.getYear());
				addProductQuantityServe.insertProductQuantity(currentMall,sh.getCell(0, row).getContents(),finalTodayDate,(Double.parseDouble(sh.getCell(8, row).getContents())),product.getTempCP(),product.getTempSP());
				}
			}
			if(nonInsertedProductRowsCols.size()==0)
				response.sendRedirect("add_product.jsp?productInsertedSuccessfully=true");
			else{
				request.getSession().setAttribute("ExcelFileProductObjSession",this);
				response.sendRedirect("add_product.jsp?ExcelProductInsertionError=true");
			}
	}
	catch (Exception e) {
		e.printStackTrace();
	}
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}