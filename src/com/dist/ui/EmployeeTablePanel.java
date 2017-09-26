package com.dist.ui;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class EmployeeTablePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTable employeeTable;
	private JScrollPane tableContainer;
	private static final int VERTICAL_STRUT_SPACE = 100;
	
	public EmployeeTablePanel(Vector<Vector<Object>> data, Vector<String> columnNames) {
		this.employeeTable = new JTable(data, columnNames);
		this.employeeTable.setVisible(true);
		this.tableContainer = new JScrollPane(this.employeeTable);
		setBorder(new TitledBorder(new EtchedBorder(),"Employee Table"));
		setLayout(new BorderLayout(5, 5));
		add(Box.createVerticalStrut(VERTICAL_STRUT_SPACE));
		add(this.tableContainer);
	}

	public JTable getEmployeeTable() {
		return employeeTable;
	}

	public void setEmployeeTable(JTable employeeTable) {
		this.employeeTable = employeeTable;
	}

	public JScrollPane getTableContainer() {
		return tableContainer;
	}

	public void setTableContainer(JScrollPane tableContainer) {
		this.tableContainer = tableContainer;
	}
}
