package com.dao;

import java.sql.SQLException;
import java.util.Collection;
import com.beans.Company;
import com.beans.Coupon;
import com.beans.Customer;

import DaoException.DaoException;

public interface CustomerDAO {

	public void					createCustomer(Customer c) throws DaoException;
	public void					removeCustomer(Customer c) throws DaoException;
	public void					updateCustomer(Customer c) throws DaoException;
	public Customer				getCustomer(long id) throws DaoException;
	public Collection<Customer>	getAllCustomer() throws DaoException;
	public Collection<Coupon> 	getCoupons()throws DaoException;
	public boolean				login(String CustName,
											String Password)throws DaoException;
}
