package com.nagarro.exitproject.services;

import com.nagarro.exitproject.models.Sellers;

public interface AdminService {
	public Sellers getUserDetails(String username);
    public Boolean userAuthentication(String username, String password);
}
