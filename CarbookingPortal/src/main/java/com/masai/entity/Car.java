package com.masai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "car")
public class Car {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int car_id;
@Column(length = 50,nullable = false,unique = true)
private String model;
@Column(length = 50,nullable = false)
private String brand;
@Column(nullable = false)
private double price;
@Column(name = "availability",nullable = false)
@Enumerated
private Availability available;
public Car() {
	super();
	// TODO Auto-generated constructor stub
}
public Car(String model, String brand, double price, Availability available) {
	super();
	this.model = model;
	this.brand = brand;
	this.price = price;
	this.available = available;
}
public int getCar_id() {
	return car_id;
}
public void setCar_id(int car_id) {
	this.car_id=car_id;
}
public String getModel() {
	return model;
}
public void setModel(String model) {
	this.model = model;
}
public String getBrand() {
	return brand;
}
public void setBrand(String brand) {
	this.brand = brand;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public Availability getAvailable() {
	return available;
}
public void setAvailable(Availability available) {
	this.available = available;
}
@Override
public String toString() {
	return "Car [car_id=" + car_id + ", model=" + model + ", brand=" + brand + ", price=" + price + ", available="
			+ available + "]";
}

}
