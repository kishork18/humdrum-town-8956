package com.masai.ui;

import java.util.Scanner;

public class Main {
	 public static void main( String[] args ){
	    	Scanner sc = new Scanner(System.in);
	    	int choice = 0;
	    	do {
	    		System.out.println("Press 1 Admin Login");
	    		System.out.println("Press 2 User Login");
	    		System.out.println("Press 3 User Registration");
	    		System.out.println("Press 0 Exit");
	    		System.out.print("Enter Selection ");
	    		choice = sc.nextInt();
	    		switch(choice) {
	    			case 1:
	    				AdminUI.adminLogin(sc);;
	    				break;
	    			case 2:
	    				UserUI.userLogin(sc);;
	    				break;
	    			case 3:
	    				UserUI.registerUser(sc);;
	    				break;
	    			case 0:
	    				System.out.println("Thanks for using the services");
	    				break;
	    			default:
	    				System.out.println("Invalid Selection, try again");
	    		}
	    	}while(choice != 0);
	    	sc.close();
	    }
}
