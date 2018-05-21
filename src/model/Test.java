package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Date date=new SimpleDateFormat("yyyy-MM-dd").parse("2017-04-22");
			Date date1 = new Date();
			System.out.println(date1);
			if(date1.after(date)){
				System.out.println("Wrong");
			}
			else{
				System.out.println("Right");				
			}
/*			System.out.println(date+"-----------------------78 Line Product Servlet");
			String finalDate=date.getDate()+"-"+(date.getMonth()+1)+"-"+(date.getYear()+1900);
			System.out.println(finalDate+"-----------------------78 Line Product Servlet");*/
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
