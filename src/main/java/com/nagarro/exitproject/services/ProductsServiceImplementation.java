package com.nagarro.exitproject.services;

import java.util.List;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;

import com.nagarro.exitproject.dao.ProductsDao;

public class ProductsServiceImplementation implements ProductsService{

	@Inject
	ProductsDao productsDao;

	
	

	

	@Override
	@Transactional
	public String addproduct(String sellerid,String name, String longdes, String shortdes, String sellercode, Double mRP,
			Double sSP, Double yMP, String warranty, String images,String dimensions,String category) {
		return this.productsDao.addproduct(sellerid,name,  longdes,  shortdes, sellercode,mRP, sSP, yMP ,warranty,images, dimensions,category);
	}



	@Override
	@Transactional
	public String getallproducts() {
		return this.productsDao.getallproducts();
	}



	@Override
	@Transactional
	public String getproductsbysellerid(String sellerid) {
		return this.productsDao.getproductsbysellerid(sellerid);
	}

	@Override
	@Transactional
	public String getproductbyid(String productid) {
		return this.productsDao.getproduct(productid);
	}




	@Override
	public String searchproducts(String query, String options) {
		return this.productsDao.searchproducts(query, options);
	}



	@Override
	public String getcategories() {
		return this.productsDao.getcategories();
	}



	@Override
	public String filtersellers(String query, String options) {
		return this.productsDao.filterproducts(query,options);
	}


	


	@Override
	public String updatestatus(String sellerid, String value, String comment) {
		return this.productsDao.updatestatus(sellerid, value,comment);
	}



	@Override
	public String editproduct(String sellerid, String name, String longdes, String shortdes, String sellercode,
			Double mRP, Double sSP, Double yMP, String warranty, String images, String dimensions, String category) {
	return 	this.productsDao.editproduct(sellerid,name,longdes,shortdes,sellercode,mRP,sSP,yMP,warranty,images,dimensions,category);
	}



	@Override
	public String addcategories(String query) {
		return this.productsDao.addcategories(query);
	}



	@Override
	public String deletecategories(String query) {
		return this.productsDao.deletecategories(query);
	}

	

	


}
