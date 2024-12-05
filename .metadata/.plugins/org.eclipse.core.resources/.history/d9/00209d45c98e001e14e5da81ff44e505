package com.beans;

import java.util.Collection;

public class Customer {
				
				//====================
				// 		Fields
				//====================
			
			private long 				id;			// Authentification
			private String				custName;	// Name 
			private String 				password;	// Password
			private Collection<Coupon>	coupons;	// Coupons purchased by customers
						
				//====================
				// 		Methods
				//====================
			
			//===============================
			//			~Constructor
			//===============================
			
			public Customer() {
			}
			
			public Customer(long id, String custName,
					String password, Collection<Coupon> coupons) {

				this.id = id;
				this.custName = custName;
				this.password = password;
				this.coupons = coupons;
			}


			//==============================
			//	Getters And Setters Methods
			//==============================


			public long getId() {
				return id;
			}

			public void setId(long id) {
				this.id = id;
			}

			public String getCustName() {
				return custName;
			}

			public void setCustName(String custName) {
				this.custName = custName;
			}

			public String getPassword() {
				return password;
			}

			public void setPassword(String password) {
				this.password = password;
			}

			public Collection<Coupon> getCoupons() {
				return coupons;
			}

			public void setCoupons(Collection<Coupon> coupons) {
				this.coupons = coupons;
			}
			
			//===============================
			//		To string Method
			//===============================
			
			@Override
			public String toString(){
				return "\n id=" + getId() 
				+ ", custName=" + getCustName()
				+ ", password=" + getPassword() 
				+ ", \ncoupons=" + getCoupons()
				+ "\n";
				
			}
}
