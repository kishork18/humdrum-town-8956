package com.masai.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.masai.entity.ActiveStatus;
import com.masai.entity.Booking;
import com.masai.entity.Car;
import com.masai.entity.LoginUser;
import com.masai.entity.Status;
import com.masai.entity.User;
import com.masai.exceptions.RecordNotFoundException;
import com.masai.exceptions.SomthingWentWrongException;
import com.masai.service.BookingServices;
import com.masai.service.BookingServicesImpl;
import com.masai.service.CarServiceImpl;
import com.masai.service.CarServices;
import com.masai.service.UserServices;
import com.masai.service.UserServicesImpl;

public class UserUI {
static void registerUser(Scanner sc) {
 System.out.println("Enter UserName: ");
 String userName=sc.next();
 System.out.println("Enter Password: ");
 String password=sc.next();
 System.out.println("Enter Email: ");
 String email=sc.next();
 System.out.println("Enter Mobile No: ");
 String mobileNo=sc.next();
 User u=new User(userName, password, email, mobileNo, ActiveStatus.ACTIVE);
 UserServices us=new UserServicesImpl();
 try {
	us.createAccount(u);
} catch (SomthingWentWrongException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	System.out.println(e.getMessage());
}
}
static void userLogin(Scanner sc) {
	System.out.print("Enter username ");
	String username = sc.next();
	System.out.print("Enter password ");
	String password = sc.next();
	UserServices us=new UserServicesImpl();
	try {
		us.logIn(username, password);
		usermenu(sc);
	} catch (SomthingWentWrongException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
static void displayMenu() {
	System.out.println("Press 1 for View CarList");
	System.out.println("Press 2 for View CarList in asending order of Price");
	System.out.println("Press 3 for View CarList in decending order of Price");
	System.out.println("Press 4 for View only availiable Cars");
	System.out.println("Press 5 for do Booking of Car");
	System.out.println("Press 6 for view Status of booked car");
	System.out.println("Press 7 for cancel Booking");
	System.out.println("Press 0 for logout");
}
 static void usermenu(Scanner sc) {
	// TODO Auto-generated method stub
	 int choice;
	 do {
		 displayMenu();
		 choice=sc.nextInt();
	  switch(choice) {
		   case 1:
			   viewCarlist();
			   break;
		   case 2:
			   asendingOrder();
			   break;
		   case 3:
			   desendingOrder();
			   break;
		   case 4:
			   availiableCar();
			   break;
		   case 5:
			   bookCar(sc);
			   break;
		   case 6:
			   viewStatus();
			   break;
		   case 7:
			   cancelBooking(sc);
			   break;
		   case 0:
			   System.out.println("Thank you for visiting our portal");
			   default:
				   System.out.println("Invalid Selection Please Select valid number");
		 }
	 }while(choice!=0);
	
	
}
 static void viewCarlist() {
	 CarServices cs=new CarServiceImpl();
	 try {
		List<Car> list=cs.carList();
		for(Car c:list) {
			System.out.println(c);
		}
	} catch (SomthingWentWrongException | RecordNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
 static void asendingOrder() {
	 CarServices cs=new CarServiceImpl();
	 try {
		List<Car> list=cs.acendingOrder();
		for(Car c:list) {
			System.out.println(c);
		}
	} catch (SomthingWentWrongException | RecordNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
 static void desendingOrder() {
	 CarServices cs=new CarServiceImpl();
	 try {
		List<Car> list=cs.decendingOrder();
		for(Car c:list) {
			System.out.println(c);
		}
	} catch (SomthingWentWrongException | RecordNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
 static void availiableCar() {
	 CarServices cs=new CarServiceImpl();
	 try {
		List<Car> list=cs.onlyAvailiable();
		for(Car c:list) {
			System.out.println(c);
		}
	} catch (SomthingWentWrongException | RecordNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
 static void bookCar(Scanner sc) {
	 System.out.println("Enter CarId: ");
	 int carId=sc.nextInt();
	 UserServices us=new UserServicesImpl();
	 CarServices cs=new CarServiceImpl();
	 BookingServices Bs=new BookingServicesImpl();
	 try {
		List<User> userList=us.getUserlist();
		List<User> user= userList.stream().filter(u->u.getUserId()==LoginUser.loginUserID).toList();
		List<Car>car=cs.carList().stream().filter(c->c.getCar_id()==carId).toList();
		Booking book= new Booking(user.get(0), car.get(0),LocalDate.now(), Status.PENDING);
		Bs.doBooking(book);
		
	} catch (SomthingWentWrongException | RecordNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
 }
 static void viewStatus() {
	 UserServices us=new UserServicesImpl();
	 BookingServices Bs=new BookingServicesImpl();
	 try {
		List<User> userList=us.getUserlist();
		List<User> user= userList.stream().filter(u->u.getUserId()==LoginUser.loginUserID).toList();
		List<Object[]> data=Bs.findUser(user.get(0));
		for(Object[] o:data) {
			System.out.println("Booking [ BookingID="+o[0]+", UserID="+o[1]+", UserName="+o[2]+", CarId="+o[3]
					+", Brand="+o[4]+", Model="+o[5]+", BookingDate="+o[6]+", Status="+o[7]+" ]");
		}
		
	} catch (SomthingWentWrongException | RecordNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
 static void cancelBooking(Scanner sc) {
	 System.out.println("Enter BookingID: ");
	 int id=sc.nextInt();
	 BookingServices Bs=new BookingServicesImpl();
	 try {
		Bs.cancelBooking(id);
	} catch (SomthingWentWrongException | RecordNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
}
