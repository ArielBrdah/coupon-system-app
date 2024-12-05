package com.beans;

import com.coupons.CouponType;
import java.sql.Date;

public class Coupon {

				//====================
				// 		Fields
				//====================

		private long		id; 		// Id of the coupon
		private String		title;		// Short name that explain coupon
		private java.sql.Date		startDate;	// Coupons created
		private java.sql.Date		endDate;	// Coupons expired
		private int			amount;		// Number of coupons
		private CouponType 	type;		// Category
		private String 		message;	// Explain the coupons
		private double 		price;		// Price of the coupons
		private String 		image;		// Real picture of the product
		
				//====================
				// 		Attributes
				//====================
			
		//===============================
		//		~Constructor
		//===============================
		public Coupon(){}
		
		public Coupon(long id,java.sql.Date endDate, double price){
			this.id=id;
			this.endDate=endDate;
			this.price=price;
		}

		public Coupon(long id, String title, java.sql.Date startDate, java.sql.Date endDate, int amount, CouponType type,
				String message, double price, String image) {
			this.id = id;
			this.title = title;
			this.startDate = startDate;
			this.endDate = endDate;
			this.amount = amount;
			this.type = type;
			this.message = message;
			this.price = price;
			this.image = image;
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

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Date getStartDate() {
			return startDate;
		}

		public void setStartDate(java.sql.Date startDate) {
			this.startDate = startDate;
		}

		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}

		public int getAmount() {
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}

		public CouponType getType() {
			return type;
		}

		public void setType(CouponType type) {
			this.type = type;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}
		
			//===============================
			//		To string Method
			//===============================
			
		@Override
		public String toString(){
			
			return 	"\nId: "		+this.getId()
					+" Title: "		+this.getTitle()
					+" Start Date: "+this.getStartDate()
					+" End Date: "	+this.getEndDate()
					+" Amount: "	+this.getAmount()
					+" Type: "		+this.getType()
					+" Message: "	+this.getMessage()
					+" Price: "		+this.getPrice()
					+" Image: "		+this.getImage()+"\n";
		}
}
