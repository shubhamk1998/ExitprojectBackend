package com.nagarro.exitproject.services;

import com.nagarro.exitproject.models.Sellers;
import java.util.List;

import javax.ws.rs.QueryParam;

import com.nagarro.exitproject.models.Admins;

public interface SellerService {

	public Sellers login(String Email, String Password);

	public String register(String Email, String CompanyName, String Address, String GST, String Mobile, String Password,String userName);

	public String getproduct(String productid);

	public String editproduct(String productid);
	
	public String getproducts(String sellerid);

	public String getsellers();

	public String getseller(String sellerid);

}
