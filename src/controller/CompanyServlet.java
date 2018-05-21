package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Company;
import model.MallBranchGodown;
import service.CompanyService;

@WebServlet("/CompanyServlet")
public class CompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Company company = new Company();
		CompanyService companyServe = new CompanyService();
		MallBranchGodown currentMall = (MallBranchGodown) request.getSession().getAttribute("Current Mall");
		company.setMallBranchGodown(currentMall);
		company.setCompany(request.getParameter("txtCompanyName"));
		company.setStatus('A');
		long flag = 0;
		flag = companyServe.insert(company);
		if(flag>0){
			response.sendRedirect("add_company.jsp?companyInsertedSuccessfully=true");
		}
		else if(flag==0){
			response.sendRedirect("add_company.jsp?companyInsertedSuccessfully=false");
		}
	}
}