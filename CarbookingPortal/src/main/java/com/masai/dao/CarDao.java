package com.masai.dao;

import java.util.List;

import com.masai.entity.Car;
import com.masai.exceptions.RecordNotFoundException;
import com.masai.exceptions.SomthingWentWrongException;

public interface CarDao {
public void addCar(Car car) throws SomthingWentWrongException;
public void updateCar(Car car) throws SomthingWentWrongException,RecordNotFoundException;
public List<Car> carList()throws SomthingWentWrongException,RecordNotFoundException;
public void deleteCar(int id) throws SomthingWentWrongException,RecordNotFoundException;


}
