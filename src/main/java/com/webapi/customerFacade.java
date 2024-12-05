package com.webapi;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.Exception.amountException;
import com.Exception.existException;
import com.beans.Coupon;
import com.clientType.clientType;
import com.controller.AccountController;
import com.coupons.CouponType;
import com.system.CouponSystem;

import DaoException.DaoException;

import com.facade.*;
import com.google.gson.Gson;

@Path("customerfacade")
public class customerFacade /* extends AccountController*/{
//	private String name = super.getUser();
//	private String pass = super.getPass();
	@Context HttpServletRequest request;
	@Context private HttpServletResponse response;
	CustomerFacade facade = null;
	Gson gson = new Gson();
	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 * @throws DaoException 
	 * @throws existException 
	 * @throws SQLException 
	 * @throws amountException 
	 */
	private CustomerFacade getCustomerFacade() 
	{
		return (CustomerFacade) request.getSession().getAttribute("currentFacade");
	}
	
	@PUT
	@Path("purchasecoupon/{c}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response purchaseCoupon(@PathParam("c")long c){
		facade = getCustomerFacade();
		try {
			facade.purchaseCoupon(c);
		} catch (SQLException | DaoException | existException | amountException e) {
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		}
		return javax.ws.rs.core.Response.ok().status(200).build(); 
		//		CustomerFacade cf = 
//				(CustomerFacade)CouponSystem.login(this.name,this.pass, clientType.CustomerFacade);
//		if( cf != null) {
//			cf.purchaseCoupon(c);
//		}
	}
	
	@GET
	@Path("getallpurchasedcoupons")
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getAllPurchasedCoupons() {

		facade = getCustomerFacade();
		try {
		return facade.getAllPurchasedCoupons();
		} catch (SQLException e) {
		}
		
		//		CustomerFacade cf = 
//				(CustomerFacade)CouponSystem.login(this.name,this.pass, clientType.CustomerFacade);
//		if( cf != null) {
//			return cf.getAllPurchasedCoupons();
//		}
//		
		return null;
	}

	@GET
	@Path("getallpurchasedcouponsbytype/{ct}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getAllPurchasedCouponsByType(@PathParam("ct")CouponType ct){

		facade = getCustomerFacade();
		try {
			facade.getAllPurchasedCoupons();
		} catch (SQLException e) {
		}
		
//		CustomerFacade cf = 
//				(CustomerFacade)CouponSystem.login(this.name,this.pass, clientType.CustomerFacade);
//		if( cf != null) {
//		return	cf.getAllPurchasedCouponsByType(ct);
//		}
		return null;
	}
	
	@GET
	@Path("getallpurchasedcouponsbyprice/{price}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getAllPurchasedCouponsByPrice(@PathParam("price")double price) {
		facade = getCustomerFacade();
		try {
			facade.getAllPurchasedCouponsByPrice(price);
		} catch (SQLException e) {
		}
//		CustomerFacade cf = 
//				(CustomerFacade)CouponSystem.login(this.name,this.pass, clientType.CustomerFacade);
//		if( cf != null) {
//			return cf.getAllPurchasedCouponsByPrice(price);
//		}
		return null;
	}
}
