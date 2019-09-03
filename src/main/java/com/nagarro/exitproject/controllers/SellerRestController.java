package com.nagarro.exitproject.controllers;

import java.text.ParseException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.nagarro.exitproject.services.SellerService;

@CrossOrigin
@Path("/sellers")
public class SellerRestController {

	@Inject
	SellerService sellerService;
	
	private Response buildresponse(String response) {
		
		return Response.ok().entity(response)
			      .header("Access-Control-Allow-Origin", "*")
			      .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
			      .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	}

	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public Response login(@QueryParam("Email") String Email,@QueryParam("Password") String Password)  {
		
		return buildresponse(this.sellerService.login(Email,Password));
		
		  
	}
	
	
	
	
	@POST
	@Path("/register")
	@Produces(MediaType.TEXT_PLAIN)
	public Response register(@QueryParam("Email") String Email,@QueryParam("CompanyName") String CompanyName,@QueryParam("Address") String Address,@QueryParam("GST") String GST,@QueryParam("Mobile") String Mobile,@QueryParam("Password") String Password,@QueryParam("userName") String userName) throws ParseException {
		String response = this.sellerService.register(Email, CompanyName, Address,  GST,  Mobile, Password, userName);
		return buildresponse(response);
	}
	
	
	@GET
	@Path("/getsellers")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getsellers(@QueryParam("page") String page,@QueryParam("count") String count) throws ParseException {
		return buildresponse(this.sellerService.getsellers(page, count));
	}
	
	
	@GET      
	@Path("/getseller")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getseller(@QueryParam("sellerid") String sellerid) throws ParseException {
		return  buildresponse(this.sellerService.getseller(sellerid));
	}
	
	
	@POST      
	@Path("/updatestatus")
	@Produces(MediaType.TEXT_PLAIN)
	public Response updatesellerstatus(@QueryParam("ID") String sellerid,@QueryParam("Value") String value) throws ParseException {
		return  buildresponse(this.sellerService.updatestatus(sellerid,value));
	}
	
	
	@GET
	@Path("/filtersellers")
	@Produces(MediaType.TEXT_PLAIN)
	public Response filtersellers(@QueryParam("query") String query,@QueryParam("options") String options) {
		return  buildresponse(this.sellerService.filtersellers(query, options));	
	}

	@GET
	@Path("/sortsellers")
	@Produces(MediaType.TEXT_PLAIN)
	public Response sortsellers(@QueryParam("query") String query,@QueryParam("options") String options) {
		return  buildresponse(this.sellerService.sortsellers(query, options));
	}
	
	
	@GET
	@Path("/searchsellers")
	@Produces(MediaType.TEXT_PLAIN)
	public Response searchsellers(@QueryParam("query") String query,@QueryParam("options") String options) {
		return  buildresponse(this.sellerService.searchsellers(query, options));
	}
	

}
