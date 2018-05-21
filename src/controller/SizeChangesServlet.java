package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Size;
import service.SizeService;

@WebServlet("/SizeChangesServlet")
public class SizeChangesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String flagString = request.getParameter("flag");
		SizeService sizeServe = new SizeService();
		Size size = sizeServe.getSizeById(id);
		if(flagString.equals("edit")){
			request.getSession().setAttribute("Edit Size",size);
			response.sendRedirect("add_size.jsp?editSize=true");
		}
		if(flagString.equals("delete")){
			sizeServe.deleteUnit(size);
			response.sendRedirect("add_size.jsp?sizeDeletedSuccessfully=true");
	}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Size size = new Size();
		SizeService sizeServe = new SizeService();
		Size sizeEditSessionObj = (Size) request.getSession().getAttribute("Edit Size");
		size.setId(sizeEditSessionObj.getId());
		size.setMallBranchGodown(sizeEditSessionObj.getMallBranchGodown());
		size.setSize(request.getParameter("txtSizeName"));
		size.setStatus(sizeEditSessionObj.getStatus());
		sizeServe.update(size);
		request.removeAttribute("Edit Size");
		response.sendRedirect("add_size.jsp?sizeUpdatedSuccessfully=true");
	}
}