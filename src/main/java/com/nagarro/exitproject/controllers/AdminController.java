package com.nagarro.exitproject.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nagarro.exitproject.models.Sellers;
import com.nagarro.exitproject.services.AdminService;

@Controller
public class AdminController {

	private AdminService AdminService;

	@Autowired(required = true)
	@Qualifier(value = "AdminService")
	public void setAdminService(AdminService ls) {
		this.AdminService = ls;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirect(HttpServletRequest request) {
		request.getSession().setAttribute("message", "");
		return "login";
	}

//	@RequestMapping(value = "/Login", method = RequestMethod.POST)
//	public String login(HttpServletRequest request, @RequestParam String username, @RequestParam String password,
//			ModelAndView m) {
//		if (this.loginService.userAuthentication(username, password)) {
//			Sellers user = this.loginService.getUserDetails(username);
//			request.getSession().setAttribute("authorized", true);
//			request.getSession().setAttribute("user", user.getFullName());
//			return "index";
//		} else {
//			request.setAttribute("authorized", false);
//			request.getSession().setAttribute("message", "Wrong Credentials");
//
//			return "login";
//		}
//	}
}
