package com.nagarro.exitproject.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.exitproject.models.Products;
import com.nagarro.exitproject.models.Sellers;

public class ProductsDaoImplementation implements ProductsDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public String getproducts(String sellerid) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Products.class);
		cr.add(Restrictions.eq("", sellerid));
		List<Sellers> results = cr.list();
		tx.commit();
		return results.toString();
	}

	@Override
	@Transactional
	public String getproduct(String productid) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Sellers.class);
		cr.add(Restrictions.eq("ID", productid));
		List<Sellers> results = cr.list();
		tx.commit();
		return results.toString();
	}
	
	
	@Override
	@Transactional
	public String searchproducts(String query, String options) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Sellers.class);

		// Filter by Name
		if(options.equals("1") ) {
			cr.add(Restrictions.like("Name", query));
		}
		//Filter by Seller Product Code
		else if ( options.equals("2")){
			cr.add(Restrictions.eq("SellerProductCode", query));	
		}
		//Filter By YourMart Code
		else {
			cr.add(Restrictions.eq("YmartId", query));				
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
		Criteria cr = session.createCriteria(Sellers.class);
		
		// Filter by Status
		if(options.equals("1") ) {
			cr.add(Restrictions.eq("Status", query));
		}
		
		//Filter by SellerID
		else if ( options.equals("2")){
			cr.add(Restrictions.eq("SellerProductCode", query));	
		}
		
		//Filter By SellerCode
		else if ( options.equals("3")){
			cr.add(Restrictions.eq("SellerCode",query));	
		}
		//Filter By Category
		else {
			cr.add(Restrictions.eq("Category", query));				
		}	
		List<Products> list  = cr.list();
		tx.commit();
		return list.toString();

	}
	
    
	
	@Override
	@Transactional
	public String addproduct(String name,String shortDes, String longDes, String dimensions, Double  mrp , Double ssp, Double ymp, String images ) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Sellers.class);
		cr.add(Restrictions.eq("Name", name));
		Products product = (Products)cr.uniqueResult() ;
		if(product.getName()!=null) 
		{
			tx.commit();
			return "Already Exists";
		}
		else {
			Products newproduct = new Products();
			newproduct.setName(name);
			newproduct.setDimensions(dimensions);
			newproduct.setLongDes(longDes);
			newproduct.setMRP(mrp);
			newproduct.setSSP(ssp);
			newproduct.setYMP(ymp);
			newproduct.setImages(images);
			newproduct.setStatus("NEW");
			session.save(newproduct);
			tx.commit();
			return "Created";
		}
		
	}

	@Override
	@Transactional
	public String editproduct(String productid, String name,String shortDes, String longDes, String dimensions, Double  mrp , Double ssp, Double ymp, String images ) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Sellers.class);
		cr.add(Restrictions.eq("ID", productid));
		Products product = (Products)cr.uniqueResult();
		if(("").equals(product.getName())) {
			return "Product Not Found";
		}
		product.setName(name);
		product.setDimensions(dimensions);
		product.setLongDes(longDes);
		product.setMRP(mrp);
		product.setSSP(ssp);
		product.setYMP(ymp);
		product.setImages(images);
		product.setStatus("NEW");
		session.save(product);
		tx.commit();
		return "Updated";
	}

	
	
	
}
