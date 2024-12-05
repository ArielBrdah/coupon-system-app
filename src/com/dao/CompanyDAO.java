package com.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.beans.Company;
import com.beans.Coupon;

import DaoException.DaoException;

	public interface CompanyDAO {
		
		public void					createCompany(Company c);
		public void					removeCompany(Company c) throws SQLException, DaoException;
		public void					updateCompany(Company c);
		public Company				getCompany(long id);
		public Collection<Company>	getAllCompany();
		public Collection<Coupon> 	getCoupons();
		public boolean				login(String custName,
												String password);
}
