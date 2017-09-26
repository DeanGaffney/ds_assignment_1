package com.dist.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import com.dist.controllers.EmployeeController;
import com.dist.models.Employee;
import com.dist.models.beans.EmployeeBean;

public class EmployeeDetailsPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private EmployeeController employeeController;

	JFrame parentContainer;

	private JTextField ssnField;
	private JTextField bDateField;
	private JTextField nameField;
	private JTextField addressField;
	private JTextField salaryField;
	private JTextField sexField;
	private JTextField worksForField;
	private JTextField managesField;
	private JTextField supervisesField;

	private static final String CREATE_BUTTON_TEXT = "Create";
	private static final String UPDATE_BUTTON_TEXT= "Update";
	private static final String DELETE_BUTTON_TEXT = "Delete";
	private static final String FIRST_BUTTON_TEXT = "First";
	private static final String PREVIOUS_BUTTON_TEXT = "Previous";
	private static final String NEXT_BUTTON_TEXT = "Next";
	private static final String LAST_BUTTON_TEXT = "Last";
	private static final String SAVE_BUTTON_TEXT = "Save";


	private JButton createButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JButton firstButton;
	private JButton previousButton;
	private JButton nextButton;
	private JButton lastButton;

	private EmployeeBean bean;

	public EmployeeDetailsPanel(EmployeeController employeeController) {
		this.employeeController = employeeController;
		this.parentContainer = null;
		this.bean = new EmployeeBean();
		setBorder(new TitledBorder(new EtchedBorder(),"Person Details"));
		setLayout(new BorderLayout(5, 5));
		add(initFields(), BorderLayout.NORTH);
		add(initButtons(), BorderLayout.CENTER);
		setFieldData(getBean().moveFirst());
	}

	/**
	 * Initalises buttons on the GUI 
	 * @return JPanel containing the buttons
	 */
	private JPanel initButtons() {
		this.createButton = new JButton(CREATE_BUTTON_TEXT);
		this.updateButton = new JButton(UPDATE_BUTTON_TEXT);
		this.deleteButton = new JButton(DELETE_BUTTON_TEXT);
		this.firstButton = new JButton(FIRST_BUTTON_TEXT);
		this.previousButton = new JButton(PREVIOUS_BUTTON_TEXT);
		this.nextButton = new JButton(NEXT_BUTTON_TEXT);
		this.lastButton = new JButton(LAST_BUTTON_TEXT);

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));

		initButton(panel, getCreateButton());
		initButton(panel, getUpdateButton());
		initButton(panel, getDeleteButton());
		initButton(panel, getFirstButton());
		initButton(panel, getPreviousButton());
		initButton(panel, getNextButton());
		initButton(panel, getLastButton());
		return panel;
	}

	/**
	 * Used to initalise a single button
	 * @param panel, the panel to add the button to
	 * @param button, the button to initalise and add to the panel
	 */
	private void initButton(JPanel panel, JButton button){
		panel.add(button);
		button.addActionListener(new ButtonHandler());
	}

	/**
	 * Initalises the fields for the GUI
	 * @return JPanel containg the fields
	 */
	private JPanel initFields(){
		this.ssnField = new JTextField(10);
		getSsnField().setEditable(false);
		this.bDateField = new JTextField(10);
		this.nameField = new JTextField(10); 
		this.addressField = new JTextField(10); 
		this.salaryField = new JTextField(10); 
		this.sexField = new JTextField(10); 
		this.worksForField = new JTextField(10); 
		this.managesField = new JTextField(10); 
		this.supervisesField = new JTextField(10); 

		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());

		panel.add(new JLabel("Ssn"), "align label");
		panel.add(getSsnField(), "wrap");
		panel.add(new JLabel("bDate"), "align label");
		panel.add(getbDateField(), "wrap");
		panel.add(new JLabel("Name"), "align label");
		panel.add(getNameField(), "wrap");
		panel.add(new JLabel("Address"), "align label");
		panel.add(getAddressField(), "wrap");
		panel.add(new JLabel("Salary"), "align label");
		panel.add(getSalaryField(), "wrap");
		panel.add(new JLabel("Sex"), "align label");
		panel.add(getSexField(), "wrap");
		panel.add(new JLabel("Works For"), "align label");
		panel.add(getWorksForField(), "wrap");
		panel.add(new JLabel("Manages"), "align label");
		panel.add(getManagesField(), "wrap");
		panel.add(new JLabel("Supervises"), "align label");
		panel.add(getSupervisesField(), "wrap");

		return panel;
	}

	/**
	 * Set the fields data to a specific employee
	 * @param emp, the employee to set the field data to
	 */
	public void setFieldData(Employee emp){
		getSsnField().setText(String.valueOf(emp.getSsn()));
		getbDateField().setText(emp.getbDate().toString());
		getNameField().setText(emp.getName());
		getAddressField().setText(emp.getAddress());
		getSalaryField().setText(String.valueOf(emp.getSalary()));
		getSexField().setText(String.valueOf(emp.getSex()));
		getWorksForField().setText(String.valueOf(emp.getWorksFor()));
		getManagesField().setText(String.valueOf(emp.getManages()));
		getSupervisesField().setText(String.valueOf(emp.getSupervises()));
	}

	/**
	 * Retrieves the field data from the fields on the GUI
	 * @return An Employee object created from the field data
	 */
	public Employee getFieldData(){
		return new Employee(
				Integer.valueOf(getSsnField().getText().trim()),
				Date.valueOf(getbDateField().getText().trim()),
				getNameField().getText().trim(),
				getAddressField().getText().trim(),
				Double.valueOf(getSalaryField().getText().trim()),
				Character.valueOf(getSexField().getText().trim().charAt(0)),
				Integer.valueOf(getWorksForField().getText().trim()),
				Integer.valueOf(getManagesField().getText().trim()),
				Integer.valueOf(getSupervisesField().getText().trim())
				);
	}

	public JTextField getSsnField() {
		return ssnField;
	}

	public void setSsnField(JTextField ssnField) {
		this.ssnField = ssnField;
	}

	public JTextField getbDateField() {
		return bDateField;
	}

	public void setbDateField(JTextField bDateField) {
		this.bDateField = bDateField;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public void setNameField(JTextField nameField) {
		this.nameField = nameField;
	}

	public JTextField getAddressField() {
		return addressField;
	}

	public void setAddressField(JTextField addressField) {
		this.addressField = addressField;
	}

	public JTextField getSalaryField() {
		return salaryField;
	}

	public void setSalaryField(JTextField salaryField) {
		this.salaryField = salaryField;
	}

	public JTextField getSexField() {
		return sexField;
	}

	public void setSexField(JTextField sexField) {
		this.sexField = sexField;
	}

	public JTextField getWorksForField() {
		return worksForField;
	}

	public void setWorksForField(JTextField worksForField) {
		this.worksForField = worksForField;
	}

	public JTextField getManagesField() {
		return managesField;
	}

	public void setManagesField(JTextField managesField) {
		this.managesField = managesField;
	}

	public JTextField getSupervisesField() {
		return supervisesField;
	}

	public void setSupervisesField(JTextField supervisesField) {
		this.supervisesField = supervisesField;
	}

	public EmployeeBean getBean() {
		return bean;
	}

	public void setBean(EmployeeBean bean) {
		this.bean = bean;
	}

	public JButton getCreateButton() {
		return createButton;
	}

	public void setCreateButton(JButton createButton) {
		this.createButton = createButton;
	}

	public JButton getUpdateButton() {
		return updateButton;
	}

	public void setUpdateButton(JButton updateButton) {
		this.updateButton = updateButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(JButton deleteButton) {
		this.deleteButton = deleteButton;
	}

	public JButton getFirstButton() {
		return firstButton;
	}

	public void setFirstButton(JButton firstButton) {
		this.firstButton = firstButton;
	}

	public JButton getPreviousButton() {
		return previousButton;
	}

	public void setPreviousButton(JButton previousButton) {
		this.previousButton = previousButton;
	}

	public JButton getNextButton() {
		return nextButton;
	}

	public void setNextButton(JButton nextButton) {
		this.nextButton = nextButton;
	}

	public JButton getLastButton() {
		return lastButton;
	}

	public void setLastButton(JButton lastButton) {
		this.lastButton = lastButton;
	}

	private boolean isEmptyFieldData() {
		return getTextFields().stream()
				.allMatch(field -> field.getText().trim().isEmpty());
	}

	private List<JTextField> getTextFields(){
		return Arrays.asList(getSsnField(), getbDateField(), getNameField(), 
				getAddressField(), getSalaryField(), getSexField(), 
				getWorksForField(), getManagesField(), getSupervisesField()
				);
	}

	private class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Employee emp = getFieldData();
			try{
				switch (e.getActionCommand()) {
				case SAVE_BUTTON_TEXT:
					if (isEmptyFieldData()) {
						JOptionPane.showMessageDialog(null,"Cannot create an empty record");
						return;
					}
					if (getBean().create(emp) != null)JOptionPane.showMessageDialog(null,"New person created successfully.");
					getEmployeeController().refreshEmployeeTable();
					getCreateButton().setText(CREATE_BUTTON_TEXT);
					setFieldData(getBean().moveLast());
					break;
				case CREATE_BUTTON_TEXT:
					setFieldData(new Employee(0, new Date(System.currentTimeMillis()), "" , "", 0, 'M' , 0, 0, 0));
					getCreateButton().setText(SAVE_BUTTON_TEXT);
					break;
				case UPDATE_BUTTON_TEXT:
					if (isEmptyFieldData()) {
						JOptionPane.showMessageDialog(null,"Cannot update an empty record");
						return;
					}
					if (getBean().update(emp) != null)
						JOptionPane.showMessageDialog(null,"Employee with ID:" + String.valueOf(emp.getSsn()+ " is updated successfully"));
					getEmployeeController().refreshEmployeeTable();
					break;
				case DELETE_BUTTON_TEXT:
					if (isEmptyFieldData()) {
						JOptionPane.showMessageDialog(null,"Cannot delete an empty record");
						return;
					}
					emp = getBean().getCurrent();
					getBean().delete();
					JOptionPane.showMessageDialog(null,"Person with ID:"+ String.valueOf(emp.getSsn()+ " is deleted successfully"));
					setFieldData(getBean().moveLast());
					getEmployeeController().refreshEmployeeTable();
					break;
				case FIRST_BUTTON_TEXT:
					setFieldData(getBean().moveFirst()); break;
				case PREVIOUS_BUTTON_TEXT:
					setFieldData(getBean().movePrevious()); break;
				case NEXT_BUTTON_TEXT:
					setFieldData(getBean().moveNext()); break;
				case LAST_BUTTON_TEXT:
					setFieldData(getBean().moveLast()); break;
				default:
					JOptionPane.showMessageDialog(null,"invalid command");
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}

	}

	public JFrame getParentContainer() {
		return parentContainer;
	}

	public void setParentContainer(JFrame parentContainer) {
		this.parentContainer = parentContainer;
	}

	public EmployeeController getEmployeeController() {
		return employeeController;
	}

	public void setEmployeeController(EmployeeController employeeController) {
		this.employeeController = employeeController;
	}

}
