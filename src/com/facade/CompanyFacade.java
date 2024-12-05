package com.facade;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import com.Exception.existException;
import com.beans.Company;
import com.beans.Coupon;
import com.clientType.clientType;
import com.coupons.CouponType;
import com.dao.CompanyDAO;
import com.dao.CouponDAO;
import com.dao.CustomerDAO;
import com.dao.dbimpl.CompanyDBDAO;
import com.dao.dbimpl.CouponDBDAO;
import com.dao.dbimpl.CustomerDBDAO;
import com.mysql.jdbc.PreparedStatement;
import com.pool.ConnectionPool;
import com.system.CouponSystem;

import DaoException.DaoException;

public class CompanyFacade implements CouponClientFacade{

		//Fields
	
	private ConnectionPool pool	=	CouponSystem.getConnectionPool();
	private CustomerDAO custd	=	null;
	private CompanyDAO 	cd 		= 	null;
	private CouponDAO	coup	= 	null;
	private Company 	comp	=	null;
	
		//~Constructor
	
	private CompanyFacade(){}
	private CompanyFacade(Company c) {
	comp	=	c;
	custd	=	new CustomerDBDAO();
	cd 		=  	new CompanyDBDAO();
	coup 	= 	new CouponDBDAO();
	Connection con	=	null;
	try {
		con = pool.getConnection();
		Statement stat = con.createStatement();
		String sql = "SELECT * FROM Coupons_System.Company where COMP_NAME=\""
		+c.getCompName()+"\" and PASSWORD=\""
		+c.getPassword()+"\";";
		ResultSet rs = stat.executeQuery(sql);
		rs.next();	
		c.setId(rs.getLong("ID"));
		comp	=	cd.getCompany(c.getId());
		//comp.setCoupons(getAllCoupon());
		
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	finally{
		//	3. release connection
		releaseConnection(con);
		
		}
	}
	
	public void releaseConnection(Connection con){
		pool.free(con);
	}
	
		//C.R.U.D:		
		//	Create,	Read,	Update,	Delete
	
	//Create
	private boolean checkName(Coupon c, Connection con) throws SQLException {
		con	=	pool.getConnection();
		Statement stat = con.createStatement();
		String sql = "SELECT * FROM Coupons_System.Coupon where TITLE=\""+c.getTitle()+"\";";
		ResultSet rs = stat.executeQuery(sql);
		boolean q	=	rs.next();
		return q;
	}
	public void createCoupon(Coupon c){
		Connection con	=	null;	
		try{
		System.out.println("Company:"+comp.getCompName()+"\ncreating a coupon:"+c.getTitle());
		System.out.println("-------------------------------------");
		if(	checkName(c,con))throw new existException("The Coupon with same name exist!");
		coup.createCoupon(c);	
			con		=	pool.getConnection();
			PreparedStatement statm = (PreparedStatement) con.prepareStatement(
					"INSERT INTO Company_Coupon VALUES (?,?)");
			statm.setLong(1, comp.getId());
			statm.setLong(2, c.getId());
		
		//	System.out.println("Executing: " + statm.toString());
			statm.executeUpdate();
			
			System.out.println(comp.getCompName()+" created and inserted the coupons !\n");
			
		}
		catch (SQLException | existException e) {
		System.out.println(e.getMessage());
		}
		finally{
			//	3. release connection
			releaseConnection(con);
			
			}
	}
	
	//Delete
	public void removeCoupon(Coupon c){
		System.out.println("\nCompany:"+comp.getCompName()+"\nremove a coupon:"+c.getTitle());
		System.out.println("-------------------------------------");
		//Delete from Company_Coupon
		
		Connection	con	=	null;
		try {
			con	=	pool.getConnection();
			Statement stat = con.createStatement();
			
			String sql = "DELETE FROM Company_Coupon"
					+" where COUPON_ID="+c.getId()+";";
			
			stat.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			releaseConnection(con);
		}
		
		//Delete from Customer_Coupon
		
		con	=	null;
		try {
			con	=	pool.getConnection();
			Statement stat = con.createStatement();
			
			String sql = "DELETE FROM Customer_Coupon"
					+" where COUPON_ID="+c.getId()+";";
			
			stat.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			releaseConnection(con);
		}
		
		//Delete from Coupon
		
		coup.removeCoupon(c);
	}
	
	//Update
	public void updateCoupon(Coupon c){
		try{
		System.out.println("\nCompany:"+comp.getCompName()+"\nupdate a coupon:");
		System.out.println("-------------------------------------");
		coup.updateCoupon(c);
		System.out.println("Coupons Updated");
		}catch(SQLException|DaoException e){
			e.printStackTrace();
		}
	}
	
	//Read
	public Company getCompany(long id){
		System.out.println("\nCompany:"+comp.getCompName()+"\nreading details of company:");
		System.out.println("-------------------------------------");
		return cd.getCompany(id);
	} 
	
	//Read
	public Collection<Coupon> getAllCoupon(){
		System.out.println("\nCompany:"+comp.getCompName()+"\nread or set all coupons:");
		System.out.println("-------------------------------------");
		//new CouponDBDAO().
		CouponDBDAO c	=	new CouponDBDAO();
		c.setCC_ID(comp.getId());
		c.setTableNameC("Company");
		c.setTableNameCC("Company_Coupon");
		return c.getAllCoupon();
	}
	
	//Read
	public Collection<Coupon> getCouponByType(CouponType ct){
		System.out.println("\nCompany:"+comp.getCompName()+"\nread coupons by type:");
		System.out.println("-------------------------------------");
		CouponDBDAO c	=	new CouponDBDAO();
		c.setCC_ID(comp.getId());
		c.setTableNameC("Company");
		c.setTableNameCC("Company_Coupon");
		return c.getCouponByType(ct);
	} 
	
		//	Instanciation
	
	@Override
	public CouponClientFacade login(String name, String password, clientType ct) {
	
		try{
		int 	row =	0;
		Company c	=	new Company();
		cd	=	new CompanyDBDAO();
		c.setCompName(name);
		c.setPassword(password);
		if(!(cd.login(name, password)))throw new existException("The password or name is wrong !");
		{
			return new CompanyFacade(c);
		}
		}catch(existException e){
			e.printStackTrace();
			return null;
		}
	}
	

	public static CouponClientFacade getInstance(String name,String password,clientType ct){
		
	CouponClientFacade ccf	=	new CompanyFacade();

	return ccf.login(name, password, ct);
		
	}

}