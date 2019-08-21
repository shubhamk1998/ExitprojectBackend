package com.nagarro.exitproject.dao;

import java.util.List;

import org.hibernate.Session;

import com.nagarro.exitproject.models.Admins;
import com.nagarro.exitproject.models.Sellers;

public interface SellersDao {


	public Sellers login(String Email, String Password) ;


	public String register(String email, String companyName, String address, String gst, String mobile, String password,
			String userName);
	
	public String addproduct(String name,String shortDes, String longDes, String dimensions, Double  mrp , Double ssp, Double ymp, String images ) ;
	
	public String editproduct(String productid, String name,String shortDes, String longDes, String dimensions, Double  mrp , Double ssp, Double ymp, String images );


	public String getproducts(String sellerid);


	public String getproduct(String productid);


	public String searchproducts(String query, String options);


	public String filterproducts(String query, String options);


	

}
