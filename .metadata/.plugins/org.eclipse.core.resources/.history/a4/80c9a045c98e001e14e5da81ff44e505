package com.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.Exception.existException;
import com.beans.Coupon;
import com.coupons.CouponType;

import DaoException.DaoException;

public interface CouponDAO {

	public void					createCoupon(Coupon c) throws existException, SQLException;
	public void					removeCoupon(Coupon c);
	public void					updateCoupon(Coupon c) throws SQLException, DaoException;
	public Coupon				getCoupon(long id) throws SQLException, existException;
	public Collection<Coupon>	getAllCoupon();
	public Collection<Coupon> 	getCouponByType(CouponType ct);

}
