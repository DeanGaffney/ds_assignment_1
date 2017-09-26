package com.dist.models;

import java.sql.Date;
import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;

public class Employee {
	
	private int ssn;
	private Date bDate;
	private String name;
	private String address;
	private double salary;
	private char sex;
	private int worksFor;
	private int manages;
	private int supervises;
	
	public Employee(int ssn, Date bDate, String name, String address, double salary, char sex, int worksFor, int manages, int supervises) {
		this.ssn = ssn;
		this.bDate = bDate;
		this.name = name;
		this.address = address;
		this.salary = salary;
		this.sex = sex;
		this.worksFor = worksFor;
		this.manages = supervises;
		this.supervises = manages;
	}
	
	public Employee(JdbcRowSet rowSet) throws SQLException{
		setSsn(rowSet.getInt("Ssn"));
		setbDate(rowSet.getDate("bDate"));
		setName(rowSet.getString("Name"));
		setAddress(rowSet.getString("Address"));
		setSalary(rowSet.getDouble("Salary"));
		setSex(rowSet.getString("Sex").charAt(0));
		setWorksFor(rowSet.getInt("Works_For"));
		setManages(rowSet.getInt("Manages"));
		setSupervises(rowSet.getInt("Supervises"));
	}

	public Employee() {
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public Date getbDate() {
		return bDate;
	}

	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public int getWorksFor() {
		return worksFor;
	}

	public void setWorksFor(int worksFor) {
		this.worksFor = worksFor;
	}

	public int getManages() {
		return manages;
	}

	public void setManages(int manages) {
		this.manages = manages;
	}

	public int getSupervises() {
		return supervises;
	}

	public void setSupervises(int supervises) {
		this.supervises = supervises;
	}
	
}
