package com.nagarro.exitproject.dao;

import java.util.List;

import org.hibernate.Session;

import com.nagarro.exitproject.models.Admins;
import com.nagarro.exitproject.models.Sellers;

public interface SellersDao {


	public String login(String Email, String Password) ;


	public String register(String email, String companyName, String address, String gst, String mobile, String password,
			String userName);
	

	public String searchsellers(String query, String options);


	public String filtersellers(String query, String options);

	public String sortsellers(String query, String options);
	


	public String getseller(String sellerid);


	public String updatestatus(String sellerid, String value);


	String getsellers(String page, String count);




	

}
