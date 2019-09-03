package com.nagarro.exitproject.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.exitproject.models.Categories;
import com.nagarro.exitproject.models.Products;

public class ProductsDaoImplementation implements ProductsDao {

	

	@Autowired
    private JavaMailSender mailSenderObj;
	
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public String getproduct(String productid) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Products.class);
		cr.add(Restrictions.eq("YmartId", Long.parseLong(productid)));
		List<Products> results = cr.list();
		tx.commit();
		return results.toString();
	}
	
	
	@Override
	@Transactional
	public String searchproducts(String query, String options) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Products.class);

		// Filter by Name
		if(options.equals("1") ) {
			cr.add(Restrictions.like("sellerproductCode", query,MatchMode.START));
		}
		//Filter by Seller Product Code
		else if ( options.equals("2")){
			cr.add(Restrictions.like("name", query,MatchMode.START));	
		}
		//Filter By YourMart Code
		else {
			cr.add(Restrictions.eq("YmartId",Long.parseLong(query)));				
		}	
		List<Products> list  = cr.list();
		tx.commit();
		return list.toString();

	}
	
	
	@Override
	@Transactional
	public String filterproducts(String query, String options) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Products.class);
		cr.add(Restrictions.eq("status", query));			
		List<Products> list  = cr.list();
		tx.commit();
		return list.toString();

	}
	

	@Override
	public String addproduct(String sellerid,String name, String longdes, String shortdes, String sellercode, Double mRP, Double sSP,
			Double yMP, String warranty, String images, String dimensions,String category) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
			Products newproduct = new Products();
			newproduct.setName(name);
			newproduct.setDimensions(dimensions);
			newproduct.setLongDes(longdes);
			newproduct.setSellerCode(sellerid);
			newproduct.setSellerproductCode(sellercode);
			newproduct.setShortDes(shortdes);
			newproduct.setMRP(mRP);
			newproduct.setSSP(sSP);
			newproduct.setCreated(new Date().toInstant().toString());
			newproduct.setYMP(yMP);
			newproduct.setImages(images);
			newproduct.setStatus("NEED_APPROVAL");
			newproduct.setWarranty(warranty);
			newproduct.setCategories(category);

			session.save(newproduct);
			tx.commit();
			return "Created";
	}

	

	@Override
	public String editproduct(String sellerid, String name, String longdes, String shortdes, String sellercode,
			Double mRP, Double sSP, Double yMP, String warranty, String images, String dimensions, String category) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Products.class);
		cr.add(Restrictions.eq("YmartId", Long.parseLong(sellerid)));
		Products product = (Products)cr.uniqueResult();
		if(("").equals(product.getName())) {
			return "Product Not Found";
		}
		product.setName(name);
		product.setCategories(category);
		product.setDimensions(dimensions);
		product.setLongDes(longdes);
		product.setMRP(mRP);
		product.setSSP(sSP);
		product.setYMP(yMP);
		product.setImages(images);
		product.setStatus("REVIEW");
		session.save(product);
		tx.commit();
		return "Updated";
	}

	@Override
	@Transactional
	public String getproductsbysellerid(String sellerid) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Products.class);
		cr.add(Restrictions.eq("sellerCode", sellerid));
		List<Products> results = cr.list();
		tx.commit();
		return results.toString();
	}
	
	@Override
	public String getallproducts() {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Products.class);
		List<Products> results = cr.list();
		tx.commit();
		return results.toString();		
	}

	@Override
	public String getcategories() {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();		
		Criteria cr = session.createCriteria(Categories.class);
		List<Categories> results = cr.list();
		tx.commit();
		return results.toString() ;		
	}
	

	@Override
	public String updatestatus(String sellerid, String value,final String comment) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Products.class);
		cr.add(Restrictions.eq("YmartId", Long.parseLong(sellerid)));
		Products product = (Products)cr.uniqueResult();
		product.setStatus(value);
		if(value.equals("REJECTED")) {
			if(product.getComments()==null) {
				ArrayList  temp = new ArrayList();
				temp.add(comment);
				product.setComments(temp);
			} else {
				ArrayList temp = product.getComments();
				temp.add(comment);
				product.setComments(temp);
			}
				
			SimpleMailMessage message = new SimpleMailMessage();
			
			message.setFrom("sk9331657@gmail.com");
			message.setTo("sk9331657@gmail.com");
			message.setSubject("subject");
			message.setText(comment);
			mailSenderObj.send(message);
			
		}
		session.save(product);
		tx.commit();
		return "Updated";
	}


	@Override
	public String addcategories(String query) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Categories.class);
		Categories category = new Categories();
		category.setName(query);
		session.save(category);		
		tx.commit();
		return "Created";
	}


	@Override
	public String deletecategories(String query) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Categories.class);
		cr.add(Restrictions.eq("Name", query));
		Categories category =  (Categories) cr.uniqueResult();
		session.delete(category);	
		tx.commit();
		return "Deleted";
	}



	


	

	
	
	
}
