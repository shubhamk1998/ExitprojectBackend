package com.nagarro.exitproject.dao;

import java.util.List;

public interface ProductsDao {


	String getproduct(String productid);

	String searchproducts(String query, String options);

	String filterproducts(String query, String options);



	String editproduct(String sellerid, String name, String longdes, String shortdes, String sellercode, Double mRP,
			Double sSP, Double yMP, String warranty, String images, String dimensions, String status);

	String getproductsbysellerid(String sellerid);


	
	String getallproducts();

	String addproduct(String sellerid, String name, String longdes, String shortdes, String sellercode, Double mRP,
			Double sSP, Double yMP, String warranty, String images, String dimensions, String category);

	String getcategories();

	String updatestatus(String sellerid, String value, String comment);

	String addcategories(String query);

	String deletecategories(String query);



}
