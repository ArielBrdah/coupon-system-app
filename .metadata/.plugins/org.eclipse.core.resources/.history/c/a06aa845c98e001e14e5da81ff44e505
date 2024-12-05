package com.facade;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.Exception.amountException;
import com.Exception.existException;
import com.beans.Coupon;
import com.beans.Customer;
import com.clientType.clientType;
import com.coupons.CouponType;
import com.dao.CouponDAO;
import com.dao.CustomerDAO;
import com.dao.dbimpl.CouponDBDAO;
import com.dao.dbimpl.CustomerDBDAO;
import com.pool.ConnectionPool;
import com.system.CouponSystem;

import DaoException.DaoException;

public class CustomerFacade implements CouponClientFacade{

	//Fields
	
	private ConnectionPool 	pool		=	CouponSystem.getConnectionPool();
	private CustomerDAO		cust		=	null;
	private CouponDAO		coup		=	null;
	private Customer 		customer	=	null;
	private long			idForCoupon	=	1l;
		
	//~Constructor
	private CustomerFacade(){}
	private CustomerFacade(Customer c){
		cust	=	new CustomerDBDAO();
		coup	=	new CouponDBDAO();
		Connection con	=	null;
		try {
			con = pool.getConnection();
			Statement stat = con.createStatement();
			String sql = "SELECT * FROM Coupons_System.Customer where CUST_NAME=\""
			+c.getCustName()+"\" and PASSWORD=\""
			+c.getPassword()+"\";";
			ResultSet rs = stat.executeQuery(sql);
			rs.next();
			
			c.setId(rs.getLong("ID"));
			customer	=	cust.getCustomer(c.getId());
			customer.setCoupons(getAllPurchasedCoupons());
			
		}
		catch (SQLException | DaoException e) {
			e.printStackTrace();
		}
		finally{
			//	3. release connection
			releaseConnection(con);
			
			}
	}
	
	
	//Give an Id for the Customer that purchased coupons
	public void giveMeAnIdForCustomerCoupon(long id){
		Connection con	=	null;
		long last		=	id;
		if(idForCoupon>id){
			last	=	idForCoupon;
		}
		try {
			con = pool.getConnection();
			Statement stat = con.createStatement();
			String sql = "select * from Coupon;";
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				if(last<rs.getLong("ID"))
				{
					last=rs.getLong("ID");
				}
			}
			idForCoupon=last;
			idForCoupon++;
		}
		catch (SQLException  e) {
			e.printStackTrace();
		}
		finally{
			//	3. release connection
			releaseConnection(con);
			
			}
		
	}
	
	// Purchase Coupon: 
	// use method: 
	// Delete, update of C.R.U.D

	public void updateOnCoupon(Coupon c){
		Connection con	=	null;
		try{
			con	=	pool.getConnection();
			String sql	=	"UPDATE Coupon SET AMOUNT="+c.getAmount()
			+" where ID="+c.getId()+";";
			Statement stat	=	con.createStatement();
			stat.executeUpdate(sql);
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			releaseConnection(con);
		}
		
	}
	public void purchaseCoupon(Coupon c){
		System.out.println("Customer purchasing the coupon : "+c.getTitle()+" Amount :  "+(c.getAmount()-1)+ " in supply");
		System.out.println("-------------------------------------");
		Connection con	=	null;
				
		try{
			
		if(!(coup.getCoupon(c.getId()) instanceof Coupon))throw new existException("The coupon isn't exist");
		if(c.getAmount()<=0)
		{
			coup.removeCoupon(c);
			throw new amountException("The supply is empty !");
		}
		
		//We take one produce
		c.setAmount(c.getAmount()-1);
		
		//Important thing 1
		//Doing update on amount
		updateOnCoupon(c);

		//We prepare the coupon for the customer
		c.setAmount(1);
		
		//We set an id in the Db in Coupon
		// like that Customer
		// can check all coupons purchased
		giveMeAnIdForCustomerCoupon(idForCoupon);
		c.setId(idForCoupon);
		
		//Creating this coupon on the Db we separate
		// the produce e.g.:Shoes Amount:5
		
		//Important thing 2
		//now : Shoes Amount:4 with id of the CompanyCoupon
		//		Shoes Amount:1 with id of the CustomerCoupon
		coup.createCoupon(c);
		
		//for the session we set the collection
		Collection<Coupon> col	=	new ArrayList<Coupon>();
		col.addAll(customer.getCoupons());
		col.add(c);
		customer.setCoupons(col);
		
			con = pool.getConnection();
			Statement stat = con.createStatement();
			String sql = "update Customer_Coupon"
			+" SET COUPON_ID=\""+idForCoupon+"\""
			+" where CUST_ID=\""+customer.getId()+"\";";
			int rows = stat.executeUpdate(sql);
			
		
		}
		catch(amountException | existException | SQLException e){
			e.printStackTrace();
		}	finally{
			//	3. release connection
			releaseConnection(con);
			
			}
		
	}
	
	//Read all purchased coupons
	//of the customers
	public Collection<Coupon> getAllPurchasedCoupons()
	{

	CouponDBDAO c	=	new CouponDBDAO();
	c.setCC_ID(customer.getId());
	c.setTableNameC("Customer");
	c.setTableNameCC("Customer_Coupon");
	return c.getAllCoupon();
	}
	
	//Read all purchased coupons by type
	public Collection<Coupon> getAllPurchasedCouponsByType(CouponType ct)
	{
	System.out.println("Customer read all purchased coupons by type");
	System.out.println("-------------------------------------");
	CouponDBDAO c	=	new CouponDBDAO();
	c.setCC_ID(customer.getId());
	c.setTableNameC("Customer");
	c.setTableNameCC("Customer_Coupon");
	return c.getAllCoupon();
	}

	
	//Instanciation
	
	@Override
	public CouponClientFacade login(String name, String password, clientType ct) {
		
		cust = new CustomerDBDAO();
		
		try {
			if(!(cust.login(name, password)))throw new existException("The password or name is wrong !");
			
				customer	=	new Customer();
				customer.setCustName(name);
				customer.setPassword(password);
		} 
		catch (DaoException | existException e) 
		{
			e.printStackTrace();
			return null;
		}
		return new CustomerFacade(customer);
	}

	public void releaseConnection(Connection con){
		pool.free(con);
	}
	
	public static CouponClientFacade getInstance(String name,String password,clientType ct){
		
		CouponClientFacade ccf	=	new CustomerFacade();

		return ccf.login(name, password, ct);
	}
}
