package com.masai.utility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMutils {
  public static EntityManagerFactory emf;
  static {
	  emf=Persistence.createEntityManagerFactory("ProjectConnect");
  }
  public static EntityManager createConnection() {
	  return emf.createEntityManager();
  }
}
