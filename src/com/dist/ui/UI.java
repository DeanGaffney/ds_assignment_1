package com.dist.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.JFrame;

import com.dist.controllers.EmployeeController;
import com.dist.db.DBManager;

public class UI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String FRAME_NAME = "Assignment 1";
	private EmployeeController employeeController;
	
	private static final int WINDOW_WIDTH = 700;
	private static final int WINDOW_HEIGHT = 800;
	
	public UI(EmployeeController employeeController) throws SQLException {
		this.employeeController = employeeController;
		init();
	}

	/**
	 * Initialises the employee table and details panel
	 * @throws SQLException
	 */
	public void init() throws SQLException{
		setTitle(FRAME_NAME);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		EmployeeDetailsPanel employeeDetailsPanel = new EmployeeDetailsPanel(getEmployeeController());
		employeeDetailsPanel.setParentContainer(this);
		
		EmployeeTablePanel employeeTable = new EmployeeTablePanel(DBManager.getTableRows(DBManager.EMPLOYEE_TABLE), 
				DBManager.getTableColumnNames(DBManager.EMPLOYEE_TABLE));
		
		getEmployeeController().setEmployeeDetailsPanel(employeeDetailsPanel);
		getEmployeeController().setEmployeeTablePanel(employeeTable);
		
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(employeeDetailsPanel, BorderLayout.NORTH);
		getContentPane().add(employeeTable);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setBackground(Color.getHSBColor(218, 25, 21));
		setVisible(true);
	}

	public EmployeeController getEmployeeController() {
		return employeeController;
	}

	public void setEmployeeController(EmployeeController employeeController) {
		this.employeeController = employeeController;
	}
}
