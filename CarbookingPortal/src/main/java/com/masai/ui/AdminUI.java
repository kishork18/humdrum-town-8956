package com.masai.ui;

import java.util.List;
import java.util.Scanner;

import com.masai.entity.Availability;
import com.masai.entity.Car;
import com.masai.exceptions.RecordNotFoundException;
import com.masai.exceptions.SomthingWentWrongException;
import com.masai.service.CarServiceImpl;
import com.masai.service.CarServices;

public class AdminUI {
static void displayAdminMenu() {
	System.out.println("Press 1 for add new Car");
	System.out.println("Press 2 for View All car details");
	System.out.println("Press 3 for update car details");
	System.out.println("Press 4 Delete car from System");
	System.out.println("Press 5 for view all booking");
	System.out.println("Press 6 for Update booking Status");
	System.out.println("Press 7 Register new Admin on portal");
	System.out.println("Press 8 for generate daily report");
	System.out.println("Press 9 for view all Reports");
	System.out.println("Press 10 for view all Reports genrated by you");
	System.out.println("Press 11 for view Report by date");
	System.out.println("Press 12 for Delete your Report");
	System.out.println("Press 13 for View All userList");
	System.out.println("Press 14 for Deactivate user Account");
	System.out.println("Press 0 for Logout");
}
static void adminMenu(Scanner sc) {
	int choice;
	do {
		displayAdminMenu();
		choice=sc.nextInt();
      switch(choice) {
         case 1:
     	   addCar(sc);
     	   break;
         case 2:
        	 UserUI.viewCarlist();
        	 break;
         case 3:
        	 updateCar(sc);
        	 break;
	  }
	}while(choice!=0);
	sc.close();
}
public static void addCar(Scanner sc) {
	System.out.println("Enter Model of Car: ");
	 String model=sc.next();
	 System.out.println("Enter Brand of Car: ");
	 String brand=sc.next();
	 System.out.println("Set Price for Booking");
	 double price=sc.nextDouble();
	 Car car=new Car(model, brand, price, Availability.AVAILABLE);
	 CarServices cs=new CarServiceImpl();
	 try {
		cs.addCar(car);
	} catch (SomthingWentWrongException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static void updateCar(Scanner sc) {
	System.out.println("Enter CarId: ");
	int carId=sc.nextInt();
	System.out.println("Enter Price: ");
	double price = sc.nextDouble();
	System.out.println("Enter 1 for set car as Availabe or Enter 2 for set it Unavailabe: ");
	int c=sc.nextInt();
	while(c!=1 && c!=2) {
		System.out.println("Invalid choice please select valid number");
		c=sc.nextInt();
	}
	
	Availability a=c==1?Availability.AVAILABLE:Availability.UNAVAILABLE;
	CarServices cs= new CarServiceImpl();
	try {
		List<Car> list=cs.carList();
		List<Car>l=list.stream().filter(k->k.getCar_id()==carId).toList();
		Car car=l.get(0);
		car.setPrice(price);
		car.setAvailable(a);
		cs.updateCar(car);
	} catch (SomthingWentWrongException | RecordNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
