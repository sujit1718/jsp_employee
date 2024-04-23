package jsp_employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeCRUD {


	public Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp","root","root");
		return connection;
	}
	
	public int signupEmployee(Employee emp) throws Exception
	{
		String sql = "insert into employee(id,name,phone,email,password,designation,salary)values(?,?,?,?,?,?,?)";
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
	    preparedStatement.setInt(1, emp.getId());
	    preparedStatement.setString(2, emp.getName());
	    preparedStatement.setLong(3, emp.getPhone());
	    preparedStatement.setString(4, emp.getEmail());
	    preparedStatement.setString(5, emp.getPassword());
	    preparedStatement.setString(6, emp.getDesignation());
	    preparedStatement.setDouble(7, emp.getSalary());
	    
	    int result = preparedStatement.executeUpdate();
	    connection.close();
	    return result;
	}
	
	 public String loginEmployee(String email) throws Exception
	 {
		String sql = "select password from employee where email=?";
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, email);
		ResultSet resultSet  = preparedStatement.executeQuery();
		String password = null;
		while(resultSet.next()) {
			password = resultSet.getString("password");
		}
		connection.close();
		return password;
	 }
	 
	 public List<Employee> displayEmployees() throws Exception {
		 String sql = "select * from employee";
		 Connection connection = getConnection();
		 PreparedStatement preparedStatement = connection.prepareStatement(sql);
		 ResultSet resultSet = preparedStatement.executeQuery();
		 List<Employee> list = new ArrayList<>();
		 while(resultSet.next()) {
			 Employee employee = new Employee();
			 employee.setId(resultSet.getInt("id"));
			 employee.setName(resultSet.getString("name"));
			 employee.setPhone(resultSet.getLong("phone"));
			 employee.setEmail(resultSet.getString("email"));
			 employee.setPassword(resultSet.getString("password"));
			 employee.setDesignation(resultSet.getString("designation"));
			 employee.setSalary(resultSet.getDouble("salary"));
			 list.add(employee);
		 }
		 connection.close();
		 return list;
	 } 
	 
	 public int delete(int id) throws Exception {
		 String sql = "delete from employee where id=?";
	     Connection connection = getConnection();
	     PreparedStatement preparedStatement = connection.prepareStatement(sql);
	     preparedStatement.setInt(1, id);
	     int result = preparedStatement.executeUpdate();
		 connection.close();
		 return result;
	 } 
	 
	 public Employee findEmployee(int id) throws Exception {
		 String sql = "select * from employee where id=?";
	     Connection connection = getConnection();
	     PreparedStatement preparedStatement = connection.prepareStatement(sql);
	     preparedStatement.setInt(1, id);
	     ResultSet resultSet = preparedStatement.executeQuery();
		 Employee employee = new Employee();
		 while(resultSet.next()) {
			 employee.setId(resultSet.getInt("id"));
			 employee.setName(resultSet.getString("name"));
			 employee.setPhone(resultSet.getLong("phone"));
			 employee.setEmail(resultSet.getString("email"));
			 employee.setPassword(resultSet.getString("password"));
			 employee.setDesignation(resultSet.getString("designation"));
			 employee.setSalary(resultSet.getDouble("salary"));
		 }
		 connection.close();
		 return employee; 
	 }
	 

		public int updateEmployee(Employee employee) throws Exception {
	        Connection connection = getConnection() ;
	        String sql = "update employee set name=?, phone=?, email=?, password=?, designation=?, salary=? where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql) ;
			
	        preparedStatement.setString(1, employee.getName());
	        preparedStatement.setLong(2, employee.getPhone());
	        preparedStatement.setString(3, employee.getEmail());
	        preparedStatement.setString(4, employee.getPassword());
	        preparedStatement.setString(5, employee.getDesignation());
	        preparedStatement.setDouble(6, employee.getSalary());
	        preparedStatement.setInt(7, employee.getId());
			
			int result = preparedStatement.executeUpdate() ;
			connection.close();
			return result;
		}

}
