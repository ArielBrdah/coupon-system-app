package com.beans;

import java.util.Collection;
import java.sql.Date;

public class Company {
			
				//====================
				// 		Fields
				//====================
	
		private long 				id;			// Auhtentification
		private String				compName;	// Name 
		private String 				password;	// Password
		private String				email;		// E-mail
		private Collection<Coupon>	coupons;	// Coupons belong to the company
		
				//====================
				// 		Attributes
				//====================
	
			//===============================
			//			~Constructor
			//===============================

		public Company(){}
		public Company(long id, String compName, String password, String email, Collection<Coupon> coupons) {

					this.id = id;
					this.compName = compName;
					this.password = password;
					this.email = email;
					this.coupons = coupons;}

			//==============================
			//	Getters And Setters Methods
			//==============================


		public long getId() {
						return id;
					}

		public void setId(long id) {
						this.id = id;
					}

		public String getCompName() {
						return compName;
					}

		public void setCompName(String compName) {
						this.compName = compName;
					}

		public String getPassword() {
						return password;
					}

		public void setPassword(String password) {
						this.password = password;
					}

		public String getEmail() {
						return email;
					}

		public void setEmail(String email) {
						this.email = email;
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
		public String toString() {
			return "\nid=" + getId() 
					+ ", compName=" + getCompName()
					+ ", password=" + getPassword() 
					+ ", email=" + getEmail()
					+ "\ncoupons=" + getCoupons();
		}
					
}
