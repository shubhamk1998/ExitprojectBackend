package com.nagarro.exitproject.services;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;

import com.nagarro.exitproject.dao.ProductsDao;

public class ProductsServiceImplementation implements ProductsService{

	@Inject
	ProductsDao productsDao;

	
	@Override
	@Transactional
	public String getproducts(String sellerid) {
		return this.productsDao.getproducts(sellerid);
	
	}

	@Override
	@Transactional
	public String getproduct(String productid) {
		return this.productsDao.getproduct(productid);
	}

	

	@Override
	@Transactional
	public String editproduct(String productid) {
		return  this.productsDao.editproduct(productid);
	}
}
