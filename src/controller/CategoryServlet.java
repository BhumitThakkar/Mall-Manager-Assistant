package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Category;
import model.MallBranchGodown;
import service.CategoryService;

@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Category category = new Category();
		CategoryService categoryServe = new CategoryService();
		MallBranchGodown currentMall = (MallBranchGodown) request.getSession().getAttribute("Current Mall");
		category.setMallBranchGodown(currentMall);
		category.setCategory(request.getParameter("txtCategoryName"));
		category.setStatus('A');
		long flag = 0;
		flag = categoryServe.insert(category);
		if(flag>0){
			response.sendRedirect("add_category.jsp?categoryInsertedSuccessfully=true");
		}
		else if(flag==0){
			response.sendRedirect("add_category.jsp?categoryInsertedSuccessfully=false");
		}
	}
}