package com.aca.emr.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aca.emr.model.Customer;




public class CustomerDao {
	
	private final static String allCustomersColumns = " firstName, city, id ";
	
	private final static String sqlUpdateCustomer =
			" UPDATE patient" +
			" SET firstName = ?," +
			" city = ? " +
			" WHERE id = ? ";
	
	private final static String sqlInsertCustomer =
			" Insert INTO patient ( " + allCustomersColumns + " ) " +
			" VALUES (?,?,?) ";
	
	private final static String sqlDeleteCustomerById =
			" DELETE FROM patient WHERE Id = ?";
	

	
	
	

	public static List <Customer> getCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			statement = conn.createStatement();
			result = statement.executeQuery(sqlSelectAllCustomers);
			
			while(result.next()) {
				Customer customer = new Customer();
			
				customer.setCity(result.getString("city"));
				customer.setFirstName(result.getString("firstName"));
				customer.setId(result.getInt("id"));
				
							
				customers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { 
				result.close();
				statement.close();
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		return customers;
	}
	
	private final static String sqlSelectAllCustomers = 
			" SELECT " + allCustomersColumns +
			" FROM patient " ;

	public static Customer updateCustomer(Customer updateCustomer) {
		
			System.out.println(updateCustomer.toString());
			PreparedStatement statement = null;
			
			Connection conn = MariaDbUtil.getConnection();
			
			try {
				statement = conn.prepareStatement(sqlUpdateCustomer);
				statement.setString(1, updateCustomer.getFirstName());
				statement.setString(2,  updateCustomer.getCity());
				statement.setInt(3, updateCustomer.getId());
				
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					statement.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return updateCustomer;
		
	}

	public static Integer insertCustomer(Customer insertCustomer) {
		
		int rowCount = 0;
		PreparedStatement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			statement = conn.prepareStatement(sqlInsertCustomer);
			statement.setString(1, insertCustomer.getFirstName());
			statement.setString(2, insertCustomer.getCity());
			statement.setInt(3,  insertCustomer.getId());
			
			rowCount = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowCount;
	}
	
	public static Integer deleteCustomer(int id) {
		
		int rowCount = 0;
		PreparedStatement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			statement = conn.prepareStatement(sqlDeleteCustomerById);
			statement.setInt(1, id);
			
			rowCount = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowCount;
	
}

}
