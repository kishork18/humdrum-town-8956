package com.masai.service;

import java.util.List;

import com.masai.dao.CarDao;
import com.masai.dao.CarDaoImpl;
import com.masai.entity.Availability;
import com.masai.entity.Car;
import com.masai.exceptions.RecordNotFoundException;
import com.masai.exceptions.SomthingWentWrongException;

public class CarServiceImpl implements CarServices{

	@Override
	public void addCar(Car car) throws SomthingWentWrongException {
		// TODO Auto-generated method stub
		CarDao cd=new CarDaoImpl();
		cd.addCar(car);
		
	}

	@Override
	public void updateCar(Car car) throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		CarDao cd=new CarDaoImpl();
		cd.updateCar(car);
		
	}

	@Override
	public List<Car> carList() throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		CarDao cd=new CarDaoImpl();
		return cd.carList();
	}

	@Override
	public void deleteCar(int id) throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		CarDao cd=new CarDaoImpl();
		cd.deleteCar(id);
	}

	@Override
	public List<Car> onlyAvailiable() throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		CarDao cd=new CarDaoImpl();
		List<Car> list=cd.carList().stream().filter(c->c.getAvailable()==Availability.AVAILABLE).toList();
		return list;
	}

	@Override
	public List<Car> acendingOrder() throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		CarDao cd=new CarDaoImpl();
		List<Car> list=cd.carList().stream().sorted((a,b)->(int)(a.getPrice()-b.getPrice())).toList();
		return list;
	}

	@Override
	public List<Car> decendingOrder() throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		CarDao cd=new CarDaoImpl();
		List<Car> list=cd.carList().stream().sorted((a,b)->(int)(b.getPrice()-a.getPrice())).toList();
		return list;
	}

}
