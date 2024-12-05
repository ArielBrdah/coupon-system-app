package com.Thread;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.beans.Coupon;
import com.dao.CompanyDAO;
import com.dao.CouponDAO;
import com.dao.CustomerDAO;
import com.pool.ConnectionPool;
import com.system.CouponSystem;

public class DailyCouponExpirationTask implements Runnable{

				//Fields
	
	private CouponDAO	coup;
	private CustomerDAO	cust;
	private CompanyDAO	comp;
	private boolean 	quit	=	false;
	private ConnectionPool pool			=	CouponSystem.getConnectionPool();
	
	
				//~constructor
	
	public DailyCouponExpirationTask(){}
	
	
	
//	String STR_START	=	"1980-01-02";	
//	String STR_END	=	"1988-01-3";	
//	DateFormat df		=	new SimpleDateFormat("yyyy-mm-dd");
//	java.util.Date d1	=	df.parse(STR_START);
//	java.util.Date d2	=	df.parse(STR_END);
//			new java.sql.Date(d1.getTime()),
//			new java.sql.Date(d2.getTime()),
	@Override
	public void run() {
		ArrayList<Coupon> a	=	new ArrayList<Coupon>(); 
		Connection con	=	null;
		try {
			con	=	pool.getConnection();
			Statement stat	=	con.createStatement();
			String	sql	=	"select ID from Coupon"
					+" where END_DATE<CURDATE();";
			ResultSet rs	=	stat.executeQuery(sql);
			
			while(rs.next()){
				Coupon e	=	new Coupon();
				e.setId(rs.getLong("ID"));
				a.add(e);
			}

			//System.out.println(stat.getResultSet());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			releaseConnection(con);
		}
		while(quit==false){
			con	=	null;
	try {
		con	=	pool.getConnection();
		Statement stat	=	con.createStatement();
		String	sql	=	"delete from Coupon"
				+" where END_DATE<CURDATE();";
		stat.executeUpdate(sql);

	//	System.out.println(stat.getResultSet());
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		releaseConnection(con);
	}
	
	for(Coupon coupon:a){
	con	=	null;
	try {
		con	=	pool.getConnection();
		Statement stat	=	con.createStatement();
		String	sql	=	"delete from Company_Coupon"
				+" where COUPON_ID="+coupon.getId()+";";
		stat.executeUpdate(sql);

		//System.out.println(stat.getResultSet());
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		releaseConnection(con);
	}
	con	=	null;
	try {
		con	=	pool.getConnection();
		Statement stat	=	con.createStatement();
		String	sql	=	"delete from Customer_Coupon"
				+" where COUPON_ID="+coupon.getId()+";";
		stat.executeUpdate(sql);

		//System.out.println(stat.getResultSet());
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		releaseConnection(con);
	}
	}
		}
	}

	private void releaseConnection(Connection con) {
		pool.free(con);
	}



	public void stopTask(){
	quit	=	true;	
	}
}
