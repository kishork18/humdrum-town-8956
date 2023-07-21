package com.masai.dao;

import java.util.List;

import com.masai.entity.User;
import com.masai.exceptions.RecordNotFoundException;
import com.masai.exceptions.SomthingWentWrongException;

public interface UserDao {
public void createAccount(User user) throws SomthingWentWrongException;
public void logIn(String username,String password) throws SomthingWentWrongException;
public void deleteAccount(int id) throws SomthingWentWrongException;
public  List<User> getUserlist() throws SomthingWentWrongException,RecordNotFoundException;
}
