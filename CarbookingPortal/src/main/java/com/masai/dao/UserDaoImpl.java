package com.masai.dao;

import java.util.ArrayList;
import java.util.List;

import com.masai.entity.ActiveStatus;
import com.masai.entity.LoginUser;
import com.masai.entity.User;
import com.masai.exceptions.RecordNotFoundException;
import com.masai.exceptions.SomthingWentWrongException;
import com.masai.utility.EMutils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class UserDaoImpl implements UserDao{

	@Override
	public void createAccount(User user) throws SomthingWentWrongException {
		EntityManager em=null;
		try {
			em=EMutils.createConnection();
			Query q=em.createQuery("SELECT count(u) FROM User u WHERE userName=:username");
			q.setParameter("username", user.getUserName());
			if((long)q.getSingleResult()>0) {
				throw new SomthingWentWrongException("Username already Exist in System");
			}
			EntityTransaction et=em.getTransaction();
			et.begin();
			em.persist(user);
			et.commit();
			System.out.println("Account has created successfully");
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new SomthingWentWrongException("Somthing went wrong unable to process requist");
		}
		// TODO Auto-generated method stub
		finally {
			em.close();
		}
		
	}

	@Override
	public void logIn(String username, String password) throws SomthingWentWrongException {
		  EntityManager em = null;
		    try {
		        em = EMutils.createConnection();
		        Query q = em.createQuery("SELECT u.userId FROM User u WHERE u.userName = :username AND u.password = :password AND u.isDeleted = :deleted");
		        q.setParameter("username", username);
		        q.setParameter("password", password);
		        q.setParameter("deleted", ActiveStatus.ACTIVE);
		        List<Integer> userlist = q.getResultList();
		        if (userlist.isEmpty()) {
		            throw new SomthingWentWrongException("Something went wrong: Username or password incorrect");
		        }
		        LoginUser.loginUserID = userlist.get(0);
		        System.out.println("Welcome " + username);
		    } catch (IllegalArgumentException | PersistenceException e) {
		        // Log the exception for debugging purposes
		        e.printStackTrace();
		        throw new SomthingWentWrongException("Something went wrong: Unable to process your request");
		    } finally {
		        if (em != null && em.isOpen()) {
		            em.close();
		        }
		    }
		
	}
	@Override
	public void deleteAccount() throws SomthingWentWrongException {
		EntityManager em = null;
		try {
			em = EMutils.createConnection();
			User user = em.find(User.class,LoginUser.loginUserID);
			EntityTransaction et = em.getTransaction();
			et.begin();
			user.setIsDeleted(ActiveStatus.DEACTIVIED);
			et.commit();
			System.out.println("Account deleted");
		}catch(PersistenceException ex) {
			throw new SomthingWentWrongException("Unable to process request, try again later");
		}finally{
			em.close();
		}
		
	}

	@Override
	public List<User> getUserlist() throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		EntityManager em=null;
		List<User> list=new ArrayList<>();
		try {
			em=EMutils.createConnection();
			Query q= em.createQuery("FROM User u");
			list=(List<User>)q.getResultList();
			if(list.size()==0) {
				throw new RecordNotFoundException("No record found in system");
			}
		} catch (PersistenceException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new SomthingWentWrongException("Somthing went wrong try again later");
		}
		return list;
	}

}
