package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Employee;
import model.EmployeeDAO;

public class EmployeeController {

	@FXML
	private TextField empIdText;
	@FXML
	private TextArea resultArea;
	@FXML
	private TextField newEmailText;
	@FXML
	private TextField nameText;
	@FXML
	private TextField surnameText;
	@FXML
	private TextField emailText;
	@FXML
	private TextField phoneText;
	@FXML
	private TextField hireText;
	@FXML
	private TableView employeeTable;
	@FXML
	private TableColumn<Employee, Integer> empIdColumn;
	@FXML
	private TableColumn<Employee, String> empNameColumn;
	@FXML
	private TableColumn<Employee, String> empLastNameColumn;
	@FXML
	private TableColumn<Employee, String> empEmailColumn;
	@FXML
	private TableColumn<Employee, String> empPhoneNumberColumn;
	@FXML
	private TableColumn<Employee, Date> empHireDateColumn;

	private Executor exec;

	@FXML
	private void initialize() {
		exec = Executors.newCachedThreadPool((runnable) -> {
			Thread t = new Thread(runnable);
			t.setDaemon(true);
			return t;
		});

		empIdColumn.setCellValueFactory(cellData -> cellData.getValue().employeeIdProperty().asObject());
		empNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		empLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		empEmailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
		empPhoneNumberColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
		empHireDateColumn.setCellValueFactory(cellData -> cellData.getValue().hireDateProperty());
	}

	@FXML
	private void searchEmployee(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		try {
			Employee emp = EmployeeDAO.searchEmployee(empIdText.getText());
			populateAndShowEmployee(emp);
		} catch (SQLException e) {
			e.printStackTrace();
			resultArea.setText("Error occurred while getting employee information from DB.\n" + e);
			throw e;
		}
	}

	@FXML
	private void searchEmployees(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
		try {
			ObservableList<Employee> empData = EmployeeDAO.searchEmployees();
			populateEmployees(empData);
		} catch (SQLException e) {
			System.out.println("Error occurred while getting employees information from DB.\n" + e);
			throw e;
		}
	}

	private void fillEmployeeTable(ActionEvent event) throws SQLException, ClassNotFoundException {
		Task<List<Employee>> task = new Task<List<Employee>>() {
			@Override
			public ObservableList<Employee> call() throws Exception {
				return EmployeeDAO.searchEmployees();
			}
		};

		task.setOnFailed(e -> task.getException().printStackTrace());
		task.setOnSucceeded(e -> employeeTable.setItems((ObservableList<Employee>) task.getValue()));
		exec.execute(task);
	}

	@FXML
	private void populateEmployee(Employee emp) throws ClassNotFoundException {
		ObservableList<Employee> empData = FXCollections.observableArrayList();
		empData.add(emp);
		employeeTable.setItems(empData);
	}

	@FXML
	private void setEmpInfoToTextArea(Employee emp) {
		resultArea.setText("First Name: " + emp.getFirstName() + "\n" + "Last Name: " + emp.getLastName());
	}

	@FXML
	private void populateAndShowEmployee(Employee emp) throws ClassNotFoundException {
		if (emp != null) {
			populateEmployee(emp);
			setEmpInfoToTextArea(emp);
		} else {
			resultArea.setText("This employee does not exist!\n");
		}
	}

	@FXML
	private void populateEmployees(ObservableList<Employee> empData) throws ClassNotFoundException {
		employeeTable.setItems(empData);
	}

	@FXML
	private void updateEmployeeEmail(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
		try {
			EmployeeDAO.updateEmpEmail(empIdText.getText(), newEmailText.getText());
			resultArea.setText("Email has been updated for, employee id: " + empIdText.getText() + "\n");
		} catch (SQLException e) {
			resultArea.setText("Problem occurred while updating email: " + e);
		}
	}

	@FXML
	private void insertEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
		if (nameText.getText().isEmpty() || surnameText.getText().isEmpty() || emailText.getText().isEmpty()
				|| phoneText.getText().isEmpty() || hireText.getText().isEmpty())
			JOptionPane.showMessageDialog(null, "Fill in the data!!!");
		else {
			try {
				EmployeeDAO.insertEmp(nameText.getText(), surnameText.getText(), emailText.getText(),
						Integer.valueOf(phoneText.getText()), hireText.getText());
				resultArea.setText("Employee inserted! \n");
			} catch (SQLException e) {
				resultArea.setText("Problem occurred while inserting employee " + e);
				throw e;
			}
		}
	}

	@FXML
	private void deleteEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
		if (empIdText.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "To delete an employee fill the \"Employee ID\"");
		} else
			try {
				EmployeeDAO.deleteEmpWithId(empIdText.getText());
				resultArea.setText("Employee deleted! Employee id: " + empIdText.getText() + "\n");
			} catch (SQLException e) {
				resultArea.setText("Problem occurred while deleting employee " + e);
				throw e;
			}
	}
}
