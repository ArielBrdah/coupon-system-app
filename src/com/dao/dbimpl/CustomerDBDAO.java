package com.dao.dbimpl;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import com.beans.Company;
import com.beans.Coupon;
import com.beans.Customer;
import com.coupons.CouponType;
import com.system.CouponSystem;
import com.dao.CouponDAO;
import com.dao.CustomerDAO;
import com.mysql.jdbc.PreparedStatement;
import com.pool.*;

import DaoException.DaoException;

public class CustomerDBDAO implements CustomerDAO{
	
	private ConnectionPool pool				= 	CouponSystem.getConnectionPool();
	private static final String TABLE_NAME	=	"Customer";
	private long id							=	0l;
	
	public void setSaveTheActualId(long id){
		this.id=id;
	}
	
	public long getSaveTheActualId(){
		return this.id;
	}
	
	
	
	public CustomerDBDAO() {
	}
	
	@Override
	public void createCustomer(Customer c) throws DaoException {
		
		//	1. get a connection from a pool
	   
		Connection con	=	null;

		try{		
			con	=	pool.getConnection();
		
		//	2. Condition if Exist ID
		//			 If is the Same ID:
		//			{	rs.next() == true } == checkID()
		
		if( checkID(c,con) )throw new DaoException("ID Exist in the Data Base");

		//	3. create sql insert
		
		PreparedStatement statm = (PreparedStatement) con.prepareStatement(
					"INSERT INTO "+ TABLE_NAME + " VALUES (?,?,?)");
			statm.setLong(1, c.getId());
			statm.setString(2, c.getCustName());
			statm.setString(3, c.getPassword());
		
		//	System.out.println("Executing: " + statm.toString());
			statm.executeUpdate();
			
			//System.out.println(c.getId()+" is created !");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
		//	3. release connection
		releaseConnection(con);
		}
		
	}
	
	private boolean checkID(Customer c, Connection con) throws SQLException {
		
		Statement stat = con.createStatement();
		String sql = "SELECT * FROM Coupons_System.Customer where ID="+c.getId()+";";
		ResultSet rs = stat.executeQuery(sql);
		
		return rs.next();
	}
	
	private boolean checkID(long c, Connection con) throws SQLException {
		
		Statement stat = con.createStatement();
		String sql = "SELECT * FROM Coupons_System.Customer where ID="+c+";";
		ResultSet rs = stat.executeQuery(sql);
		
		return rs.next();
	}
	
	
	private void releaseConnection(Connection con){
		pool.free(con);
	}

//	private Connection getConnection() throws SQLException {
////		String dbName	=	"Coupons_System";
////		String url		=	"jdbc:mysql://127.0.0.1:3306/"+dbName;//+"?verifyServerCertificate=false&useSSL=true";	
////		return DriverManager.getConnection(url,"root","Blaireau1");
//		return pool.getConnection();
//	}

	@Override
	public void removeCustomer(Customer c) throws DaoException {
		Connection con	=	null;
		try {
			con = pool.getConnection();
			if( !(checkID(c,con)) )throw new DaoException("ID Doesn't exist in the Data Base");
			Statement stat = con.createStatement();
			String sql = "DELETE FROM Coupons_System.Customer"
					+" where ID="+c.getId()+";";
		//	System.out.println("Executing: " + stat.toString());
			//int rows = 
					stat.executeUpdate(sql);
			System.out.println(c.getId()+" is Deleted !");
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			//	3. release connection
			releaseConnection(con);
			}
			
	}

	@Override
	public void updateCustomer(Customer c) throws DaoException{
		Connection con	=	null;

		
		
		try {
			con = pool.getConnection();
			
			if( !(checkID(c,con)) )throw new DaoException("ID Doesn't exist in the Data Base");
			if(c.getCustName().equals(null))throw new DaoException("The name is 'null' !");
			Statement stat = con.createStatement();
			String sql = "update Coupons_System.Customer"
						+" SET CUST_NAME=\""+c.getCustName()
						+"\", PASSWORD=\""+c.getPassword()
						+"\" where ID=\""+c.getId()+"\";";
			
		//	System.out.println("Executing: " + stat.toString());
			int rows = stat.executeUpdate(sql);
			System.out.println(c.getId()+" is updated !");
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			//	3. release connection
			releaseConnection(con);
			}		
	}

	@Override
	public Customer getCustomer(long id) throws DaoException{
		Connection con	=	null;
		Customer c	=	null;
		try {
			con = pool.getConnection();
			if( !(checkID(id,con)) )throw new DaoException("Customer not exist in the Data Base");
			Statement stat = con.createStatement();
			String sql = "SELECT * FROM Coupons_System.Customer where ID="+id+";";
			ResultSet rs = stat.executeQuery(sql);
			rs.next();
			
			setSaveTheActualId(id);
			c = new Customer(rs.getLong("ID")
					,rs.getString("CUST_NAME")
					,rs.getString("PASSWORD")
					,getCoupons());
			setSaveTheActualId(id);
			c.setCoupons(getCoupons());
			
			return c;}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally{
			//	3. release connection
			releaseConnection(con);
			}
		return c;
	}

	@Override
	public Collection<Customer> getAllCustomer() throws DaoException{
		
		Collection<Customer> c = new ArrayList<Customer>();


		Connection con	=	null;
		try {
			con = pool.getConnection();
			Statement stat = con.createStatement();
			String sql = "select ID,CUST_NAME,PASSWORD from Customer;";

		//	System.out.println("Executing: " + stat.toString());
			ResultSet rs = stat.executeQuery(sql);

			// Every next call - moves the result set to the next
			// record (row). When there no more records, it returns
			// false
			int count=1;
			while (rs.next())
			{
				
				Customer e	=	new Customer(rs.getLong("ID"), rs.getString("CUST_NAME"), rs.getString("PASSWORD"), getCoupons());
				c.add(e);

			}
			

		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			//	3. release connection
			releaseConnection(con);

		}
		return c;
	}

	@Override
	public Collection<Coupon> getCoupons() throws DaoException{
		Collection<Coupon> coupon = new ArrayList<Coupon>();
		CouponType ct = null;
		Connection con	=	null;
		
		try {
			con = pool.getConnection();
			Statement stat = con.createStatement();
			String sql = "SELECT TITLE,START_DATE,END_DATE,AMOUNT,TYPE,MESSAGE,PRICE,IMAGE "
					+"FROM Customer cp "
					+"JOIN Customer_Coupon cc "
					+"ON cp.ID=cc.CUST_ID "
					+"JOIN Coupon cn "
					+"ON cc.COUPON_ID=cn.ID "
					+"WHERE cc.CUST_ID="+getSaveTheActualId()+";";
			
		//	System.out.println("Executing: " + stat.toString());
			ResultSet rs = stat.executeQuery(sql);
			
			if(rs.wasNull())throw new DaoException("Doesn't have coupon");
			while(rs.next())
			{

				//System.out.println("the enum is : " +CouponType.values()[(rs.getInt("TYPE"))]);

				Coupon cpn	= new Coupon(
					getSaveTheActualId(),
					rs.getString("TITLE"),
					rs.getDate("START_DATE"),
					rs.getDate("END_DATE"),
					rs.getInt("AMOUNT"),
					CouponType.values()[(rs.getInt("TYPE"))],
					rs.getString("MESSAGE"),
					rs.getDouble("PRICE"),
					rs.getString("IMAGE"));
			//System.out.println(cpn.toString());
			coupon.add(cpn);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			releaseConnection(con);
		}
		return coupon;
	}

	@Override
	public boolean login(String CustName, String Password) {
		Connection con	=	null;
		boolean bool 	=	false;
		try{
			con =	pool.getConnection();
			Statement stat = con.createStatement();
			String sql = "SELECT * FROM Coupons_System.Customer where CUST_NAME=\""+CustName
					+"\" and PASSWORD=\""+Password+"\";";
			System.out.println(sql);
		//	System.out.println("Executing: " + stat.toString());
			ResultSet rs = stat.executeQuery(sql);
			
			// we need to use next to check the first row
			rs.next();
			//System.out.println("row is "+ rs.getRow() + rs.next());
			if(rs.getRow()!=0)bool=true;
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(con);
		}
		return bool;
		
	}

	
}
