package com.masai.service;

import java.util.List;

import com.masai.dao.BookingDao;
import com.masai.dao.BookingDaoImpl;
import com.masai.entity.Booking;
import com.masai.entity.Status;
import com.masai.entity.User;
import com.masai.exceptions.RecordNotFoundException;
import com.masai.exceptions.SomthingWentWrongException;

public class BookingServicesImpl implements BookingServices{

	@Override
	public void doBooking(Booking b) throws SomthingWentWrongException {
		// TODO Auto-generated method stub
		BookingDao bd=new BookingDaoImpl();
		bd.doBooking(b);
		
	}

	@Override
	public List<Object[]> BookingList() throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		BookingDao bd=new BookingDaoImpl();
		return bd.BookingList();
	}

	@Override
	public void updateBookingStatus(int bookinid, Status s) throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		BookingDao bd=new BookingDaoImpl();
		bd.updateBookingStatus(bookinid, s);
		
	}

	@Override
	public List<Object[]> findUser(User user) throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		BookingDao bd=new BookingDaoImpl();
		return bd.findUser(user);
	}

	@Override
	public void cancelBooking(int bookingId) throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		BookingDao bd=new BookingDaoImpl();
		bd.cancelBooking(bookingId);
		
	}

}
