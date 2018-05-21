package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MallBranchGodown;
import model.Unit;
import service.UnitService;

@WebServlet("/UnitServlet")
public class UnitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Unit unit = new Unit();
		UnitService unitServe = new UnitService();
		MallBranchGodown currentMall = (MallBranchGodown) request.getSession().getAttribute("Current Mall");
		unit.setMallBranchGodown(currentMall);
		unit.setUnit(request.getParameter("txtUnitName"));
		unit.setStatus('A');
		long flag = 0;
		flag = unitServe.insert(unit);
		if(flag>0){
			response.sendRedirect("add_unit.jsp?unitInsertedSuccessful=true");
		}
		else if(flag==0){
			response.sendRedirect("add_unit.jsp?unitInsertedSuccessful=false");
		}
	}
}
