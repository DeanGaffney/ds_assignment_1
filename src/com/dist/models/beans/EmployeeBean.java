package com.dist.models.beans;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

import com.dist.db.DBManager;
import com.dist.models.Employee;

public class EmployeeBean {

	private JdbcRowSet rowSet;
	private Employee employee;

	public EmployeeBean() {
		try{
			this.rowSet = RowSetProvider.newFactory().createJdbcRowSet();
			this.rowSet.setUrl(DBManager.DB_URL);
			this.rowSet.setUsername(DBManager.DB_USER);
			this.rowSet.setPassword(DBManager.DB_PASS);
			this.rowSet.setCommand("SELECT * FROM " + DBManager.EMPLOYEE_TABLE);
			this.rowSet.execute();
			this.rowSet.first();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * Moves result set to the first entry in the set
	 * @return Employee object at the first position of the result set
	 */
	public Employee moveFirst(){
		Employee emp = new Employee();
		try{
			getRowSet().first();
			emp = new Employee(getRowSet());
		}catch(SQLException e){
			e.printStackTrace();
		}
		return emp;
	}

	/**
	 * Creates an employee in the database using the fields provided on the GUI
	 * @param emp, the employee object to create
	 * @return EMployee object that was created
	 */
	public Employee create(Employee emp) {
		try {
			getRowSet().moveToInsertRow();
			alterRow(emp);
			getRowSet().insertRow();
		} catch (SQLException ex) {
			try {
				getRowSet().rollback();
				emp = null;
			} catch (SQLException e) {

			}
			ex.printStackTrace();
		}
		return emp;
	}
	
	/**
	 * Alters the current row of the result set with employee information
	 * @param emp, the employee that contains the information to use to update the row
	 */
	private void alterRow(Employee emp){
		try{
			getRowSet().updateInt("Ssn", emp.getSsn());
			getRowSet().updateDate("bDate", emp.getbDate());
			getRowSet().updateString("Name" , emp.getName());
			getRowSet().updateString("Address" , emp.getAddress());
			getRowSet().updateDouble("Salary", emp.getSalary());
			getRowSet().updateString("Sex", String.valueOf(emp.getSex()));
			getRowSet().updateInt("Works_For", emp.getWorksFor());
			if(emp.getManages() == 0){
				getRowSet().updateNull("Manages");
			}else{
				getRowSet().updateInt("Manages", emp.getManages());
			}
			if(emp.getSupervises() == 0){
				getRowSet().updateNull("Supervises");
			}else{
				getRowSet().updateInt("Supervises", emp.getSupervises());
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * Updates an existing employee in the database
	 * @param emp, the employee to update
	 * @return employee object that was updated
	 */
	public Employee update(Employee emp) {
		try {
			alterRow(emp);
			getRowSet().updateRow();
			getRowSet().moveToCurrentRow();
		} catch (SQLException ex) {
			try {
				getRowSet().rollback();
				emp = null;
			} catch (SQLException e) {
				ex.printStackTrace();
			}
		}
		return emp;
	}

	/**
	 * Deletes the current row from the database
	 */
	public void delete() {
		try {
			getRowSet().moveToCurrentRow();
			getRowSet().deleteRow();
		} catch (SQLException ex) {
			try {
				getRowSet().rollback();
			} catch (SQLException e) { }
			ex.printStackTrace();
		}

	}

	/**
	 * Moves to the last position of th result set
	 * @return Employee object that is at the last position of the result set
	 */
	public Employee moveLast() {
		Employee emp = new Employee();
		try {
			getRowSet().last();
			emp = new Employee(getRowSet());
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return emp;
	}

	/**
	 * Moves to the next result in the result set
	 * @return employee located at the next position
	 */
	public Employee moveNext() {
		Employee emp = new Employee();
		try {
			if (getRowSet().next() == false)getRowSet().previous();
			emp = new Employee(getRowSet());
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return emp;
	}

	/**
	 * Moves to the previous result in the result set
	 * @return employee object located at the previous position of the result set
	 */
	public Employee movePrevious() {
		Employee emp = new Employee();
		try {
			if (!getRowSet().previous())getRowSet().next();
			emp = new Employee(getRowSet());
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return emp;
	}

	/**
	 * Retrieves the current result from the result set
	 * @return employee located at the current position
	 */
	public Employee getCurrent() {
		Employee emp = new Employee();
		try {
			getRowSet().moveToCurrentRow();
			emp = new Employee(getRowSet());
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return emp;
	}

	public JdbcRowSet getRowSet() {
		return rowSet;
	}

	public void setRowSet(JdbcRowSet rowSet) {
		this.rowSet = rowSet;
	}


	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
