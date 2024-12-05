package com.tests;
//================================
// Group: 
// Ariel Berdah and Naor Maguen
//
// Date: 2017.07
// Project Coupon
// Part 1
// JDBC
//
//================================



import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.beans.*;
import com.coupons.CouponType;
import com.dao.CompanyDAO;
import com.dao.CouponDAO;
import com.dao.CustomerDAO;
import com.dao.dbimpl.CompanyDBDAO;
import com.dao.dbimpl.CouponDBDAO;
import com.dao.dbimpl.CustomerDBDAO;
import com.facade.AdminFacade;
import com.facade.CompanyFacade;
import com.facade.CouponClientFacade;
import com.facade.CustomerFacade;
import com.pool.ConnectionPool;
import com.system.CouponSystem;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import DaoException.DaoException;

import java.sql.*;
import com.clientType.*;


public class testDB {
	
	public static void main(String[] args) throws ParseException{

		Date d	=	new Date();
		System.out.println("Today	:	"	+	d.toLocaleString());
		
				// Test for Admin Facade
		
//		CouponClientFacade ccf1 = CouponSystem.login("admin", "1234", clientType.AdminFacade);
//		Customer 	cust	=	new Customer(9598,"Rene","Babibel",null);
//		Company	comp	=	new Company(1233,"Californy","La","madas@hotmail.com",null);
//		testAdmin(ccf1,cust,comp);
//		
		
				// Test for Company Facade
		
//		CouponClientFacade ccf2 = CouponSystem.login("Big Shoes", "shoes455", clientType.CompanyFacade);
//		String STR_START	=	"1980-07-02";	
//		String STR_END		=	"2017-07-17";	
//		DateFormat df		=	new SimpleDateFormat("yyyy-mm-dd");
//		java.util.Date d1	=	df.parse(STR_START);
//		java.util.Date d2	=	df.parse(STR_END);
//		long	lon			=	82l;
//		Coupon c;
//		c=new Coupon(lon,"Nike Air kolibri22",
//				new java.sql.Date(d1.getTime()),
//				new java.sql.Date(d2.getTime()),
//				15,CouponType.SPORTS,"free for basket",25.90,
//				"http://zoomparis.com/2256-large_default/basket-nike-air-jordan-6-retro-black-infrared.jpg");
//	
//		testCompany(ccf2,c);
//		CouponSystem.shutdown();

					// Test for Customer Facade
//		String STR_START	=	"1980-01-02";	
//		String STR_END	=	"2222-01-3";	
//		DateFormat df		=	new SimpleDateFormat("yyyy-mm-dd");
//		java.util.Date d1	=	df.parse(STR_START);
//		java.util.Date d2	=	df.parse(STR_END);
//		long	lon			=	6l;
//		Coupon c;
//		c=new Coupon(lon,"Nike Air",
//				new java.sql.Date(d1.getTime()),
//				new java.sql.Date(d2.getTime()),
//				15,CouponType.SPORTS,"free for basket",259.90,
//				"http://zoomparis.com/2256-large_default/basket-nike-air-jordan-6-retro-black-infrared.jpg");
//		
//		CouponClientFacade ccf3 = CouponSystem.login("Amit Goldmann", "95153456", clientType.CustomerFacade);
//		testCustomer(ccf3,c);
//		CouponSystem.shutdown();
		}

public static void testCustomer(CouponClientFacade ccf3,Coupon c){
	if( ccf3 instanceof CustomerFacade){
		
		//C.R.U.D
		
		//Create, Read: to Purchase
		//((CustomerFacade) ccf3).purchaseCoupon(c);
		
		//Read All Coupons Purchased
		//System.out.println(((CustomerFacade) ccf3).getAllPurchasedCoupons());
		
		//Read All Coupons Purchased by type
		//System.out.println(((CustomerFacade) ccf3).getAllPurchasedCouponsByType(c.getType()));
		
	}
	
	
	
	
	}
public static void testCompany(CouponClientFacade ccf2,Coupon c){
		if(ccf2 instanceof CompanyFacade ){
	
			//Create
			//((CompanyFacade) ccf2).createCoupon(c);
			
			//Read
			//System.out.println(((CompanyFacade) ccf2).getCompany(1l));
			
			//Update
			//STR_END		=	"1989-01-03";	
			//d2			=	df.parse(STR_END);
			//c			=	new Coupon(70l,new java.sql.Date(d2.getTime()),400);
			//((CompanyFacade) ccf2).updateCoupon(c);
			
			//Read
			// System.out.println(((CompanyFacade) ccf2).getCompany(1l));
			
			//Delete
			//((CompanyFacade) ccf2).removeCoupon(c);
			
			//Read
			// System.out.println(((CompanyFacade) ccf2).getCompany(1l));
			
			//Read All
			// System.out.println(((CompanyFacade) ccf2).getAllCoupon());
			
			//Read All By Type
			// System.out.println(((CompanyFacade) ccf2).getCouponByType(CouponType.SPORTS));

		}
				
	}

public static void testAdmin(CouponClientFacade ccf1,Customer cust,Company comp)
{
		if(ccf1 instanceof AdminFacade){
			
			//Create
		//	((AdminFacade) ccf1).createCustomer(cust);
		//	((AdminFacade) ccf1).createCompany(comp);
			
			//Read
		//	System.out.println(((AdminFacade) ccf1).getCustomer(cust.getId()));
		//	System.out.println(((AdminFacade) ccf1).getCompany(comp.getId()));
			
			//Update
		//	cust	=	new Customer(9598,"Bernard","pleupleu",null);
		//	comp	=	new Company(1233,"BigDeal","Lalalala","madass@hotmail.com",null);
		//	((AdminFacade) ccf1).updateCustomer(cust);
		//	((AdminFacade) ccf1).updateCompany(comp);
			
			//Read
		//	System.out.println(((AdminFacade) ccf1).getCustomer(cust.getId()));
		//	System.out.println(((AdminFacade) ccf1).getCompany(comp.getId()));
			
			//Delete
		//	((AdminFacade) ccf1).removeCustomer(cust);
		//	((AdminFacade) ccf1).removeCompany(comp);
			
			//Read
		//	System.out.println(((AdminFacade) ccf1).getCustomer(cust.getId()));
		//	System.out.println(((AdminFacade) ccf1).getCompany(comp.getId()));
			
		}
	
}	
} 