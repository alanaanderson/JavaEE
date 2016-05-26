package com.airline.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.airline.service.CounterBean;
import com.airline.service.CounterStatefulBean;

/**
 * Servlet implementation class AddOne
 */
@WebServlet("/AddOne")
public class AddOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	CounterBean cb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOne() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession();
		
		CounterStatefulBean cbStateful = (CounterStatefulBean) s.getAttribute("cbStateful");
		
		PrintWriter out = response.getWriter();
		
		out.println("The current count value for the SINGLETON BEAN is: " + cb.getCount());
		
		out.println("The current count value for the STATEFUL BEAN is: " + cbStateful.getCount());
		
		cb.addOneToCount();
		
		cbStateful.addOneToCount();
		
		out.println("The count was incremented by one. The current count value for the SINGLETON BEAN is: " + cb.getCount());
		
		out.println("The count was incremented by one. The current count value for the STATEFUL BEAN is: " + cbStateful.getCount());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
