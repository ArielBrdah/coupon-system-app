package com.system;

import java.sql.Connection;
import java.sql.SQLException;

import com.pool.ConnectionPool;
import com.Thread.DailyCouponExpirationTask;
import com.clientType.*;
import com.facade.*;

public class CouponSystem {
	private static CouponSystem cs;
	private static ConnectionPool pool;
	private static int i = 0;
	private static DailyCouponExpirationTask task;
	private Thread t1;
	
	static{
		String url = "jdbc:mysql://localhost:3306/Coupons_System";//"jdbc:mysql://localhost:3306"; //+ dbName;
		String username = "root";
		String password = "Blaireau1";
		try {
			pool = new ConnectionPool(url, username, password);
		} catch (SQLException e) {
			System.out.println("Cannot create connection pool. "
					+ "reason: " + e.getMessage());
			System.exit(1);
		}
	}
	
	private CouponSystem(){
		task	=	new DailyCouponExpirationTask();
		t1	=	new Thread(task, "dailyCoupon");
		t1.start();
	}
	
	public static void shutdown(){
		task.stopTask();
		pool.closeAllConnections();
	}
	
	public static ConnectionPool getConnectionPool()
	{
		return pool;
	}
	
	public static CouponClientFacade login(String name,String password,clientType ct){
		
		switch(ct){
		case AdminFacade:
			new CouponSystem();
			return AdminFacade.getInstance( name,  password,  ct);
				
		case CompanyFacade:
			new CouponSystem();
			return CompanyFacade.getInstance(name, password, ct);
		
		case CustomerFacade:
			new CouponSystem();
			return CustomerFacade.getInstance(name, password, ct);
		
		default:
				return null;
		}
		
		
	}
}
