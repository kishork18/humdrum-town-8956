package com.masai.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.masai.entity.Admin;
import com.masai.entity.Availability;
import com.masai.entity.Car;
import com.masai.entity.LoginAdmin;
import com.masai.entity.Report;
import com.masai.entity.Status;
import com.masai.entity.User;
import com.masai.exceptions.RecordNotFoundException;
import com.masai.exceptions.SomthingWentWrongException;
import com.masai.service.AdminServices;
import com.masai.service.AdminServicesImpl;
import com.masai.service.BookingServices;
import com.masai.service.BookingServicesImpl;
import com.masai.service.CarServiceImpl;
import com.masai.service.CarServices;
import com.masai.service.ReportService;
import com.masai.service.ReportServicesImpl;
import com.masai.service.UserServices;
import com.masai.service.UserServicesImpl;

public class AdminUI {
	static void adminLogin(Scanner sc) {
		System.out.print("Enter username ");
		String username = sc.next();
		System.out.print("Enter password ");
		String password = sc.next();
		AdminServices as=new AdminServicesImpl();
		try {
			as.logIn(username, password);
			 adminMenu(sc);
		} catch (SomthingWentWrongException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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
	System.out.println("Press 13 for View All userAccount");
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
         case 4:
        	 deleteCar(sc);
        	 break;
         case 5:
        	 viewBookings();
        	 break;
         case 6:
        	 updateBookingStatus(sc);
        	 break;
         case 7:
        	 registerAdmin(sc);
        	 break;
         case 8:
        	 genrateReport();
        	 break;
         case 9:
        	 viewAllReports();
        	 break;
         case 10:
        	 viewReportGeneratedByYou();
        	 break;
         case 11:
        	 viewReportByDate(sc);
        	 break;
         case 12:
        	 deleteReport(sc);
        	 break;
         case 13:
        	 viewAllUserList();
        	 break;
         case 14:
        	 deActivateUser(sc);
        	 break;
         case 0:
        	 System.out.println("By By Admin");
        	 break;
        	 default:
        		 System.out.println("Invalid selection please select valid option");
        	 
	  }
	}while(choice!=0);
}
 static void addCar(Scanner sc) {
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
 static void updateCar(Scanner sc) {
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
static void deleteCar(Scanner sc) {
	System.out.println("Enter car id: ");
	int id=sc.nextInt();
	CarServices cs=new CarServiceImpl();
	try {
		cs.deleteCar(id);
	} catch (SomthingWentWrongException | RecordNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
static void viewBookings() {
	BookingServices bs=new BookingServicesImpl();
	try {
		List<Object[]>list=bs.BookingList();
		for(Object[] o:list) {
			System.out.println("Booking [ BookingID="+o[0]+", UserID="+o[1]+", UserName="+o[2]+", CarId="+o[3]
					+", Brand="+o[4]+", Model="+o[5]+", BookingDate="+o[6]+", Status="+o[7]+" ]");
		}
	} catch (SomthingWentWrongException | RecordNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
} 
static void updateBookingStatus(Scanner sc) {
	System.out.println("Enter Booking Id: ");
	int id=sc.nextInt();
	System.out.println("Select option for booking status \n"
			+ "1)Confirm Booking\n"
			+ "2)Reject Booking");
	int s=sc.nextInt();
	while(s!=1 && s!=2) {
		System.out.println("Invalid choice please enter valid choice");
		s=sc.nextInt();
	}
	Status status=s==1?Status.CONFIRMED:Status.REJECTED;
	BookingServices bs=new BookingServicesImpl();
	try {
		bs.updateBookingStatus(id, status);
	} catch (SomthingWentWrongException | RecordNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
static void registerAdmin(Scanner sc) {
	System.out.println("Enter UserName: ");
	 String userName=sc.next();
	 System.out.println("Enter Password: ");
	 String password=sc.next();
	 System.out.println("Enter Email: ");
	 String email=sc.next();
	 System.out.println("Enter Mobile No: ");
	 String mobileNo=sc.next();
	 Admin admin= new Admin(userName, password, email, mobileNo);
	 AdminServices as= new AdminServicesImpl();
	 try {
		as.createAccount(admin);
	} catch (SomthingWentWrongException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
}
static void genrateReport() {
	AdminServices as= new AdminServicesImpl();
	ReportService rs=new ReportServicesImpl();
	try {
		List<Admin> list=as.getAdminList();
		List<Admin> admin=list.stream().filter(a->a.getAdmin_id()==LoginAdmin.loginAdminId).toList();
		Report r= new Report(admin.get(0), LocalDate.now(), 0, 0);
		rs.generateReport(r);
		
	} catch (SomthingWentWrongException | RecordNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
static void viewAllReports() {
	ReportService rs=new ReportServicesImpl();
	try {
		List<Report> list=rs.getReportList();
		for(Report r:list) {
			System.out.println(r);
		}
	} catch (SomthingWentWrongException | RecordNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
static void viewReportGeneratedByYou() {
	AdminServices as= new AdminServicesImpl();
	ReportService rs=new ReportServicesImpl();
	List<Admin> list;
	try {
		list = as.getAdminList();
		List<Admin> admin=list.stream().filter(a->a.getAdmin_id()==LoginAdmin.loginAdminId).toList();
		List<Report> reportlist=rs.findReportByAdmin(admin.get(0));
		for(Report r:reportlist) {
			System.out.println(r);
		}
	} catch (SomthingWentWrongException | RecordNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
static void viewReportByDate(Scanner sc) {
	System.out.println("Enter Date [YYYY-MM-DD]: ");
	LocalDate date=LocalDate.parse(sc.next());
	ReportService rs=new ReportServicesImpl();
	try {
		List<Report> reportlist=rs.findReportByDate(date);
		for(Report r:reportlist) {
			System.out.println(r);
		}
	} catch (SomthingWentWrongException | RecordNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
static void deleteReport(Scanner sc) {
	System.out.println("Enter Report ID: ");
	int id=sc.nextInt();
	AdminServices as= new AdminServicesImpl();
	ReportService rs=new ReportServicesImpl();
	List<Admin> list;
	try {
		list = as.getAdminList();
		List<Admin> admin=list.stream().filter(a->a.getAdmin_id()==LoginAdmin.loginAdminId).toList();
		rs.deleteReport(id, admin.get(0));
	} catch (SomthingWentWrongException | RecordNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
static void viewAllUserList() {
	UserServices us= new UserServicesImpl();
	try {
		List<User> list=us.getUserlist();
		for(User u: list) {
			System.out.println(u);
		}
	} catch (SomthingWentWrongException | RecordNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
static void deActivateUser(Scanner sc) {
	System.out.println("Enter User ID: ");
	int id=sc.nextInt();
	UserServices us= new UserServicesImpl();
	try {
		us.deleteAccount(id);
	} catch (SomthingWentWrongException | RecordNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
