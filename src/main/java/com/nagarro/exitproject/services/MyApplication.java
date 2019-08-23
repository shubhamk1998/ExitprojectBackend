package com.nagarro.exitproject.services;


import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.nagarro.exitproject.controllers.ProductRestController;
import com.nagarro.exitproject.controllers.SellerRestController;
import com.nagarro.exitproject.dao.ProductsDao;
import com.nagarro.exitproject.dao.ProductsDaoImplementation;
import com.nagarro.exitproject.dao.SellersDao;
import com.nagarro.exitproject.dao.SellersDaoImplementation;

public class MyApplication extends ResourceConfig {

	/**
	 * Register JAX-RS application components.
	 */
	public MyApplication() {
		register(MultiPartFeature.class);
		register(RequestContextFilter.class);
		register(SellerRestController.class);
		register(ProductRestController.class);
		 register(new AbstractBinder() {
	            @Override
	            protected void configure() {
            	    bind(SellersDaoImplementation.class).to(SellersDao.class);
	            	    bind(SellerServiceImplementation.class).to(SellerService.class);
	            	    bind(ProductsDaoImplementation.class).to(ProductsDao.class);
	            	    bind(ProductsServiceImplementation.class).to(ProductsService.class);
	            }
	        });
	}
}