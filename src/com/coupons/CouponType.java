package com.coupons;

public enum CouponType {
RESTAURANTS("RESTAURANTS"),
ELECTRICITY("ELECTRICITY"),
FOOD("FOOD"),
HEALTH("HEALTH"),
SPORTS("SPORTS"),
CAMPING("CAMPING"),
TRAVELLING("TRAVELLING"),
HITECH("HITECH");

	private int coup=0;
	
	CouponType(String str){
		coup=getCoupons(str);
	}
	
public int getCoupons(String str){
	switch(str){
	case "RESTAURANTS":
		return 1;
	case "ELECTRICITY":
		return 2;
	case "FOOD":
		return	 3;
	case "HEALTH":
		return	 4;
	case "SPORTS":
		return	 5;
	case "CAMPING":
		return	 6;
	case "TRAVELLING":
		return	 7;
	case "HITECH":
		return 8;
	default: return	0;
	}
	}

}
