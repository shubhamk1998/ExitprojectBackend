package com.nagarro.exitproject.services;

import java.util.List;

public interface ProductsService {




	


	String getallproducts();

	String editproduct(String sellerid, String name, String longdes, String shortdes, String sellercode, Double mRP,
			Double sSP, Double yMP, String warranty, String images, String dimensions, String category);

	String getproductsbysellerid(String sellerId);

	String getproductbyid(String productid);



	String searchproducts(String query, String options);



	String addproduct(String sellerid, String name, String longdes, String shortdes, String sellercode, Double mRP,
			Double sSP, Double yMP, String warranty, String images, String dimensions, String category);






	String getcategories();






	String filtersellers(String query, String options);












	String updatestatus(String sellerid, String value, String comment);

	String addcategories(String query);

	String deletecategories(String query);

}
