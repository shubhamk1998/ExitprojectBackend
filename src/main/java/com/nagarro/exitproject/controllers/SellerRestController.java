package com.nagarro.exitproject.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import com.nagarro.exitproject.models.Sellers;
import com.nagarro.exitproject.services.SellerService;

import org.glassfish.jersey.media.multipart.FormDataParam;

import com.nagarro.exitproject.models.Admins;

@Path("/sellers")
public class SellerRestController {

	@Inject
	SellerService sellerService;

	
	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public String getuser(@QueryParam("Email") String Email,@QueryParam("Password") String Password) throws ParseException {
		Sellers seller = this.sellerService.login(Email,Password);
		return seller.toString();
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.TEXT_PLAIN)
	public String register(@QueryParam("Email") String Email,@QueryParam("CompanyName") String CompanyName,@QueryParam("Address") String Address,@QueryParam("GST") String GST,@QueryParam("Mobile") String Mobile,@QueryParam("Password") String Password,@QueryParam("userName") String userName) throws ParseException {
		String response = this.sellerService.register(Email, CompanyName, Address,  GST,  Mobile, Password, userName);
		return response;
	}
	
	
	@POST
	@Path("/getsellers")
	@Produces(MediaType.TEXT_PLAIN)
	public String getsellers(@QueryParam("Email") String Email,@QueryParam("CompanyName") String CompanyName,@QueryParam("Address") String Address,@QueryParam("GST") String GST,@QueryParam("Mobile") String Mobile,@QueryParam("Password") String Password,@QueryParam("userName") String userName) throws ParseException {
		return this.sellerService.getSellers();
	}
	
	
	@POST
	@Path("/getseller")
	@Produces(MediaType.TEXT_PLAIN)
	public String getseller(@QueryParam("sellerid") String sellerid) throws ParseException {
		String response = this.sellerService.getseller(sellerid);
		return response;
	}
	
	
	
	
	

//	@DELETE
//	@Path("/deleteproduct")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String deleteUser(@QueryParam("id") String id) throws ParseException {
//		return this.sellerService.deleteUser(productid);
//	}
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public String bulkupload(@FormDataParam("file") InputStream uploadedInputStream)
			throws UnsupportedEncodingException, IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(uploadedInputStream, "UTF-8"))) {
			String csvdata = br.lines().collect(Collectors.joining(System.lineSeparator())).substring(1);
			String[] lines = csvdata.split("\n");
			String str = "";
			Boolean error = false;
			for (String line : lines) {
				String[] data = line.split(",");
				String Name = data[0];
				String Email = data[1];
				String dOB = data[2];
				String location = data[3];
				if ((this.sellerService.CreateUser(Name, Email, dOB, location).equals("Error"))) {
					error = true;
					str = str + "," + Email;
				}
			}
			if (!error) {
				str = str + "Created";
			}
			return str;
		}

	}

//	@GET
//	@Path("/downloadCSV")
//	public Response downloadCSV() {
//		StreamingOutput fileStream = new StreamingOutput() {
//			@Override
//			public void write(java.io.OutputStream output) throws IOException, WebApplicationException {
//				try {
//					output.write(("Name,Email,DOB,Location"+"\n").getBytes());
//					List<Admins> users = sellerService.getAllUsers();
//					for (Admins flight : users) {
//						output.write((flight.getName() + "," + flight.getEmail() + "," + flight.getDOB() + ","
//								+ flight.getLocation() + "\n").getBytes());
//					}
//					output.flush();
//				} catch (Exception e) {
//
//				}
//			}
//		};
//		return Response.ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)
//				.header("content-disposition", "attachment; filename = Employees.csv").build();
//
//	}

}
