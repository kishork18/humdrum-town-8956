package com.masai.service;

import java.util.List;

import com.masai.entity.User;
import com.masai.exceptions.RecordNotFoundException;
import com.masai.exceptions.SomthingWentWrongException;

public interface UserServices {
	
		public void createAccount(User user) throws SomthingWentWrongException;
		public void logIn(String username,String password) throws SomthingWentWrongException;
		public void deleteAccount(int id) throws SomthingWentWrongException;
		public  List<User> getUserlist() throws SomthingWentWrongException,RecordNotFoundException;
}
