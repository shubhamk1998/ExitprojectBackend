package com.nagarro.exitproject.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.exitproject.dao.SellersDao;
import com.nagarro.exitproject.models.Admins;
import com.nagarro.exitproject.models.Sellers;
import com.nagarro.exitproject.models.Products;

@Service
@Transactional
public class SellerServiceImplementation implements SellerService {

	@Inject
	SellersDao sellersDao;

	@Override
	@Transactional
	public Sellers login(String Email, String Password) {
		return this.sellersDao.login(Email, Password);
	}

	@Override
	@Transactional
	public String register(String Email, String CompanyName, String Address, String GST, String Mobile, String Password,
			String userName) {
		return  this.sellersDao.register(Email, CompanyName, Address,  GST,  Mobile, Password, userName);
	}
	
	@Override
	@Transactional
	public String getsellers() {
		return this.sellersDao.getsellers();
	}
	
	@Override
	@Transactional
	public String getseller(String sellerid) {
		return this.sellersDao.getseller(sellerid);
	}
	
	
	@Override
	@Transactional
	public String searchsellers(String query, String options) {
		return this.sellersDao.searchsellers(query,options);
		
	}
	
	
	@Override
	@Transactional
	public String filtersellers(String query, String options) {
		return this.sellersDao.filtersellers(query,options);
	}



//	@Override
//	@Transactional
//	public String deleteUser(@QueryParam("id") String id) throws ParseException {
//		return this.sellerService.deleteUser(id);
//	}

}
