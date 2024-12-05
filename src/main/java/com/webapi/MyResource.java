package com.webapi;

import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import com.Exception.*;
import com.beans.Company;
import com.beans.Customer;
import com.clientType.clientType;
import com.controller.AccountController;
import com.facade.AdminFacade;
import com.facade.CompanyFacade;
import com.system.CouponSystem;

import DaoException.DaoException;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource /* extends AccountController*/{
//	private String name = super.getUser();
//	private String password = super.getPass();

	@Context HttpServletRequest request;
	@Context private HttpServletResponse response;
	AdminFacade facade = null;
	Gson gson = new Gson();
	
	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 * @throws existException 
	 * @throws DaoException 
	 * @throws SQLException 
	 */
	
	private AdminFacade getAdminFacade() 
	{
		return (AdminFacade) request.getSession().getAttribute("currentFacade");
	}
	
	//==========COMPANY
	@POST
	@Path("createcompany")
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response createCompany(Company c){
		facade = getAdminFacade();
		try {
			facade.createCompany(c);
		} catch (SQLException | DaoException | existException e) {
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		}
		return javax.ws.rs.core.Response.ok(c.getCompName() + " was created").status(200).build(); 
//		AdminFacade af = 
//				(AdminFacade)CouponSystem.login(this.name,this.password, clientType.AdminFacade);
//		if(af != null)
//			af.createCompany(c);
	}

	@GET
	@Path("getcompany/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompany( @PathParam("id")long id) throws SQLException, existException, DaoException {
		facade = getAdminFacade();
		try {
			facade.getCompany(id);
		} catch (SQLException | DaoException e) {
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		}
		return javax.ws.rs.core.Response.ok().status(200).build(); 
//		AdminFacade af = 
//				(AdminFacade)CouponSystem.login(this.name,this.password, clientType.AdminFacade);
//		if( af != null )
//			return af.getCompany(id);
//		return null;
	}
	
	@DELETE
	@Path("removecompany/{index}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeCompany(@PathParam("index")long index) throws SQLException, existException, DaoException {
		facade = getAdminFacade();
		try {
			facade.removeCompany(index);
		} catch (SQLException e) {
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		}
		return javax.ws.rs.core.Response.ok().status(200).build();
		
	//		AdminFacade af = 
//				(AdminFacade)CouponSystem.login(this.name,this.password, clientType.AdminFacade);
//		if( af != null )
//			af.removeCompany(index);
	}
	
	@PUT
	@Path("updatecompany")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCompany(Company c) {

		facade = getAdminFacade();
		try {
			facade.updateCompany(c);
		} catch (SQLException | DaoException  e) {
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		}
		return javax.ws.rs.core.Response.ok().status(200).build();
//		AdminFacade af = 
//				(AdminFacade)CouponSystem.login(this.name,this.password, clientType.AdminFacade);
//		if( af != null )
//			af.updateCompany(c);
	}
	
	@GET
	@Path("getallcompanies")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Company> getAllCompany() {

		facade = getAdminFacade();
		try {
			return facade.getAllCompany();
		} catch (SQLException e) {
		}
		return null;

		//		AdminFacade af = 
//				(AdminFacade)CouponSystem.login(this.name,this.password, clientType.AdminFacade);
//		return af.getAllCompany();
	}
	
	//==========CUSTOMER
	@POST
	@Path("createcustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCustomer(Customer c) {

		facade = getAdminFacade();
		try {
			facade.createCustomer(c);
		} catch (SQLException | DaoException | existException e) {
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		}
		return javax.ws.rs.core.Response.ok().status(200).build(); 
		
		//		AdminFacade af = 
//				(AdminFacade)CouponSystem.login(this.name,this.password, clientType.AdminFacade);
//		if(af != null)
//			af.createCustomer(c);
	}
	
	@GET
	@Path("getcustomer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomer(@PathParam("id")long id) {

		facade = getAdminFacade();
		try {
			return facade.getCustomer(id);
		} catch (SQLException | DaoException e) {
		}		
		//		AdminFacade af = 
//				(AdminFacade)CouponSystem.login(this.name,this.password, clientType.AdminFacade);
//		if( af != null )
//			return af.getCustomer(id);
	
		return null;
	}
	
	@DELETE
	@Path("removecustomer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeCustomer(@PathParam("id")long id) {
		facade = getAdminFacade();
		try {
			facade.removeCustomer(id);
		} catch (SQLException | DaoException | existException e) {
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		}
		return javax.ws.rs.core.Response.ok().status(200).build(); 
		
		//		AdminFacade af = 
//				(AdminFacade)CouponSystem.login(this.name,this.password, clientType.AdminFacade);
//		if( af != null )
//			af.removeCustomer(id);
	}
	
	@PUT
	@Path("updatecustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCustomer(Customer c) {

		facade = getAdminFacade();
		try {
			facade.updateCustomer(c);
		} catch (SQLException | DaoException e) {
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		}
		return javax.ws.rs.core.Response.ok().status(200).build();
		
	//		AdminFacade af = 
//				(AdminFacade)CouponSystem.login(this.name,this.password, clientType.AdminFacade);
//		if( af != null )
//			af.updateCustomer(c);
	}
	
	@GET
	@Path("getallcustomer")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Customer> getAllCustomer() {
		facade = getAdminFacade();
		try {
			return facade.getAllCustomer();
		} catch (SQLException | DaoException e) {
		}
		//		AdminFacade af = 
//				(AdminFacade)CouponSystem.login(this.name,this.password, clientType.AdminFacade);
//		return af.getAllCustomer();
		return null;
	}
}
