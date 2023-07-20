package com.masai.dao;


import com.masai.entity.Admin;
import com.masai.exceptions.SomthingWentWrongException;

public interface AdminDao {
	public void createAccount(Admin admin) throws SomthingWentWrongException;
	public void logIn(String username,String password) throws SomthingWentWrongException;
}
