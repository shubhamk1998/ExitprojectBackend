package com.nagarro.exitproject.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.Column;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.nagarro.exitproject.models.Admins;
import com.nagarro.exitproject.models.Products;
import com.nagarro.exitproject.models.Sellers;

@Repository
@Transactional
public class SellersDaoImplementation implements SellersDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public String login(String Email, String Password) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Sellers.class);
		cr.add(Restrictions.eq("email", Email));
		cr.add(Restrictions.eq("password", Password));
		Sellers seller = (Sellers)cr.uniqueResult();
		tx.commit();
		
		JSONObject obj = new JSONObject();
		if(seller==null) {
			obj.put("Status","User Not Found");
		}
		else {
			obj.put("Status","User Found");
			obj.put("User", seller.toString());
		}
		return obj.toString();
	}

	@Override
	@Transactional
	public String register(String Email, String CompanyName, String Address, String GST, String Mobile, String Password,
			String userName) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Sellers.class);
		cr.add(Restrictions.eq("email", Email));
		Sellers sellerx = (Sellers)cr.uniqueResult();
		if(sellerx!=null) {
			return "Seller Already Exists";
		}
		Sellers seller = new Sellers();
		seller.setUserName(userName);
		seller.setEmail(Email);
		seller.setCreated( new Date().toInstant().toString() );
		seller.setAddress(Address);
		seller.setCompanyName(CompanyName);
		seller.setMobile(Mobile);
		seller.setStatus("NEED_APPROVAL");
		seller.setPassword(Password);
		seller.setUserName(userName);
		session.save(seller);
		tx.commit();
		return "Created";
	}
	

	@Override
	@Transactional
	public String getsellers(String page, String count) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Sellers.class);
		cr.add(Restrictions.eq("status", "NEED_APPROVAL"));
		List<Sellers> list = cr.list();
		tx.commit();
		
		 tx = session.beginTransaction();
			Criteria cr1 = session.createCriteria(Sellers.class);
			cr1.add(Restrictions.eq("status", "APPROVED"));
			List<Sellers> list1 = cr1.list();
			tx.commit();
			
			 tx = session.beginTransaction();
				Criteria cr2 = session.createCriteria(Sellers.class);
				cr2.add(Restrictions.eq("status", "REJECTED"));
				List<Sellers> list2 = cr2.list();
				tx.commit();
				list.addAll(list1);
				list.addAll(list2);


//		Criteria cr1 = session.createCriteria(Sellers.class);
//		cr.add(Restrictions.eq("status", "APPROVED"));
//		List<Sellers> list1 = cr1.list();
//		Session session2 = this.sessionFactory.getCurrentSession();
//		Transaction tx2 = session2.beginTransaction();
//		Criteria cr2 = session.createCriteria(Sellers.class);
//		cr.add(Restrictions.eq("status", "REJECTED"));
//		List<Sellers> list2 = cr2.list();
//		list.addAll(list1);
//		list.addAll(list2);
//		cr.setFirstResult(Integer.parseInt(page));
//		cr.setMaxResults(Integer.parseInt(count));
		session.close();

		return list.toString();
	}

	@Override
	public String searchsellers(String query, String options) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Sellers.class);
		
		// Filter by Name
		if(options.equals("1") ) {
			cr.add(Restrictions.like("companyName", query,MatchMode.START));
		}
		//Filter by Seller Product Code
		else if ( options.equals("2")){
			cr.add(Restrictions.like("userName", query,MatchMode.START));	
		}
		//Filter By YourMart Code
		else {
			cr.add(Restrictions.like("mobile", query,MatchMode.START));				
		}	
		List<Products> list  = cr.list();
		tx.commit();
		return list.toString();
	}

	@Override
	@Transactional
	public String filtersellers(String query, String options) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Sellers.class);
		cr.add(Restrictions.eq("status", query));
		List<Sellers> list  = cr.list();
		tx.commit();
		return list.toString();
	}
	
	@Override
	@Transactional
	public String sortsellers(String query, String options) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Sellers.class);
		cr.add(Restrictions.eq("Status", query));
		List<Sellers> list  = cr.list();
		tx.commit();
		return list.toString();
	}


	@Override
	@Transactional
	public String getseller(String sellerid) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Sellers.class );
		cr.add(Restrictions.eq("sellerId",Long.parseLong(sellerid)));
	    Sellers seller = (Sellers)cr.uniqueResult();
		tx.commit();
		return seller.toString();
	}

	@Override
	@Transactional
	public String updatestatus(String sellerid, String value) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Sellers.class );
		cr.add(Restrictions.eq("sellerId", Long.parseLong(sellerid)));
	    Sellers seller = (Sellers)cr.uniqueResult();
	    seller.setStatus(value);
	    session.saveOrUpdate(seller);
	    tx.commit();
		return "Updated";
	}




}
