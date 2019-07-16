package com.nagarro.restapp.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.nagarro.restapp.models.Flights;
import com.nagarro.restapp.services.FlightService;

@Path("/Users")
public class FlightController {

	@Inject
	FlightService flightService;

	@GET
	@Path("/getuser")
	@Produces(MediaType.TEXT_PLAIN)
	public String getuser(@QueryParam("id") String id) throws ParseException {
		List<Flights> allflights = this.flightService.getUser(id);
		return allflights.toString();
	}

	@GET
	@Path("/deleteuser")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(@QueryParam("id") String id) throws ParseException {
		return this.flightService.deleteUser(id);
	}

	@GET
	@Path("/edituser")
	@Produces(MediaType.TEXT_PLAIN)
	public String editUser(@QueryParam("id") String id, @QueryParam("Name") String Name,
			@QueryParam("Email") String Email, @QueryParam("DOB") String dOB, @QueryParam("Location") String location) {
		return this.flightService.editUser(id, Name, Email, dOB, location);
	}

	@GET
	@Path("/createuser")
	@Produces(MediaType.TEXT_PLAIN)
	public String createUser(@QueryParam("Name") String Name, @QueryParam("Email") String Email,
			@QueryParam("DOB") String dOB, @QueryParam("Location") String location) {
		return this.flightService.CreateUser(Name, Email, dOB, location);
	}

	@GET
	@Path("/getallusers")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllUsers() {
		return this.flightService.getAllUsers().toString();
	}

}
