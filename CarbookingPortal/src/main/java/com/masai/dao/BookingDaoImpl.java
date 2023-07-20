package com.masai.dao;


import java.util.ArrayList;
import java.util.List;

import com.masai.entity.Booking;
import com.masai.entity.Status;
import com.masai.entity.User;
import com.masai.exceptions.RecordNotFoundException;
import com.masai.exceptions.SomthingWentWrongException;
import com.masai.utility.EMutils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class BookingDaoImpl implements BookingDao{

	@Override
	public void doBooking(Booking b) throws SomthingWentWrongException {
		// TODO Auto-generated method stub
		EntityManager em=null;
		try {
			em=EMutils.createConnection();
			Query q=em.createQuery("SELECT b\r\n"
					+ "FROM Booking b\r\n"
					+ "WHERE b.user = :userId AND b.car = :carId");
			q.setParameter("userId", b.getUser());
			q.setParameter("carId", b.getCar());
			if(q.getResultList().size()>0) {
				throw new SomthingWentWrongException("This booking is alread availiable in System");
			}
			EntityTransaction et=em.getTransaction();
			et.begin();
			em.persist(b);
			et.commit();
			System.out.println("Your Booking done successfully");
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
	public List<Object[]> BookingList() throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		EntityManager em=null;
		List<Object[]> list= new ArrayList<>();
		try {
			em=EMutils.createConnection();
			Query q=em.createQuery("SELECT \r\n"
					+ "  b.booking_id,\r\n"
					+ "  u.userId,\r\n"
					+ "  u.userName,\r\n"
					+ "  c.car_id,\r\n"
					+ "  c.brand AS carbrand,\r\n"
					+ "  c.model AS carmodel,\r\n"
					+ "  b.booking_date,\r\n"
					+ "  b.status \r\n"
					+ "FROM Booking b \r\n"
					+ "JOIN b.user u \r\n"
					+ "JOIN b.car c  \r\n"
					+ "");
			if(q.getResultList().size()==0) {
				throw new RecordNotFoundException("No record found in system");
			}
			list=q.getResultList();
		} catch (PersistenceException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new SomthingWentWrongException("Somthing went wrong try again later");
		}
		finally {
			em.close();
		}
		return list;
	}

	@Override
	public void updateBookingStatus(int bookinid,Status s) throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		EntityManager em=null;
		try {
			em=EMutils.createConnection();
			Booking b=em.find(Booking.class, bookinid);
			if(b==null) {
				throw new RecordNotFoundException("Record Not found with given ID");
			}
			EntityTransaction et=em.getTransaction();
			et.begin();
			b.setStatus(s);
			et.commit();
			System.out.println("Status Updated successfully");
		} catch (PersistenceException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new SomthingWentWrongException("Somthing Went Wrong Please try again later");
		}
		finally {
			em.close();
		}
		
	}

	@Override
	public List<Object[]> findUser(User user) throws SomthingWentWrongException, RecordNotFoundException {
		EntityManager em=null;
		List<Object[]> list= new ArrayList<>();
		try {
			em=EMutils.createConnection();
			Query q=em.createQuery("SELECT \r\n"
					+ "  b.booking_id,\r\n"
					+ "  u.userId,\r\n"
					+ "  u.userName,\r\n"
					+ "  c.car_id,\r\n"
					+ "  c.brand AS carbrand,\r\n"
					+ "  c.model AS carmodel,\r\n"
					+ "  b.booking_date,\r\n"
					+ "  b.status \r\n"
					+ "FROM Booking b \r\n"
					+ "JOIN b.user u \r\n"
					+ "JOIN b.car c  \r\n"
					+ "WHERE b.user=:user");
			q.setParameter("user", user);
			if(q.getResultList().size()==0) {
				throw new RecordNotFoundException("No record found in system");
			}
			list=q.getResultList();
		} catch (PersistenceException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new SomthingWentWrongException("Somthing went wrong try again later");
		}
		finally {
			em.close();
		}
		return list;
	}
	

}
