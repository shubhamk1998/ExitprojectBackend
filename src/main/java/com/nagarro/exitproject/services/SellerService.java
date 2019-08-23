package com.nagarro.exitproject.services;

import com.nagarro.exitproject.models.Sellers;
import java.util.List;

import javax.ws.rs.QueryParam;

import com.nagarro.exitproject.models.Admins;

public interface SellerService {

	public Sellers login(String Email, String Password);

	public String register(String Email, String CompanyName, String Address, String GST, String Mobile, String Password,String userName);


	public String getsellers();

	public String getseller(String sellerid);

	public String searchsellers(String query, String options);

	public String filtersellers(String query, String options);

}
