package com.masai.service;

import java.util.List;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.entity.Admin;
import com.masai.exceptions.RecordNotFoundException;
import com.masai.exceptions.SomthingWentWrongException;

public class AdminServicesImpl implements AdminServices{

	@Override
	public void createAccount(Admin admin) throws SomthingWentWrongException {
		// TODO Auto-generated method stub
		AdminDao ad=new AdminDaoImpl();
		ad.createAccount(admin);
		
	}

	@Override
	public void logIn(String username, String password) throws SomthingWentWrongException {
		// TODO Auto-generated method stub
		AdminDao ad=new AdminDaoImpl();
		ad.logIn(username, password);
		
	}

	@Override
	public List<Admin> getAdminList() throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		AdminDao ad=new AdminDaoImpl();
		return ad.getAdminList();
	}

}
