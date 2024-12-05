package com.dao.dbimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.Exception.existException;
import com.beans.Company;
import com.beans.Coupon;
import com.coupons.CouponType;
import com.dao.CouponDAO;

import com.mysql.jdbc.PreparedStatement;
import com.pool.ConnectionPool;
import com.system.CouponSystem;

import DaoException.DaoException;

public class CouponDBDAO implements CouponDAO{

	private ConnectionPool 		pool			= 	CouponSystem.getConnectionPool();
	private static final String TABLE_NAME		=	"Coupon";
	private String 				TABLE_NAME_CC	=	null;
	private String				TABLE_NAME_C	=	null;
	private long				CC_ID			=	0l;
	
	
	
	
	public void setCC_ID(long id)
	{
		CC_ID=id;
	}
	
	public long getCC_ID()
	{
		return CC_ID;
	}
	
	public void setTableNameCC(String str)
	{
		TABLE_NAME_CC=str;
	}
	
	public String getTableNameCC()
	{
		return TABLE_NAME_CC;
	}
	
	public void setTableNameC(String str)
	{
		TABLE_NAME_C=str;
	}
	
	public String getTableNameC()
	{
		return TABLE_NAME_C;
	}
	
	@Override
	public void createCoupon(Coupon c) throws existException,SQLException{
//		1. get a connection from a pool
		   
			Connection con	=	null;

			try{		
				con	=	pool.getConnection();
			
			//	2. Condition if Exist ID
			//			 If is the Same ID:
			//			{	rs.next() == true } == checkID()
			
			if( checkID(c,con) )throw new existException("ID of the Coupons Exist in the Data Base");
			//if(	checkName(c,con))throw new existException("The Coupon with same name exist!");

			//java.sql.Date endDate	=	new java.sql.Date(c.getEndDate().getYear(),c.getEndDate().getMonth(),c.getEndDate().getDay());
			//java.sql.Date startDate	=	new java.sql.Date(c.getStartDate().getYear(),c.getStartDate().getMonth(),c.getStartDate().getDay());
			//java.sql.Types type		=	c.getType().Types;
			//	3. create sql insert
			PreparedStatement statm = (PreparedStatement) con.prepareStatement(
						"INSERT INTO "+ TABLE_NAME + " VALUES (?,?,?,?,?,?,?,?,?)");
				statm.setLong(5, c.getAmount());
				statm.setDate(4, c.getEndDate());
				statm.setLong(1, c.getId());
				statm.setString(9, c.getImage());
				statm.setString(7, c.getMessage());
				statm.setLong(8, (long) c.getPrice());
				statm.setDate(3, c.getStartDate());
				statm.setString(2, c.getTitle());
				statm.setInt(6, c.getType().ordinal());
			
		//		System.out.println("Executing: " + statm.toString());
				statm.executeUpdate();
				
				System.out.println(c.getTitle()+" coupons inserted in the Db !");
			}
//			catch(SQLException e){
//				e.printStackTrace();
//			}
//			catch (existException e) {
//				e.printStackTrace();
//			}
			finally{
			//	3. release connection
			releaseConnection(con);
			}
		
	}
	private boolean checkName(Coupon c, Connection con) throws SQLException {
		
		Statement stat = con.createStatement();
		String sql = "SELECT * FROM Coupons_System.Coupon where TITLE=\""+c.getTitle()+"\";";
		ResultSet rs = stat.executeQuery(sql);
		boolean q	=	rs.next();
		return q;
	}
	private boolean checkID(Coupon c, Connection con) throws SQLException {
		
		Statement stat = con.createStatement();
		String sql = "SELECT * FROM Coupons_System.Coupon where ID="+c.getId()+";";
		ResultSet rs = stat.executeQuery(sql);
		return	rs.next();
	}
	
	private boolean checkID(long c, Connection con) throws SQLException {
		
		Statement stat = con.createStatement();
		String sql = "SELECT * FROM Coupons_System.Coupon where ID="+c+";";
		ResultSet rs = stat.executeQuery(sql);
		return	rs.next();
	}
	
	private void releaseConnection(Connection con){
		pool.free(con);
	}

	@Override
	public void removeCoupon(Coupon c) {
		Connection con	=	null;
		try {
			con = pool.getConnection();
			if( !(checkID(c,con)) )throw new DaoException("ID Doesn't exist in the Data Base");
			Statement stat = con.createStatement();
			String sql = "DELETE FROM Coupon"
				+ " where ID="+c.getId()+";";
	//		System.out.println("Executing: " + stat.toString());
			int rows = stat.executeUpdate(sql);
			System.out.println(c.getId()+" is Deleted !");
			
		} 
		catch (SQLException e) {
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

	@Override
	public void updateCoupon(Coupon c) throws SQLException, DaoException {
		Connection con	=	null;
		try {
			con = pool.getConnection();
			if( !(checkID(c,con)) )throw new DaoException("ID Doesn't exist in the Data Base");
			Statement stat = con.createStatement();
			CouponType ct = c.getType();
			String sql = "update Coupons_System.Coupon"
						+" SET END_DATE=\""+c.getEndDate()
						+"\", PRICE=\""+c.getPrice()
						+"\" where ID="+c.getId()+";";
	//		System.out.println("Executing: " + stat.toString());
			int rows = stat.executeUpdate(sql);
			System.out.println(c.getId()+" is updated !");
			
		} 
//		catch (SQLException e) {
//			e.printStackTrace();
//		}
//		catch(DaoException e){
//			e.printStackTrace();
//	}
		finally{
			//	3. release connection
			releaseConnection(con);
			}	
				
	}

	@Override
	public Coupon getCoupon(long id){
		Connection con	=	null;
		Coupon c		=	null;

		try {
			con = pool.getConnection();
			if( !(checkID(id,con)) )throw new existException("ID Doesn't exist in the Data Base");
			//System.out.println(id);
			Statement stat = con.createStatement();
			String sql = "SELECT * FROM Coupons_System.Coupon where ID="+id+";";
			ResultSet rs = stat.executeQuery(sql);
			rs.next();
			c = new Coupon();
			c.setType(CouponType.values()[(rs.getInt("TYPE"))]);
			c.setTitle((rs.getString("TITLE")));
			c.setStartDate((rs.getDate("START_DATE")));
			c.setEndDate((rs.getDate("END_DATE")));
			c.setPrice((rs.getDouble("PRICE")));
			c.setMessage((rs.getString("MESSAGE")));
			c.setImage((rs.getString("IMAGE")));
			c.setId(rs.getLong("ID"));
			c.setAmount((rs.getInt("AMOUNT")));
			
			
		} 
		catch (SQLException | existException e) {
			e.printStackTrace();
		}
		finally{
			//	3. release connection
			releaseConnection(con);
			}
		return c;
	}

	@Override
	public Collection<Coupon> getAllCoupon() {
		Collection<Coupon> c = new ArrayList<Coupon>();


		Connection con	=	null;
		try {
			con = pool.getConnection();
			Statement stat = con.createStatement();
			
//			select * from Coupon
//			join Company_Coupon
//			on ID=Coupon_ID
//			join Company
//			on COMP_ID=Company.ID
//			where Company.ID=1;
			String sql	=	null;
			if(getTableNameCC().equals("Company_Coupon")){
			sql = "select * from Coupon"
					+"	join "	+	getTableNameCC()
					+"	on Coupon.ID="	+	getTableNameCC()	+	".COUPON_ID"
					+"	join "	+	getTableNameC()
					+"	on COMP_ID=Company.ID"
					+"	where Company.ID="	+	getCC_ID()	+	";"	;
			}
			
			if(getTableNameCC().equals("Customer_Coupon")){
				sql = "select * from Coupon"
						+"	join "	+	getTableNameCC()
						+"	on Coupon.ID="	+	getTableNameCC()	+	".COUPON_ID"
						+"	join "	+	getTableNameC()
						+"	on CUST_ID=Customer.ID"
						+"	where Customer.ID="	+	getCC_ID()	+	";"	;
			}

	//		System.out.println("Executing: " + stat.toString());
			ResultSet rs = stat.executeQuery(sql);
			//ResultSetMetaData rsmd=(ResultSetMetaData) stat.executeQuery(sql);
			
			// Every next call - moves the result set to the next
			// record (row). When there no more records, it returns
			// false
			int count=1;
			while (rs.next())
			{		
				Coupon e	=	new Coupon(
						rs.getLong("ID"),
						rs.getString("TITLE"), 
						rs.getDate("START_DATE"),
						rs.getDate("END_DATE"),
						rs.getInt("AMOUNT"),
						CouponType.values()[(rs.getInt("TYPE"))],
						rs.getString("MESSAGE"),
						rs.getDouble("PRICE"),
						rs.getString("IMAGE"));
						
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
	public Collection<Coupon> getCouponByType(CouponType ct) {
		Collection<Coupon> c = new ArrayList<Coupon>();
		Coupon e	=null;

		Connection con	=	null;
		try {
			con = pool.getConnection();
			Statement stat = con.createStatement();
			String sql	=	"";
			if(getTableNameCC().equals("Company_Coupon")){
			
				//set ordinal to CouponType
				//when we want to request on Db
				sql = "select * from Coupon"
					+" join "	+	getTableNameCC()
					+" on Coupon.ID="	+	getTableNameCC()	+	".COUPON_ID"
					+" join "	+	getTableNameC()
					+" on COMP_ID=Company.ID"
					+" where Company.ID="	+	getCC_ID()
					+" and TYPE=\""	+ CouponType.valueOf(ct.toString()).ordinal()	+	"\";"	;
			//System.out.println(sql);
	 			}
			
			if(getTableNameCC().equals("Customer_Coupon")){
				sql = "select * from Coupon"
						+"	join "	+	getTableNameCC()
						+"	on Coupon.ID="	+	getTableNameCC()	+	".COUPON_ID"
						+"	join "	+	getTableNameC()
						+"	on CUST_ID=Customer.ID"
						+"	where Company.ID="	+	getCC_ID()	
						+"	and TYPE=\""	+ CouponType.valueOf(ct.toString()).ordinal()	+	"\";"	;

			}

	//		System.out.println("Executing: " + stat.toString());
			ResultSet rs = stat.executeQuery(sql);
			//ResultSetMetaData rsmd=(ResultSetMetaData) stat.executeQuery(sql);
			
			// Every next call - moves the result set to the next
			// record (row). When there no more records, it returns
			// false
			int count=1;
			while (rs.next())
			{
			//	System.out.println(rs.next());
				
				// to set a Object Coupon 
				// need to write the String Type
				// CouponType.values()[getInt()]
				e	=	new Coupon(
						rs.getLong("ID"),
						rs.getString("TITLE"), 
						rs.getDate("START_DATE"),
						rs.getDate("END_DATE"),
						rs.getInt("AMOUNT"),
						CouponType.values()[(rs.getInt("TYPE"))],
						rs.getString("MESSAGE"),
						rs.getDouble("PRICE"),
						rs.getString("IMAGE"));
						
				c.add(e);
			}
			

		} 
		catch (SQLException d) {
			d.printStackTrace();
		}
		finally{
			//	3. release connection
			releaseConnection(con);

		}
		return c;
		
	}
	

}
