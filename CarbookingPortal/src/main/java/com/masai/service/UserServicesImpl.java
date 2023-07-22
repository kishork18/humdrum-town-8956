package com.masai.service;

import java.util.List;

import com.masai.dao.UserDao;
import com.masai.dao.UserDaoImpl;
import com.masai.entity.User;
import com.masai.exceptions.RecordNotFoundException;
import com.masai.exceptions.SomthingWentWrongException;

public class UserServicesImpl implements UserServices{

	@Override
	public void createAccount(User user) throws SomthingWentWrongException {
		// TODO Auto-generated method stub
		UserDao ud= new UserDaoImpl();
		ud.createAccount(user);
		
	}

	@Override
	public void logIn(String username, String password) throws SomthingWentWrongException {
		// TODO Auto-generated method stub
		UserDao ud= new UserDaoImpl();
		ud.logIn(username, password);
	}

	@Override
	public void deleteAccount(int id) throws SomthingWentWrongException,RecordNotFoundException {
		// TODO Auto-generated method stub
		UserDao ud= new UserDaoImpl();
		ud.deleteAccount(id);
	}

	@Override
	public List<User> getUserlist() throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		UserDao ud= new UserDaoImpl();
		return ud.getUserlist();
	}

}
