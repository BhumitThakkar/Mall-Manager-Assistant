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
import model.Sale;
import service.ProductService;
import service.SaleService;

@WebServlet("/SalesFile")
public class SalesFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private List<String> nonInsertedSalesRowsCols=new ArrayList<String>();

	public List<String> getNonInsertedSalesRowsCols() {
		return nonInsertedSalesRowsCols;
	}

	public void setNonInsertedSalesRowsCols(List<String> nonInsertedSalesRowsCols) {
		this.nonInsertedSalesRowsCols = nonInsertedSalesRowsCols;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		ProductService productServe = new ProductService();
		nonInsertedSalesRowsCols.clear();
		try {
			String FilePath = "D://Way2web java/Mall Manager Assistant/WebContent/excel/Add Sales.xls";
			FileInputStream fs = new FileInputStream(FilePath);
			Workbook wb = Workbook.getWorkbook(fs);

			// TO get the access to the sheet
			Sheet sh = wb.getSheet("Sheet1");

			// To get the number of rows present in sheet
			int totalNoOfRows = sh.getRows();

			// To get the number of columns present in sheet
			Sale sale = new Sale();
			SaleService saleServe = new SaleService();
			MallBranchGodown currentMall = (MallBranchGodown) request.getSession().getAttribute("Current Mall");
			sale.setMallBranchGodown(currentMall);

			for (int row = 1; row < totalNoOfRows; row++) {						//Starts from 0, but 1st row is heading so,....

/*				System.gc();        */

			if("".equals(sh.getCell(0, row).getContents()))
			{
				nonInsertedSalesRowsCols.add("("+(row+1)+",1)");
				continue;
			}else
			sale.setProductNumber(sh.getCell(0, row).getContents());

			if("".equals(sh.getCell(1, row).getContents()))
			{
				nonInsertedSalesRowsCols.add("("+(row+1)+",2)");
				continue;
			}else
			{
				try {
					Date date=formatter.parse(sh.getCell(1, row).getContents());
					LocalDate localDate = LocalDate.now();
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println(sh.getCell(1, row).getContents());
					System.out.println(date);
					System.out.println(date.getYear()+1900+" "+localDate.getYear());
					System.out.println(date.getMonth()+1+" "+localDate.getMonthValue());
					System.out.println(date.getDate()+" "+localDate.getDayOfMonth());
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					if((date.getYear()+1900)>localDate.getYear()){
						nonInsertedSalesRowsCols.add("("+(row+1)+",2)--->Sales Date Can't Be In Future");
						continue;
					}
					else if((date.getYear()+1900)==localDate.getYear()){
						if((date.getMonth()+1)>localDate.getMonthValue()){
							nonInsertedSalesRowsCols.add("("+(row+1)+",2)--->Sales Date Can't Be In Future");
							continue;
						}
						else if((date.getMonth()+1)==localDate.getMonthValue()){
							if(date.getDate()>localDate.getDayOfMonth()){
								nonInsertedSalesRowsCols.add("("+(row+1)+",2)--->Sales Date Can't Be In Future");
								continue;
							}
							else{
								String finalDate=date.getDate()+"-"+(date.getMonth()+1)+"-"+(date.getYear()+1900);
								sale.setSaleDate(finalDate);								
							}
						}
						else{
							String finalDate=date.getDate()+"-"+(date.getMonth()+1)+"-"+(date.getYear()+1900);
							sale.setSaleDate(finalDate);
						}
					}
					else{
						String finalDate=date.getDate()+"-"+(date.getMonth()+1)+"-"+(date.getYear()+1900);
						sale.setSaleDate(finalDate);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			if("".equals(sh.getCell(2, row).getContents())){
				nonInsertedSalesRowsCols.add("("+(row+1)+",3)");
				continue;
			}
			else if(Double.parseDouble(sh.getCell(2, row).getContents())<=0){
				nonInsertedSalesRowsCols.add("("+(row+1)+",3)---> Can't be 0 or less");
				continue;
			}
			else
			sale.setSaleQuantity(Double.parseDouble(sh.getCell(2, row).getContents()));
			
			if("".equals(sh.getCell(3, row).getContents()))
			{
				nonInsertedSalesRowsCols.add("("+(row+1)+",4)");
				continue;
			}else
			sale.setSp(Double.parseDouble(sh.getCell(3, row).getContents()));
			
			long flag=-2;
			flag = productServe.updateBySales(sale,currentMall);
			if(flag==0){
				nonInsertedSalesRowsCols.add("("+(row+1)+",3)-->Cant Sale More Quantity Than Existing");
				continue;
			}
			else if(flag==-1){
				nonInsertedSalesRowsCols.add("("+(row+1)+",1)-->Unknown Product Cant Be Sold");
				continue;
			}
			else if(flag==1)
			saleServe.insert(sale);
		}

		if(nonInsertedSalesRowsCols.size()==0)
			response.sendRedirect("sales.jsp?saleInsertedSuccessfully=true");
		else
			{
			request.getSession().setAttribute("ExcelFileSaleObjSession",this);
			response.sendRedirect("sales.jsp?ExcelSaleInsertionError=true");
			}
	}
	catch (Exception e) {
		e.printStackTrace();
	}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}