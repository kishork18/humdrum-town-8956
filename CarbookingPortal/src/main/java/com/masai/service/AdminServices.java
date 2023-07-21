package com.masai.service;

import java.util.List;

import com.masai.entity.Admin;
import com.masai.exceptions.RecordNotFoundException;
import com.masai.exceptions.SomthingWentWrongException;

public interface AdminServices {
	public void createAccount(Admin admin) throws SomthingWentWrongException;
	public void logIn(String username,String password) throws SomthingWentWrongException;
	public List<Admin> getAdminList()throws SomthingWentWrongException, RecordNotFoundException;
}
