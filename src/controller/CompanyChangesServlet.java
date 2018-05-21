package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Company;
import service.CompanyService;

@WebServlet("/CompanyChangesServlet")
public class CompanyChangesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String flagString = request.getParameter("flag");
		CompanyService companyServe = new CompanyService();
		Company company = companyServe.getCompanyById(id);
		if(flagString.equals("edit")){
			request.getSession().setAttribute("Edit Company",company);
			response.sendRedirect("add_company.jsp?editCompany=true");
		}
		if(flagString.equals("delete")){
			companyServe.deleteCompany(company);
			response.sendRedirect("add_company.jsp?companyDeletedSuccessfully=true");
	}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Company company = new Company();
		CompanyService companyServe = new CompanyService();
		Company companyEditSessionObj = (Company) request.getSession().getAttribute("Edit Company");
		company.setId(companyEditSessionObj.getId());
		company.setMallBranchGodown(companyEditSessionObj.getMallBranchGodown());
		company.setCompany(request.getParameter("txtCompanyName"));
		company.setStatus(companyEditSessionObj.getStatus());
		companyServe.update(company);
		request.removeAttribute("Edit Company");
		response.sendRedirect("add_company.jsp?companyUpdatedSuccessfully=true");
	}

}
