package com.nagarro.exitproject.dao;

import com.nagarro.exitproject.models.Sellers;

public interface AdminsDao {
    public Sellers getUserDetails(String username);
    public Boolean userAuthentication(String username, String password);
}
