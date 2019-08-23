package com.nagarro.exitproject.controllers;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.server.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.ServletContextResource;

import com.nagarro.exitproject.services.ProductsService;

@Path("/products")
public class ProductRestController {

	@Inject
	ProductsService productService;

	@Autowired
	ServletContext context;

	@GET
	@Path("/getproducts")
	@Produces(MediaType.TEXT_PLAIN)
	public String getproducts(@QueryParam("SellerId") String SellerId) throws ParseException {
		return this.productService.getproducts();
	}

	@GET
	@Path("/getproduct")
	@Produces(MediaType.TEXT_PLAIN)
	public String getproduct(@QueryParam("productid") String productid) throws ParseException {
		return this.productService.editproduct(productid);
	}

	@POST
	@Path("/addproduct")
	@Produces(MediaType.TEXT_PLAIN)
	public String createproduct(@QueryParam("productid") String productid) throws ParseException {
		return this.productService.addproduct(productid);
	}

	@POST
	@Path("/editproduct")
	@Produces(MediaType.TEXT_PLAIN)
	public String editproduct(@QueryParam("productid") String productid) throws ParseException {
		return this.productService.editproduct(productid);
	}

	@POST
	@Path("/uploadimage")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		String uploadedFileLocation = "d://uploaded/" + String.valueOf(Math.random() * 20 + 1)
				+ fileDetail.getFileName();
		String filename = fileDetail.getFileName();
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

		return Response.status(200).entity(uploadedFileLocation).build();

	}
	
	
	
//	public Response getFile(@QueryParam("imageid") String imageid) {
//
//		File file = new File("d://uploaded/"+imageid);
//
//		ResponseBuilder response = Response.ok((Object) file);
//		response.header("Content-Disposition",
//			"attachment; filename=image_from_server.png");
//			response.header("Content-Type", "image/jpeg");
//		return response.build();
//
//	}
//	
	
	
	@Context
	private HttpServletResponse response;
	 

	
	@GET
    @Path( "/getimage" )
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
    public HttpServletResponse download( @QueryParam("imageid") String imageid ) throws IOException 
    {
	        File file = new File( "d://uploaded/"+imageid);
	        byte[] data = IOUtils.toByteArray( file.toURI() ) ;
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().flush();
			response.getOutputStream().write(data);
			response.getOutputStream().close();
			return response;
    }

}
