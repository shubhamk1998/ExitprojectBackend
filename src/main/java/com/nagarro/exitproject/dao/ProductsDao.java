package com.nagarro.exitproject.dao;

interface ProductsDao {

	String getproducts(String sellerid);

	String getproduct(String productid);

	String searchproducts(String query, String options);

	String filterproducts(String query, String options);

	String addproduct(String name, String shortDes, String longDes, String dimensions, Double mrp, Double ssp,
			Double ymp, String images);

}
