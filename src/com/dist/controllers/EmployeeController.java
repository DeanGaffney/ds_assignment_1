package com.dist.controllers;

import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import com.dist.db.DBManager;
import com.dist.ui.EmployeeDetailsPanel;
import com.dist.ui.EmployeeTablePanel;


public class EmployeeController {
	
	private EmployeeDetailsPanel employeeDetailsPanel;
	private EmployeeTablePanel employeeTable;
	
	public EmployeeController() {
		
	}
	
	/**
	 * Refreshes the employee table to reflect changes in the database
	 * @throws SQLException
	 */
	public void refreshEmployeeTable() throws SQLException{
		DefaultTableModel dm = (DefaultTableModel)getEmployeeTablePanel().getEmployeeTable().getModel();
		dm.setDataVector(DBManager.getTableRows(DBManager.EMPLOYEE_TABLE), DBManager.getTableColumnNames(DBManager.EMPLOYEE_TABLE));
		dm.fireTableDataChanged();
	}

	public EmployeeDetailsPanel getEmployeeDetailsPanel() {
		return employeeDetailsPanel;
	}

	public void setEmployeeDetailsPanel(EmployeeDetailsPanel employeeDetailsPanel) {
		this.employeeDetailsPanel = employeeDetailsPanel;
	}

	public EmployeeTablePanel getEmployeeTablePanel() {
		return employeeTable;
	}

	public void setEmployeeTablePanel(EmployeeTablePanel employeeTable) {
		this.employeeTable = employeeTable;
	}
	
	
}
