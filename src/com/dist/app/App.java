package com.dist.app;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.Savepoint;

import com.dist.controllers.EmployeeController;
import com.dist.db.DBManager;
import com.dist.ui.UI;

public class App {
	
	private UI ui;
	private EmployeeController employeeController;
	private Savepoint savepoint;
	
	public App() {
		this.employeeController = new EmployeeController();
	}
	
	/**
	 * Sets up the ui and runs the application
	 * @throws SQLException
	 */
	public void run(){
		try{
			this.ui = new UI(getEmployeeController());
			this.ui.setEmployeeController(this.employeeController);
		}catch(SQLException e){
			
		}
	}
	
	/**
	 * Enables roll backs of the database for this particular app run
	 */
	public void enableRollback(){
		getUi().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					DBManager.rollback(getSavepoint());
				} catch (SQLException ex) {
				}
			}
		});
	}

	public UI getUi() {
		return ui;
	}

	public void setUi(UI ui) {
		this.ui = ui;
	}

	public EmployeeController getEmployeeController() {
		return employeeController;
	}

	public void setEmployeeController(EmployeeController employeeController) {
		this.employeeController = employeeController;
	}

	public Savepoint getSavepoint() {
		return savepoint;
	}

	public void setSavepoint(Savepoint savepoint) {
		this.savepoint = savepoint;
	}
}

