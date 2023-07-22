package com.masai.dao;

import java.util.ArrayList;
import java.util.List;

import com.masai.entity.Availability;
import com.masai.entity.Car;
import com.masai.exceptions.RecordNotFoundException;
import com.masai.exceptions.SomthingWentWrongException;
import com.masai.utility.EMutils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class CarDaoImpl implements CarDao{

	@Override
	public void addCar(Car car) throws SomthingWentWrongException {
		// TODO Auto-generated method stub
		EntityManager em=null;
		try {
			em=EMutils.createConnection();
			Query q=em.createQuery("SELECT count(c) FROM Car c WHERE c.model=:model");
			q.setParameter("model", car.getModel());
			if((long)q.getSingleResult()>0) {
				throw new SomthingWentWrongException("Provided model car already exist in system");
			}
			EntityTransaction et=em.getTransaction();
			et.begin();
			em.persist(car);
			et.commit();
			System.out.println("Car added Successfully in System");
		} catch (PersistenceException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new SomthingWentWrongException("Somthing went wrong unable to process");
		}
		finally {
			em.close();
		}
		
	}

	@Override
	public void updateCar(Car car) throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		EntityManager em=null;
		try {
			em=EMutils.createConnection();
			Car carfromDB= em.find(Car.class, car.getCar_id());
			if(carfromDB==null) {
				throw new RecordNotFoundException("No record found with given Id");
			}
			if(!carfromDB.getModel().equals(car.getModel())) {
				Query query = em.createQuery("SELECT count(c) FROM Car c WHERE model = :model");
				query.setParameter("model", car.getModel());
				if((long)query.getSingleResult()>0) {
					throw new SomthingWentWrongException("Record with provided model is already exist in system");
				}
			}
			EntityTransaction et=em.getTransaction();
			et.begin();
			carfromDB.setBrand(car.getBrand());
			carfromDB.setModel(car.getModel());
			carfromDB.setPrice(car.getPrice());
			carfromDB.setAvailable(car.getAvailable());
			et.commit();
			System.out.println("Car updated successfully");
		} catch (PersistenceException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new SomthingWentWrongException("Somthing Went wrong unable to process");
		}finally {
			em.close();
		}
		
	}

	@Override
	public List<Car> carList() throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		EntityManager em=null;
		List<Car> list=new ArrayList<>();
		try {
			em=EMutils.createConnection();
			Query q=em.createQuery("FROM Car c");
			list=(List<Car>)q.getResultList();
			if(list.size()==0) {
				throw new RecordNotFoundException("No record found in system");
			}
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new SomthingWentWrongException("Something Went wrong unable to process");
		}
		finally {
			em.close();
		}
		return list;
	}

	@Override
	public void deleteCar(int id) throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
	    EntityManager em=null;
	    try {
			em=EMutils.createConnection();
			Car car=em.find(Car.class, id);
			if(car==null) {
				throw new RecordNotFoundException("Record not found with given id");
			}
			EntityTransaction et=em.getTransaction();
			et.begin();
			em.remove(car);
			et.commit();
			System.out.println("Car deleted successfully");
		} catch (PersistenceException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new SomthingWentWrongException("Unable to proccess somthing went wrong");
		}
	    finally {
	    	em.close();
	    }
		
	}
	

}
