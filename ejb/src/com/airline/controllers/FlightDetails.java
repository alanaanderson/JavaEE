package com.airline.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.service.FlightLocal_ejb;
import com.airline.service.FlightRemote;
import com.airline.service.FlightServiceStatelessBean;

/**
 * Servlet implementation class FlightDetails
 */
@WebServlet("/FlightDetails")
public class FlightDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FlightLocal_ejb fs;
	
	private FlightLocal_ejb fsStateful;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlightDetails() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("The flight details servlet has been called...");
		
		try {
			Context context = new InitialContext();
			
			Object fsObj = context.lookup("java:global/ejb/flightStateless!com.airline.service.FlightLocal_ejb");
			
			fs = (FlightLocal_ejb) fsObj;
			
			Object fsStatefulObj = context.lookup("java:global/ejb/flightStateful!com.airline.service.FlightLocal_ejb");
			
			fsStateful = (FlightLocal_ejb) fsStatefulObj;
		}
		catch(NamingException e) {
			System.out.println("Naming exception has occured in the lookup of our FlighService EJBs");
			e.printStackTrace();
		}
		
		// Stateless
		out.println("Flight Details: " + fs.getFrom() + " to " + fs.getTo());

		// Stateful
		out.println("Flight Details: " + fsStateful.getFrom() + " to " + fsStateful.getTo());
		


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
