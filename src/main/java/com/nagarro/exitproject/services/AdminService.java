package com.nagarro.exitproject.services;

import com.nagarro.exitproject.models.Admins;
import com.nagarro.exitproject.models.Sellers;

public interface AdminService {
	public Admins getUserDetails(String username);
    public Boolean userAuthentication(String username, String password);
}
