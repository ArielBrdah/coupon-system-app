package com.webapi;

import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.Exception.amountException;
import com.Exception.existException;
import com.beans.*;
import com.clientType.clientType;
import com.controller.AccountController;
import com.coupons.CouponType;
import com.facade.*;
import com.google.gson.Gson;
import com.system.CouponSystem;

import DaoException.DaoException;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("companyfacade")
public class companyFacade /*extends AccountController*/{
//private String name = super.getUser();
//private String pass = super.getPass();
	@Context HttpServletRequest request;
	@Context private HttpServletResponse response;
	CompanyFacade facade = null;
	Gson gson = new Gson();
	
	private CompanyFacade getCompanyFacade() 
	{
		return (CompanyFacade) request.getSession().getAttribute("currentFacade");
	}

		@POST
		@Path("createcoupon")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response createCoupon(Coupon c){
			facade = getCompanyFacade();
			try {
				facade.createCoupon(c);
			} catch (SQLException | existException e) {
				return javax.ws.rs.core.Response.ok(e).status(500).build();
			}
			return javax.ws.rs.core.Response.ok().status(200).build(); 
//			CompanyFacade cf = 
//					(CompanyFacade)CouponSystem.login(this.name,this.pass, clientType.CompanyFacade);
//
//			if(cf != null) {
//				cf.createCoupon(c);
//			}
		}
		
		@DELETE
		@Path("removecoupon/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response removeCoupon(@PathParam("id")long id){
			facade = getCompanyFacade();
			try {
				facade.removeCoupon(id);
			} catch (SQLException | DaoException e) {
				return javax.ws.rs.core.Response.ok(e).status(500).build();
			}
			return javax.ws.rs.core.Response.ok().status(200).build(); 
//			CompanyFacade cf = 
//					(CompanyFacade)CouponSystem.login(this.name,this.pass, clientType.CompanyFacade);
//			if(cf != null) {
//				cf.removeCoupon(id);
//			}
		}

		@PUT
		@Path("updatecoupon")
		@Produces(MediaType.APPLICATION_JSON)
				public Response updateCoupon(Coupon c) {		
			facade = getCompanyFacade();
			try {
				facade.updateCoupon(c);
			} catch (SQLException | DaoException e) {
				return javax.ws.rs.core.Response.ok(e).status(500).build();
			}
			return javax.ws.rs.core.Response.ok().status(200).build(); 
			//			CompanyFacade cf =
//					(CompanyFacade)CouponSystem.login(this.name,this.pass, clientType.CompanyFacade);
//			if(cf != null) {
//				cf.updateCoupon(c);
//			}
		}
		
		@GET
		@Path("getcoupon/{id}")
		@Produces(MediaType.APPLICATION_JSON)
				public Coupon getCoupon(@PathParam("id")long id) {
			facade = getCompanyFacade();

				try {
					return facade.getCoupon(id);
				} catch (SQLException | existException e) {
					e.printStackTrace();
				}
//			CompanyFacade cf = 
//					(CompanyFacade)CouponSystem.login(this.name,this.pass, clientType.CompanyFacade);
//
//			if(cf != null) {
//				return cf.getCoupon(id);
//			}
				return null;
		}

		@GET
		@Path("getallcoupons")
		@Produces(MediaType.APPLICATION_JSON)
				public Collection<Coupon> getAllCoupon() {
			facade = getCompanyFacade();
			try {
				return facade.getAllCoupon();
			} catch (SQLException e) {
			}
			//			CompanyFacade cf =
//					(CompanyFacade)CouponSystem.login(this.name,this.pass, clientType.CompanyFacade);
//			if(cf != null) {
//				return cf.getAllCoupon();
//			}
//			return null;
			return null;
		}
		
		@GET
		@Path("getallcouponsbytype/{type}")
		@Produces(MediaType.APPLICATION_JSON)
				public Collection<Coupon> getAllCouponByTypes(@PathParam("type")CouponType ct) {
			facade = getCompanyFacade();
			try {
			 facade.getCouponByType(ct);
			} catch (SQLException e) {
			}
			//			CompanyFacade cf =
//					(CompanyFacade)CouponSystem.login(this.name,this.pass, clientType.CompanyFacade);
//			if(cf != null) {
//				return cf.getCouponByType(ct);
//			}
			return null;
		}

}
