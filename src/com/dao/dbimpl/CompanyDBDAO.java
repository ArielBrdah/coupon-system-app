package com.dao.dbimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import com.beans.Company;
import com.beans.Coupon;
import com.coupons.CouponType;
import com.dao.CompanyDAO;
import com.dao.CouponDAO;
import com.mysql.jdbc.PreparedStatement;
import com.pool.ConnectionPool;
import com.system.CouponSystem;

import DaoException.DaoException;

public class CompanyDBDAO implements CompanyDAO{

	private ConnectionPool pool				= 	CouponSystem.getConnectionPool();
	private static final String TABLE_NAME	=	"Company";
	private long saveTheActualID			=	0l; // need to use the ID from getAllCompany 
												   // which use the fonction getCoupons
	
	@Override
	public void createCompany(Company c){
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
					"INSERT INTO "+ TABLE_NAME + " VALUES (?,?,?,?)");
			statm.setLong(1, c.getId());
			statm.setString(2, c.getCompName());
			statm.setString(3, c.getPassword());
			statm.setString(4, c.getEmail());
		
		//	System.out.println("Executing: " + statm.toString());
			statm.executeUpdate();
			
		//	System.out.println(c.getId()+" is created !");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		catch(DaoException e)
		{
			e.printStackTrace();
		}
		finally{
		//	3. release connection
		releaseConnection(con);
		}
		}
		

		private boolean checkID(Company c, Connection con) throws SQLException {
			
			Statement stat = con.createStatement();
			String sql = "SELECT * FROM Coupons_System.Company where ID="+c.getId()+";";
			ResultSet rs = stat.executeQuery(sql);
			
			return rs.next();
		}
		
		private boolean checkID(long c, Connection con) throws SQLException {
			
			Statement stat = con.createStatement();
			String sql = "SELECT * FROM Coupons_System.Company where ID="+c+";";
			ResultSet rs = stat.executeQuery(sql);
			boolean q	=	rs.next();
			return q;
		}
		
		private void releaseConnection(Connection con){
			pool.free(con);
		}
	

	@Override
	public void removeCompany(Company c)throws SQLException,DaoException {
		Connection con	=	null;
		try {
			con = pool.getConnection();
			if( !(checkID(c,con)) )throw new DaoException("ID Doesn't exist in the Data Base");
			Statement stat = con.createStatement();
			String sql = "DELETE FROM Coupons_System.Company where ID="+c.getId()+";";
		//	System.out.println("Executing: " + stat.toString());
			int rows = stat.executeUpdate(sql);
			System.out.println(c.getId()+" is Deleted !");
			
		} 
		finally{
			//	3. release connection
			releaseConnection(con);
			}		
	}

	@Override
	public void updateCompany(Company c) {
		Connection con	=	null;
		try {
			con = pool.getConnection();
			if( !(checkID(c,con)) )throw new DaoException("ID Doesn't exist in the Data Base");
			if(c.getCompName().equals(null))throw new DaoException("The name is 'null' !");
			Statement stat = con.createStatement();
			String sql = "update Coupons_System.Company"
						+" SET COMP_NAME=\""+c.getCompName()
						+"\", PASSWORD=\""+c.getPassword()
						+"\", EMAIL=\""+c.getEmail()
						+"\" where ID=\""+c.getId()+"\";";
			//System.out.println("Executing: " + stat.toString());
			int rows = stat.executeUpdate(sql);
			System.out.println(c.getId()+" is updated !");
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch(DaoException e){
			e.printStackTrace();
		}
		finally{
			//	3. release connection
			releaseConnection(con);
			}	
		
	}

	@Override
	public Company getCompany(long id) {
		Connection con	=	null;
		Company c	=	new Company();
		try {
			con = pool.getConnection();
			if( !(checkID(id,con)) )throw new DaoException("ID Doesn't exist in the Data Base");
			//System.out.println(id);
			Statement stat = con.createStatement();
			String sql = "select * from Coupon" 
						+" join Company_Coupon"
						+" on ID=Coupon_ID"
						+" join Company"
						+" on COMP_ID=Company.ID"
						+" where Company.ID="+id+";";
			ResultSet rs = stat.executeQuery(sql);
			rs.next();
			
			c = new Company(
			id,
			rs.getString("COMP_NAME"),
			rs.getString("PASSWORD"),
			rs.getString("EMAIL"),
			null);
			setSaveTheActualID(id);
			c.setCoupons(getCoupons());
			
			//return c;
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		catch (DaoException e){
			System.out.println(e.getMessage());
		}
		finally{
			//	3. release connection
			releaseConnection(con);
			return c;
			}
		//return c;
		}

	@Override
	public Collection<Company> getAllCompany() {

		Collection<Company> c = new ArrayList<Company>();


		Connection con	=	null;
		try {
			con = pool.getConnection();
			Statement stat = con.createStatement();
			String sql = "select ID,COMP_NAME,PASSWORD,EMAIL from Company;";

			//System.out.println("Executing: " + stat.toString());
			ResultSet rs = stat.executeQuery(sql);
			//ResultSetMetaData rsmd=(ResultSetMetaData) stat.executeQuery(sql);
			
			// Every next call - moves the result set to the next
			// record (row). When there no more records, it returns
			// false
			int count=1;
			while (rs.next())
			{
				
				setSaveTheActualID(rs.getLong("ID"));
				Company e	=	new Company(rs.getLong("ID"), rs.getString("COMP_NAME"), rs.getString("PASSWORD"),rs.getString("EMAIL"), getCoupons());
				c.add(e);
//				System.out.println("ID= "+ rs.getString("ID"));
//				System.out.println("COMP_NAME= " + rs.getString("COMP_NAME"));
//				System.out.println("PASSWORD= " + rs.getString("PASSWORD"));
//				System.out.println("-----------------------------------------");
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

	private void setSaveTheActualID(long long1) {
		this.saveTheActualID = long1;
	}


	@Override
	public Collection<Coupon> getCoupons() {
		Collection<Coupon> coupon = new ArrayList<Coupon>();
		CouponType ct = null;
		Connection con	=	null;
		
		try {
			con = pool.getConnection();
			Statement stat = con.createStatement();
			String sql = "SELECT TITLE,START_DATE,END_DATE,AMOUNT,TYPE,MESSAGE,PRICE,IMAGE "
					+"FROM Company cp "
					+"JOIN Company_Coupon cc "
					+"ON cp.ID=cc.COMP_ID "
					+"JOIN Coupon cn "
					+"ON cc.COUPON_ID=cn.ID "
					+"WHERE cc.COMP_ID="+getSaveTheActualID()+";";
			
			//System.out.println("Executing: " + stat.toString());
			ResultSet rs = stat.executeQuery(sql);
			
			if(rs.wasNull())throw new DaoException("Doesn't have coupon");
			while(rs.next())
			{


				Coupon cpn	= new Coupon(
					getSaveTheActualID(),
					rs.getString("TITLE"),
					rs.getDate("START_DATE"),
					rs.getDate("END_DATE"),
					rs.getInt("AMOUNT"),
					CouponType.values()[(rs.getInt("TYPE"))],
					rs.getString("MESSAGE"),
					rs.getDouble("PRICE"),
					rs.getString("IMAGE"));
			coupon.add(cpn);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}catch(DaoException e){
			e.printStackTrace();
		}
		finally{
			releaseConnection(con);
		}
		return coupon;
	}

	private long getSaveTheActualID() {
		return saveTheActualID;
	}


	@Override
	public boolean login(String compName, String password) {
		Connection con	=	null;
		boolean bool 	=	false;
		try{
			con =	pool.getConnection();
			Statement stat = con.createStatement();
			String sql = "SELECT * FROM Coupons_System.Company where COMP_NAME=\""+compName
					+"\" and PASSWORD=\""+password+"\";";
			System.out.println(sql);
			//System.out.println("Executing: " + stat.toString());
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
