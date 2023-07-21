package com.masai.service;

import java.util.List;

import com.masai.entity.Car;
import com.masai.exceptions.RecordNotFoundException;
import com.masai.exceptions.SomthingWentWrongException;

public interface CarServices {
	public void addCar(Car car) throws SomthingWentWrongException;
	public void updateCar(Car car) throws SomthingWentWrongException,RecordNotFoundException;
	public List<Car> carList()throws SomthingWentWrongException,RecordNotFoundException;
	public void deleteCar(int id) throws SomthingWentWrongException,RecordNotFoundException;
	public List<Car> onlyAvailiable() throws SomthingWentWrongException,RecordNotFoundException;
	public List<Car> acendingOrder()throws SomthingWentWrongException,RecordNotFoundException;
	public List<Car> decendingOrder()throws SomthingWentWrongException,RecordNotFoundException;
}
