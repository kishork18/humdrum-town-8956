package com.masai.dao;

import java.util.ArrayList;
import java.util.List;

import com.masai.entity.Admin;
import com.masai.entity.LoginAdmin;
import com.masai.exceptions.RecordNotFoundException;
import com.masai.exceptions.SomthingWentWrongException;
import com.masai.utility.EMutils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class AdminDaoImpl implements AdminDao{

	@Override
	public void createAccount(Admin admin) throws SomthingWentWrongException {
		// TODO Auto-generated method stub
		EntityManager em=null;
		try {
			em=EMutils.createConnection();
			Query q=em.createQuery("SELECT count(a) FROM Admin a WHERE userName=:username");
			q.setParameter("username", admin.getUserName());
			if((long)q.getSingleResult()>0) {
				throw new SomthingWentWrongException("Username already Exist in System");
			}
			EntityTransaction et=em.getTransaction();
			et.begin();
			em.persist(admin);
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
		        Query q = em.createQuery("SELECT a.admin_id FROM Admin a WHERE a.userName = :username AND a.password = :password");
		        q.setParameter("username", username);
		        q.setParameter("password", password);
		        List<Integer> userlist = q.getResultList();
		        if (userlist.isEmpty()) {
		            throw new SomthingWentWrongException("Something went wrong: Username or password incorrect");
		        }
		        LoginAdmin.loginAdminId = userlist.get(0);
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
	public List<Admin> getAdminList() throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		EntityManager em=null;
		List<Admin> list= new ArrayList<>();
		try {
			em=EMutils.createConnection();
			Query q=em.createQuery("FROM Admin a");
			list=(List<Admin>)q.getResultList();
			if(list.size()==0) {
				throw new RecordNotFoundException("No record found in System");
			}
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new SomthingWentWrongException("Somthing went wrong try again later");
		}
		finally {
			em.close();
		}
		
		return list;
	}
	
}
