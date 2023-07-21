package com.masai.service;

import java.time.LocalDate;
import java.util.List;

import com.masai.entity.Admin;
import com.masai.entity.Report;
import com.masai.exceptions.RecordNotFoundException;
import com.masai.exceptions.SomthingWentWrongException;

public interface ReportService {
	public void generateReport(Report r)throws SomthingWentWrongException;
	  public List<Report> getReportList()throws SomthingWentWrongException,RecordNotFoundException;
	  public List<Report> findReportByAdmin(Admin a)throws SomthingWentWrongException,RecordNotFoundException;
	  public List<Report> findReportByDate(LocalDate date)throws SomthingWentWrongException,RecordNotFoundException;
	  public void deleteReport(int id,Admin a) throws SomthingWentWrongException, RecordNotFoundException;
}
