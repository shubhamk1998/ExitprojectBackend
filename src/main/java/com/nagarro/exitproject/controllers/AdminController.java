package com.nagarro.exitproject.controllers;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.nagarro.exitproject.services.AdminService;


@CrossOrigin
@Path("/admin")
public class AdminController {

	
	private Response buildresponse(String response) {
		
		return Response.ok().entity(response)
			      .header("Access-Control-Allow-Origin", "*")
			      .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
			      .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	}

	private AdminService AdminService;

	@Autowired(required = true)
	@Qualifier(value = "AdminService")
	public void setAdminService(AdminService ls) {
		this.AdminService = ls;
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public Response login(@QueryParam("Email") String Email,@QueryParam("Password") String Password) {
		if (this.AdminService.userAuthentication(Email, Password)) {
			JSONObject obj = new JSONObject();
			obj.put("User", this.AdminService.getUserDetails(Email).toString() );
			obj.put("Status","User Found");
			return  buildresponse(obj.toString());
		} else {
			return  buildresponse("No User Found");
		}
		
	}

	

	
	
	
	
	
	
	
	
	
	
}
