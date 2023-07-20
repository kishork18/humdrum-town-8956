package com.masai.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int booking_id;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id",nullable = false)
private User user;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "car_id",nullable = false)
private Car car;
private LocalDate booking_date;
@Enumerated
private Status status;
public Booking() {
	super();
	// TODO Auto-generated constructor stub
}
public Booking(User user, Car car, LocalDate booking_date, Status status) {
	super();
	this.user = user;
	this.car = car;
	this.booking_date = booking_date;
	this.status = status;
}
public int getBooking_id() {
	return booking_id;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Car getCar() {
	return car;
}
public void setCar(Car car) {
	this.car = car;
}
public LocalDate getBooking_date() {
	return booking_date;
}
public void setBooking_date(LocalDate booking_date) {
	this.booking_date = booking_date;
}
public Status getStatus() {
	return status;
}
public void setStatus(Status status) {
	this.status = status;
}

}
