package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Exception.existException;
import com.clientType.clientType;
import com.facade.*;
import com.system.CouponSystem;

import DaoException.DaoException;
/**
 * Servlet implementation class AccountController
 */
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String USER = "";
    private static String PASS = ""; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountController() {
        super();
    }
//
//    public String getUser() {
//    	return USER;
//    }
//    public String getPass() {
//    	return PASS;
//    }
//    private void setUser(String str) {
//    	this.USER = str;
//    }
//    private void setPass(String str) {
//    	this.PASS = str;
//    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ACTION	: LOGIN OR LOGOUT
		
		String action = request.getParameter("action");
		if(action == null) {
			request.getRequestDispatcher("log.html").forward(request, response);
		}
		else if(action.equalsIgnoreCase("logout")) {
			// Removed the current username & password from the session
			// we can't go to the restrain page more
			HttpSession session = request.getSession();
			session.removeAttribute("currentFacade");
//			setUser("");
//			setPass("");
			request.getRequestDispatcher("log.html").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// LOGIN: we enter the user and pass
String username = request.getParameter("name");
String password = request.getParameter("password");
String client = request.getParameter("client");
//setUser(username);
//setPass(password);


	CouponClientFacade ccf;
	try {
		ccf = CouponSystem.login(username, password, clientType.valueOf(client));


	
	// if is he admin we enter all attribute password and username in the session
	// and allowed restrain page
	if(client.equals("AdminFacade") && ccf != null) {
		HttpSession session = request.getSession();
		session.setAttribute("currentFacade", ccf);
		// this is the restrain page - going on the filter
	request.getRequestDispatcher("adminfacade.html").forward(request, response);
	} else if(client.equals("CompanyFacade") && ccf != null){
		HttpSession session = request.getSession();
		session.setAttribute("currentFacade", ccf);
		request.getRequestDispatcher("companyfacade.html").forward(request, response);
	} 
	else if(client.equals("CustomerFacade") && ccf != null){
		HttpSession session = request.getSession();
		session.setAttribute("currentFacade", ccf);
		request.getRequestDispatcher("customerfacade.html").forward(request, response);
	} 
	else if(ccf == null){
		// if not client print this message
		request.getRequestDispatcher("log.html").forward(request, response);
	}
	} catch (SQLException | existException | DaoException e) {
		e.printStackTrace();
	}	
	}

}
