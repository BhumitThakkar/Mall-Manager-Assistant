package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Category;
import service.CategoryService;

@WebServlet("/CategoryChangesServlet")
public class CategoryChangesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String flagString = request.getParameter("flag");
		CategoryService categoryServe = new CategoryService();
		Category category = categoryServe.getCategoryById(id);
		if(flagString.equals("edit")){
			request.getSession().setAttribute("Edit Category",category);
			response.sendRedirect("add_category.jsp?editCategory=true");
		}
		if(flagString.equals("delete")){
			categoryServe.deleteCategory(category);
			response.sendRedirect("add_category.jsp?categoryDeletedSuccessfully=true");
	}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Category category = new Category();
		CategoryService categoryServe = new CategoryService();
		Category categoryEditSessionObj = (Category) request.getSession().getAttribute("Edit Category");
		category.setId(categoryEditSessionObj.getId());
		category.setMallBranchGodown(categoryEditSessionObj.getMallBranchGodown());
		category.setCategory(request.getParameter("txtCategoryName"));
		category.setStatus(categoryEditSessionObj.getStatus());
		categoryServe.update(category);
		request.removeAttribute("Edit Category");
		response.sendRedirect("add_category.jsp?categoryUpdatedSuccessfully=true");
	}
}