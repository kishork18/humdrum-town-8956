package com.masai.service;

import java.time.LocalDate;
import java.util.List;

import com.masai.dao.ReportDao;
import com.masai.dao.ReportDaoImpl;
import com.masai.entity.Admin;
import com.masai.entity.Report;
import com.masai.exceptions.RecordNotFoundException;
import com.masai.exceptions.SomthingWentWrongException;

public class ReportServicesImpl implements ReportService{

	@Override
	public void generateReport(Report r) throws SomthingWentWrongException {
		// TODO Auto-generated method stub
		ReportDao rd= new ReportDaoImpl();
		rd.generateReport(r);
	}

	@Override
	public List<Report> getReportList() throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		ReportDao rd= new ReportDaoImpl();
		return rd.getReportList();
	}

	@Override
	public List<Report> findReportByAdmin(Admin a) throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		ReportDao rd= new ReportDaoImpl();
		return rd.findReportByAdmin(a);
	}

	@Override
	public List<Report> findReportByDate(LocalDate date) throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		ReportDao rd= new ReportDaoImpl();
		return rd.findReportByDate(date);
	}

	@Override
	public void deleteReport(int id,Admin a) throws SomthingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		ReportDao rd= new ReportDaoImpl();
		rd.deleteReport(id,a);
		
	}

}
