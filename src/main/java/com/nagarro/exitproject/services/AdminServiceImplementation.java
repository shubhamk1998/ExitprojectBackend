package com.nagarro.exitproject.services;


import com.nagarro.exitproject.dao.AdminsDao;
import com.nagarro.exitproject.models.Admins;
import com.nagarro.exitproject.models.Sellers;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminServiceImplementation implements AdminService {

	private AdminsDao adminsDao;

	public void setAdminsDao(AdminsDao AdminsDao) {
		this.adminsDao = AdminsDao;
	}
	
    @Override
	@Transactional
    public Boolean userAuthentication(String username, String password) {
    	return this.adminsDao.userAuthentication(username,password);
    }

    @Override
    @Transactional
    public Admins getUserDetails(String username) {
    	return this.adminsDao.getUserDetails(username);
    }

}

