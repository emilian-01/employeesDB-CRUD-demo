package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBUtil;

public class EmployeeDAO {

	public static Employee searchEmployee(String empId) throws SQLException, ClassNotFoundException {
		String selectStmt = "SELECT * FROM employees WHERE employee_id=" + empId;
		try {
			ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);
			Employee employee = getEmployeeFromResultSet(rsEmp);
			return employee;
		} catch (SQLException e) {
			System.out.println("While searching an employee with " + empId + " id, an error occurred: " + e);
			throw e;
		}
	}

	private static Employee getEmployeeFromResultSet(ResultSet rs) throws SQLException {
		Employee emp = null;
		if (rs.next()) {
			emp = new Employee();
			emp.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
			emp.setFirstName(rs.getString("FIRST_NAME"));
			emp.setLastName(rs.getString("LAST_NAME"));
			emp.setEmail(rs.getString("EMAIL"));
			emp.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			emp.setHireDate(rs.getDate("HIRE_DATE"));
			emp.setJobId(rs.getString("JOB_ID"));
			emp.setSalary(rs.getInt("SALARY"));
			emp.setCommissionPct(rs.getDouble("COMMISSION_PCT"));
			emp.setManagerId(rs.getInt("MANAGER_ID"));
			emp.setDepartmantId(rs.getInt("DEPARTMENT_ID"));
		}
		return emp;
	}

	public static ObservableList<Employee> searchEmployees() throws SQLException, ClassNotFoundException {
		String selectStmt = "SELECT * FROM employees";

		try {
			ResultSet rsEmps = DBUtil.dbExecuteQuery(selectStmt);
			ObservableList<Employee> empList = getEmployeeList(rsEmps);
			return empList;
		} catch (SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
			throw e;
		}
	}

	private static ObservableList<Employee> getEmployeeList(ResultSet rs) throws SQLException, ClassNotFoundException {
		ObservableList<Employee> empList = FXCollections.observableArrayList();

		while (rs.next()) {
			Employee emp = new Employee();
			emp.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
			emp.setFirstName(rs.getString("FIRST_NAME"));
			emp.setLastName(rs.getString("LAST_NAME"));
			emp.setEmail(rs.getString("EMAIL"));
			emp.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			emp.setHireDate(rs.getDate("HIRE_DATE"));
			emp.setJobId(rs.getString("JOB_ID"));
			emp.setSalary(rs.getInt("SALARY"));
			emp.setCommissionPct(rs.getDouble("COMMISSION_PCT"));
			emp.setManagerId(rs.getInt("MANAGER_ID"));
			emp.setDepartmantId(rs.getInt("DEPARTMENT_ID"));
			empList.add(emp);
		}
		return empList;
	}

	public static void updateEmpEmail(String empId, String empEmail) throws SQLException, ClassNotFoundException {
		String updateStmt = "UPDATE `employees` SET `EMAIL`='" + empEmail + "' WHERE EMPLOYEE_ID='" + empId + "'";

		try {

			DBUtil.dbExecuteUpdate(updateStmt);
		} catch (SQLException e) {
			System.out.print("Error occurred while UPDATE Operation: " + e);
			throw e;
		}
	}

	public static void deleteEmpWithId(String empId) throws SQLException, ClassNotFoundException {
		String updateStmt = "DELETE FROM employees\n" + "         WHERE employee_id ='" + empId + "'";

		try {
			DBUtil.dbExecuteUpdate(updateStmt);
		} catch (SQLException e) {
			System.out.print("Error occurred while DELETE Operation: " + e);
			throw e;
		}
	}

	public static void insertEmp(String name, String lastname, String email, Integer phone, String hireDate)
			throws SQLException, ClassNotFoundException {
		Random rand = new Random();
		int n = rand.nextInt(100);
		int s = rand.nextInt(1000000);
		int p = rand.nextInt(20);
		String updateStmt = "BEGIN\n" + "INSERT INTO employees\n"
				+ "(EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, HIRE_DATE, JOB_ID)\n" + "VALUES\n"
				+ "(sequence_employee.nextval, '" + name + "', '" + lastname + "','" + email
				+ "', SYSDATE, 'IT_PROG');\n" + "END;";
		String upStmt = "INSERT INTO `employees`"
				+ "(`EMPLOYEE_ID`, `FIRST_NAME`, `LAST_NAME`, `EMAIL`, `PHONE_NUMBER`, `HIRE_DATE`, `JOB_ID`, `SALARY`, `COMMISSION_PCT`, `MANAGER_ID`, `DEPARTMENT_ID`)"
				+ " VALUES (NULL, '" + name + "', '" + lastname + "', '" + email + "', '" + phone + "','" + hireDate
				+ "', '" + n + "', '" + s + "', '" + p + "', '" + (n + 10) + "', '" + (n + 5) + "')";
		try {
			DBUtil.dbExecuteUpdate(upStmt);

		} catch (SQLException e) {
			System.out.print("Error occurred while DELETE Operation: " + e);
			throw e;
		}
	}
}
