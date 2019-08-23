package com.nagarro.exitproject.services;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;

import com.nagarro.exitproject.dao.ProductsDao;

public class ProductsServiceImplementation implements ProductsService{

	@Inject
	ProductsDao productsDao;

	
	@Override
	@Transactional
	public String getproducts() {
		return this.productsDao.getproducts();
	}

	@Override
	@Transactional
	public String getproduct(String productid) {
		return this.productsDao.getproduct(productid);
	}

	@Override
	public String editproduct(String productid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addproduct(String productid) {
		// TODO Auto-generated method stub
		return null;
	}

	


}
