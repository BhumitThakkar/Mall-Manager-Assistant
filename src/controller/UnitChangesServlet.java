package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Unit;
import service.UnitService;

@WebServlet("/UnitChangesServlet")
public class UnitChangesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String flagString = request.getParameter("flag");
		UnitService unitServe = new UnitService();
		Unit unit = unitServe.getUnitById(id);
		if(flagString.equals("edit")){
			request.getSession().setAttribute("Edit Unit",unit);
			response.sendRedirect("add_unit.jsp?editUnit=true");
		}
		if(flagString.equals("delete")){
			unitServe.deleteUnit(unit);
			response.sendRedirect("add_unit.jsp?unitDeletedSuccessfully=true");
	}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Unit unit = new Unit();
		UnitService unitServe = new UnitService();
		Unit unitEditSessionObj = (Unit) request.getSession().getAttribute("Edit Unit");
		unit.setId(unitEditSessionObj.getId());
		unit.setMallBranchGodown(unitEditSessionObj.getMallBranchGodown());
		unit.setUnit(request.getParameter("txtUnitName"));
		unit.setStatus(unitEditSessionObj.getStatus());
		unitServe.update(unit);
		request.removeAttribute("Edit Unit");
		response.sendRedirect("add_unit.jsp?unitUpdatedSuccessfully=true");
	}

}