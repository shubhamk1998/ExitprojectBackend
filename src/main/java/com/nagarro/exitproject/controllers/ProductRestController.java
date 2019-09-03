package com.nagarro.exitproject.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.nagarro.exitproject.services.ProductsService;

@CrossOrigin
@Path("/products")
public class ProductRestController {

	private Response buildresponse(String response) {
		
		return Response.ok().entity(response)
			      .header("Access-Control-Allow-Origin", "*")
			      .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
			      .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	}


	@Inject
	ProductsService productService;

	@Autowired
	ServletContext context;

	
	@Autowired
    private JavaMailSender mailSenderObj;
	
	
	
	
	@GET
	@Path("/getallproducts")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getallproducts() {
		return buildresponse(this.productService.getallproducts());
	}
	
	@GET
	@Path("/getproductsbyseller")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getproducts(@QueryParam("SellerId") String SellerId) throws ParseException {
		return buildresponse(this.productService.getproductsbysellerid(SellerId));
	}

	@GET
	@Path("/getproduct")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getproduct(@QueryParam("productid") String productid) throws ParseException {
		return buildresponse(this.productService.getproductbyid(productid));
	}
	// Name='+Name+'&longdes='+long_Description+'&shortdes='+short_Description+'&sellercode='+seller_productCode+'&MRP='+MRP+'&SSP='+SSP+'&YMP='+YMP+'&warranty='+warranty+'&MRP='+MRP+'&Images='+Images

	@POST
	@Path("/addproduct")
	@Produces(MediaType.TEXT_PLAIN)
	public Response createproduct(@QueryParam("SellerID") String sellerid,@QueryParam("Name") String Name,@QueryParam("longdes") String longdes,@QueryParam("shortdes") String shortdes,@QueryParam("sellercode") String sellercode,@QueryParam("MRP") Double MRP,@QueryParam("SSP") Double SSP,@QueryParam("YMP") Double YMP,@QueryParam("warranty") String warranty,@QueryParam("Images") String Images,@QueryParam("Dimensions") String dimensions,@QueryParam("Category") String category) throws ParseException {
														System.out.println(sellerid+Name+longdes);
		return buildresponse(this.productService.addproduct(sellerid,Name,longdes,shortdes,sellercode,MRP,SSP,YMP,warranty,Images,dimensions,category));
	}
	
	
	@GET
	@Path("/searchproducts")
	@Produces(MediaType.TEXT_PLAIN)
	public Response searchproducts(@QueryParam("query") String query,@QueryParam("options") String options){
		return buildresponse(this.productService.searchproducts(query,  options));
	}
	
	@GET
	@Path("/filterproducts")
	@Produces(MediaType.TEXT_PLAIN)
	public Response filtersellers(@QueryParam("query") String query,@QueryParam("options") String options) {
		return  buildresponse(this.productService.filtersellers(query, options));	
	}
	
	
	@GET
	@Path("/getcategories")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getcatergories(@QueryParam("query") String query,@QueryParam("options") String options){
		return buildresponse(this.productService.getcategories());
	}
	
	
	@POST
	@Path("/addcategories")
	@Produces(MediaType.TEXT_PLAIN)
	public Response addcatergories(@QueryParam("category") String query){
		return buildresponse(this.productService.addcategories(query));
	}
	
	
	@POST
	@Path("/deletecategories")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deletecatergories(@QueryParam("category") String query){
		return buildresponse(this.productService.deletecategories(query));
	}
	
	
	
	

	@POST      
	@Path("/updatestatus")
	@Produces(MediaType.TEXT_PLAIN)
	public Response updatesellerstatus(@QueryParam("ID") String sellerid,@QueryParam("Value") String value,@QueryParam("comment") String comment) throws ParseException {
		return  buildresponse(this.productService.updatestatus(sellerid,value,comment));
	}
	


	@POST
	@Path("/editproduct")
	@Produces(MediaType.TEXT_PLAIN)
	public Response editproduct(@QueryParam("YmartID") String ymartid,@QueryParam("Name") String Name,@QueryParam("longdes") String longdes,@QueryParam("shortdes") String shortdes,@QueryParam("sellercode") String sellercode,@QueryParam("MRP") Double MRP,@QueryParam("SSP") Double SSP,@QueryParam("YMP") Double YMP,@QueryParam("warranty") String warranty,@QueryParam("Images") String Images,@QueryParam("Dimensions") String dimensions,@QueryParam("Category") String category) throws ParseException {
		return buildresponse(this.productService.editproduct(ymartid,Name,longdes,shortdes,sellercode,MRP,SSP,YMP,warranty,Images,dimensions,category));
	}

	@POST
	@Path("/uploadimage")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {

		String filename = String.valueOf(Math.random() * 20 + 1) + fileDetail.getFileName();
		String uploadedFileLocation = "d://uploaded/" + filename;

		try {
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		String output = uploadedFileLocation;
		return buildresponse(filename);

	}

	@Context
	private HttpServletResponse response;

	@GET
	@Path("/getimage")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public HttpServletResponse download(@QueryParam("imageid") String imageid) throws IOException {
		File file = new File("d://uploaded/" + imageid);
		byte[] data = IOUtils.toByteArray(file.toURI());
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().flush();
		response.getOutputStream().write(data);
		response.getOutputStream().close();
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
		return response;
	}

}
