package com.masai.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "report")
public class Report {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int report_id;
@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "admin_id")
private Admin admin;
private LocalDate report_date;
private int number_of_bookings;
private double revenue_generated;
public Report() {
	super();
	// TODO Auto-generated constructor stub
}
public Report(Admin admin, LocalDate report_date, int number_of_bookings, double revenue_generated) {
	super();
	this.admin = admin;
	this.report_date = report_date;
	this.number_of_bookings = number_of_bookings;
	this.revenue_generated =  revenue_generated;
}
public int getReport_id() {
	return report_id;
}
public Admin getAdmin() {
	return admin;
}
public void setAdmin(Admin admin) {
	this.admin = admin;
}
public LocalDate getReport_date() {
	return report_date;
}
public void setReport_date(LocalDate report_date) {
	this.report_date = report_date;
}
public int getNumber_of_bookings() {
	return number_of_bookings;
}
public void setNumber_of_bookings(int number_of_bookings) {
	this.number_of_bookings = number_of_bookings;
}
public double getRevenue_generated() {
	return revenue_generated;
}
public void setRevenue_generated(double revenue_generated) {
	this.revenue_generated = revenue_generated;
}
@Override
public String toString() {
	return "Report [report_id=" + report_id + ", AdminID=" + admin.getAdmin_id() + ", AdminUserName=" + admin.getUserName() +", report_date=" + report_date
			+ ", number_of_bookings=" + number_of_bookings + ", revenue_generated=" + revenue_generated + "]";
}

}
