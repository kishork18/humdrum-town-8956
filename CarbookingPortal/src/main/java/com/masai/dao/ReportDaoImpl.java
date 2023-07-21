package com.masai.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.masai.entity.Admin;
import com.masai.entity.Report;
import com.masai.entity.Status;
import com.masai.exceptions.RecordNotFoundException;
import com.masai.exceptions.SomthingWentWrongException;
import com.masai.utility.EMutils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class ReportDaoImpl implements ReportDao{

	@Override
	public void generateReport(Report r) throws SomthingWentWrongException {
		// TODO Auto-generated method stub
		EntityManager em=null;
		try {
			em=EMutils.createConnection();
			Query q=em.createQuery("SELECT count(b) FROM Booking b WHERE b.status=:status AND b.booking_date=:date");
			q.setParameter("status", Status.CONFIRMED);
			q.setParameter("date", r.getReport_date());
			Object count=  q.getSingleResult();
			if(count!=null && count instanceof Number) {
				int c=((Number) count).intValue();
				r.setNumber_of_bookings(c);
			}else {
				throw new SomthingWentWrongException("Somthing went wrong or could not found any count of booking");
			}
			Query q2=em.createQuery("SELECT SUM(b.car.price)\r\n"
					+ "FROM Booking b\r\n"
					+ "WHERE b.status = :CONFIRMED AND b.booking_date=:date\r\n"
					+ "");
			q2.setParameter("CONFIRMED", Status.CONFIRMED);
			q2.setParameter("date", r.getReport_date());

	        Object sum=  q2.getSingleResult();
	        if(sum!=null && sum instanceof Number) {
				double s=((Number) sum).doubleValue();
				r.setRevenue_generated(s);;
			}else {
				throw new SomthingWentWrongException("Somthing went wrong or could not found any revenue from booking");
			}
			EntityTransaction et=em.getTransaction();
			et.begin();
			em.persist(r);
			et.commit();
			System.out.println("Report Generated successfully");
		} catch (PersistenceException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new SomthingWentWrongException("Something went wrong please try again later");
		}
		finally {
			em.close();
		}
		
	}

	@Override
	public List<Report> getReportList() throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		EntityManager em=null;
		List <Report> list=new ArrayList<>();
		try {
			em=EMutils.createConnection();
			Query q=em.createQuery("FROM Report r");
			list=q.getResultList();
			if(list==null) {
				throw new RecordNotFoundException("Report list is Empty no Record found");
			}
		} catch (PersistenceException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new SomthingWentWrongException("Something went wrong please try again later");
		}
		finally {
			em.close();
		}
		return list;
	}

	@Override
	public List<Report> findReportByAdmin(Admin a) throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		EntityManager em=null;
		List <Report> list=new ArrayList<>();
		try {
			em=EMutils.createConnection();
			Query q=em.createQuery("SELECT r FROM Report r WHERE r.admin=:admin");
			q.setParameter("admin", a);
			list=q.getResultList();
			if(list==null) {
				throw new RecordNotFoundException("Report list is Empty no Record found");
			}
		} catch (PersistenceException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new SomthingWentWrongException("Something went wrong please try again later");
		}
		finally {
			em.close();
		}
		return list;
	}

	@Override
	public List<Report> findReportByDate(LocalDate date) throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		EntityManager em=null;
		List <Report> list=new ArrayList<>();
		try {
			em=EMutils.createConnection();
			Query q=em.createQuery("SELECT r FROM Report r WHERE r.report_date=:date");
			q.setParameter("date", date);
			list=q.getResultList();
			if(list==null) {
				throw new RecordNotFoundException("Report list is Empty no Record found");
			}
		} catch (PersistenceException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new SomthingWentWrongException("Something went wrong please try again later");
		}
		finally {
			em.close();
		}
		return list;
	}

	@Override
	public void deleteReport(int id,Admin a) throws SomthingWentWrongException, RecordNotFoundException {
	  EntityManager em=null;
	  try {
		em=EMutils.createConnection();
		Query q=em.createQuery("SELECT r FROM Report r WHERE r.report_id=:id AND r.admin=:admin");
		q.setParameter("id", id);
		q.setParameter("admin", a);
		List<Report> list=q.getResultList();
		Report r=list.get(0);
		if(r==null) {
			throw new RecordNotFoundException("Record Not found with Provided Id or this report is not genrated by you");
		}
		EntityTransaction et=em.getTransaction();
		et.begin();
		em.remove(r);
		et.commit();
		System.out.println("Report Deleted successfully");
	} catch (PersistenceException e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new SomthingWentWrongException("Somthing went wrong please try again later");
	}
	  finally {
			em.close();
		}
		
	}

}
