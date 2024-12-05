package com.facade;

import java.util.Collection;

import com.Exception.existException;
import com.beans.Company;
import com.beans.Coupon;
import com.beans.Customer;
import com.clientType.clientType;
import com.dao.*;
import com.dao.dbimpl.CompanyDBDAO;
import com.dao.dbimpl.CouponDBDAO;
import com.dao.dbimpl.CustomerDBDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.pool.ConnectionPool;
import com.system.CouponSystem;

import DaoException.DaoException;

public class AdminFacade implements CouponClientFacade{

	//Fields
	
	private ConnectionPool 	pool	=	CouponSystem.getConnectionPool();
	private CompanyDAO  compd 	=	null;
	private CustomerDAO custd 	=	null;
	private CouponDAO	coupd	=	null;
	
	//~Contructor
	
	private AdminFacade(){
		compd	=	new CompanyDBDAO();
		custd	=	new CustomerDBDAO();
		coupd	=	new CouponDBDAO();
	}
	
	//C.R.U.D
	//Create,	Read,	Update,	Delete
	
	//Create
	public void createCompany(Company c){
		System.out.println("-------------------------------------");
		System.out.println("Admin create in Db the company: " + c.getCompName());
		System.out.println("-------------------------------------");
		compd.createCompany(c);
	}
	
	//Delete
	public void removeCompany(Company c){
		System.out.println("-------------------------------------");
		System.out.println("Admin remove in Db the company: " + c.getCompName());
		System.out.println("-------------------------------------");
		Connection	con	=	null;
		try{
		compd.removeCompany(c);}
		catch(DaoException|SQLException e){
			System.err.println("This Company not exist");
		}
		try {
			con	=	pool.getConnection();
			Statement stat = con.createStatement();
			
			String sql = "DELETE FROM Company_Coupon"
					+" where COMP_ID="+c.getId()+";";
			
			stat.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			releaseConnection(con);
		}
		con	=	null;
		try {
			if(c.getCoupons() == null)throw new existException("Company don't have coupons");
			for(Coupon coupon:c.getCoupons())
			{
			coupd.removeCoupon(coupon);
			con	=	pool.getConnection();
			Statement stat = con.createStatement();
			
			String sql = "DELETE FROM Customer_Coupon"
					+" where COUPON_ID="+coupon.getId()+";";
			stat.executeUpdate(sql);
			}
		} catch (SQLException|NullPointerException | existException e) {
			e.printStackTrace();
		}finally{
			releaseConnection(con);
		}
	}
	
	//Update
	public void updateCompany(Company c){
		System.out.println("-------------------------------------");
		System.out.println("Admin update in Db the company: " + c.getCompName());
		System.out.println("-------------------------------------");
		compd.updateCompany(c);
	}
	
	//Read
	public Company getCompany(long id){
		System.out.println("-------------------------------------");
		System.out.println("Admin reading in Db the company with ID: " + id);
		System.out.println("-------------------------------------");
		return compd.getCompany(id);

	}
	
	//Read_All
	public Collection<Company> getAllCompany(){
		System.out.println("-------------------------------------");
		System.out.println("Admin read in Db all companies");
		System.out.println("-------------------------------------");
		return compd.getAllCompany();
	}
	
	//Read
	public void createCustomer(Customer c) {
		try {
			custd.createCustomer(c);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		System.out.println("-------------------------------------");
		System.out.println("Admin create in Db the customer: "+c.getCustName());
		System.out.println("-------------------------------------");
	}
	
	//Delete
	public void removeCustomer(Customer c) {
		System.out.println("-------------------------------------");
		System.out.println("Admin deleting in Db the customer: "+c.getCustName());
		System.out.println("-------------------------------------");
		Connection	con	=	null;
		try {
			con	=	pool.getConnection();
			Statement stat = con.createStatement();
			String sql = "DELETE FROM Customer_Coupon "
					+"where CUST_ID="+c.getId()+";";
			stat.executeUpdate(sql);
			System.out.println(c.getCustName()+" is removed !");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			releaseConnection(con);
		}
		
		try{
		custd.removeCustomer(c);
		for(Coupon ccc:c.getCoupons()){
		coupd.removeCoupon(ccc);
		}
		}
		catch(NullPointerException | DaoException e){
			System.out.println(c.getCustName()+" not have coupons !");
		}
		System.out.println("Admin remove in Db the customer: "+c.getCustName());
		System.out.println("-------------------------------------");

	}
	
	private void releaseConnection(Connection con) {
	pool.free(con);
	}

	//Update
	public void updateCustomer(Customer c) {
		try {
			custd.updateCustomer(c);
			System.out.println("-------------------------------------");
			System.out.println("Admin update in Db the customer: "+c.getCustName());
			System.out.println("-------------------------------------");
		} catch (DaoException e) {
			e.printStackTrace();
		}

	}
	
	//Read
	public Customer getCustomer(long id) {
		System.out.println("-------------------------------------");
		System.out.println("Admin reading in Db the customer with ID: "+id);
		System.out.println("-------------------------------------");
		try {
			return custd.getCustomer(id);
		} catch (DaoException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	//Read_All
	public Collection<Customer> getAllCustomer(){
		System.out.println("-------------------------------------");
		System.out.println("Admin read in Db all customer");
		System.out.println("-------------------------------------");
		try {
			return custd.getAllCustomer();
		} catch (DaoException e) {
			System.out.println("\nThere's not customer!");
			e.printStackTrace();
			return null;
		}
	}
	
	
	//Instanciation
	@Override
	public CouponClientFacade login(String name, String password, clientType ct) {
		
		if(checkName(name) && checkPassword(password)
				&& ct.equals(clientType.AdminFacade))
		{	
		
			return new AdminFacade();
		}
		
		return null;
	}
	
	private static boolean checkPassword(String password){
		return password.equals("1234");
	}
	
	private static boolean checkName(String name){
		return name.equals("admin");
	}
	
	public static CouponClientFacade getInstance(String name,String password, clientType ct){
		AdminFacade af = new AdminFacade();
		return af.login(name, password, ct);
	}
	
}
