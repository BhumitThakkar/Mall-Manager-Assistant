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
import model.AddProductQuantity;
import model.MallBranchGodown;
import service.AddProductQuantityService;
import service.ProductService;

@WebServlet("/AddProductQuantityFile")
public class AddProductQuantityFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private List<String> nonInsertedProductQuantityRowsCols=new ArrayList<String>();
	ProductService productServe = new ProductService();
	
	public List<String> getNonInsertedProductQuantityRowsCols() {
		return nonInsertedProductQuantityRowsCols;
	}

	public void setNonInsertedProductQuantityRowsCols(List<String> nonInsertedProductQuantityRowsCols) {
		this.nonInsertedProductQuantityRowsCols = nonInsertedProductQuantityRowsCols;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		nonInsertedProductQuantityRowsCols.clear();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		try {
			String FilePath = "D://Way2web java/Mall Manager Assistant/WebContent/excel/Add Product Quantity.xls";
			FileInputStream fs = new FileInputStream(FilePath);
			Workbook wb = Workbook.getWorkbook(fs);

			// TO get the access to the sheet
			Sheet sh = wb.getSheet("Sheet1");

			// To get the number of rows present in sheet
			int totalNoOfRows = sh.getRows();

			// To get the number of columns present in sheet
			AddProductQuantity addProductQuantity = new AddProductQuantity();
			AddProductQuantityService addProductQuantityServe = new AddProductQuantityService();
			MallBranchGodown currentMall = (MallBranchGodown) request.getSession().getAttribute("Current Mall");
			addProductQuantity.setMallBranchGodown(currentMall);

			for (int row = 1; row < totalNoOfRows; row++) {						//Starts from 0, but 1st row is heading so,....

			if("".equals(sh.getCell(0, row).getContents()))
			{
				nonInsertedProductQuantityRowsCols.add("("+(row+1)+",1)");
				continue;
			}else
				addProductQuantity.setProductNumber(sh.getCell(0, row).getContents());

			if("".equals(sh.getCell(1, row).getContents()))
			{
				nonInsertedProductQuantityRowsCols.add("("+(row+1)+",2)");
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
						nonInsertedProductQuantityRowsCols.add("("+(row+1)+",2)--->Product Entry Date Can't Be In Future");
						continue;
					}
					else if((date.getYear()+1900)==localDate.getYear()){
						if((date.getMonth()+1)>localDate.getMonthValue()){
							nonInsertedProductQuantityRowsCols.add("("+(row+1)+",2)--->Product Entry Date Can't Be In Future");
							continue;
						}
						else if((date.getMonth()+1)==localDate.getMonthValue()){
							if(date.getDate()>localDate.getDayOfMonth()){
								nonInsertedProductQuantityRowsCols.add("("+(row+1)+",2)--->Product Entry Date Can't Be In Future");
								continue;
							}
							else{
								String finalDate=date.getDate()+"-"+(date.getMonth()+1)+"-"+(date.getYear()+1900);
								addProductQuantity.setProductEntryDate(finalDate);								
							}
						}
						else{
							String finalDate=date.getDate()+"-"+(date.getMonth()+1)+"-"+(date.getYear()+1900);
							addProductQuantity.setProductEntryDate(finalDate);
						}
					}
					else{
						String finalDate=date.getDate()+"-"+(date.getMonth()+1)+"-"+(date.getYear()+1900);
						addProductQuantity.setProductEntryDate(finalDate);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			
			if("".equals(sh.getCell(2, row).getContents())){
				nonInsertedProductQuantityRowsCols.add("("+(row+1)+",3)");
				continue;
			}
			else
			addProductQuantity.setAddProductQuantity(Double.parseDouble(sh.getCell(2, row).getContents()));
			
			if("".equals(sh.getCell(3, row).getContents()))
			{
				nonInsertedProductQuantityRowsCols.add("("+(row+1)+",4)");
				continue;
			}else
			addProductQuantity.setCp(Double.parseDouble(sh.getCell(3, row).getContents()));
			
			if("".equals(sh.getCell(4, row).getContents()))
			{
				nonInsertedProductQuantityRowsCols.add("("+(row+1)+",5)");
				continue;
			}else
			addProductQuantity.setSp(Double.parseDouble(sh.getCell(4, row).getContents()));

			if(addProductQuantity.getCp()>addProductQuantity.getSp()){
				nonInsertedProductQuantityRowsCols.add("("+(row+1)+",4) and ("+(row+1)+",5)--->CP should not be more than SP");
				continue;
			}
			addProductQuantity.setSold(0.0);
			addProductQuantity.setSpoil(0.0);
			long flag=0;
			flag = productServe.updateByAddProductQuantity(addProductQuantity,currentMall);
			if(flag==-1){
				nonInsertedProductQuantityRowsCols.add("("+(row+1)+",1)-->Cant Increase Unknown Product Quantity");
				continue;
			}
			else if(flag==1)
			addProductQuantityServe.insert(addProductQuantity);
		}

		if(nonInsertedProductQuantityRowsCols.size()==0)
			response.sendRedirect("add_quantity.jsp?productQuantityInsertedSuccessfully=true");
		else
			{
			request.getSession().setAttribute("ExcelFileAddProductQuantityObjSession",this);
			response.sendRedirect("add_quantity.jsp?ExcelQuantityInsertionError=true");
			}
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}