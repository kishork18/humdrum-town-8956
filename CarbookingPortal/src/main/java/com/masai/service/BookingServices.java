package com.masai.service;

import java.util.List;

import com.masai.entity.Booking;
import com.masai.entity.Status;
import com.masai.entity.User;
import com.masai.exceptions.RecordNotFoundException;
import com.masai.exceptions.SomthingWentWrongException;

public interface BookingServices {
	public void doBooking(Booking b) throws SomthingWentWrongException;
	public List<Object[]> BookingList()throws SomthingWentWrongException,RecordNotFoundException;
	public void updateBookingStatus(int bookinid,Status s)throws SomthingWentWrongException,RecordNotFoundException;
	public List<Object[]> findUser(User user) throws SomthingWentWrongException,RecordNotFoundException;
	public void cancelBooking(int bookingId)throws SomthingWentWrongException,RecordNotFoundException;
}
