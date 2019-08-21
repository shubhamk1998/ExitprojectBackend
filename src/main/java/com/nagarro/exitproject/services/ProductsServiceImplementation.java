package com.nagarro.exitproject.services;

import org.springframework.transaction.annotation.Transactional;

public class ProductsServiceImplementation implements ProductsService{

	@Override
	@Transactional
	public String getproducts(String sellerid) {
		return this.sellersDao.getproducts(sellerid);
	
	}

	@Override
	@Transactional
	public String getproduct(String productid) {
		return this.sellersDao.getproduct(productid);
	}

	

	@Override
	@Transactional
	public String editproduct(String productid) {
		return  this.sellersDao.editproduct(productid);
	}
}
