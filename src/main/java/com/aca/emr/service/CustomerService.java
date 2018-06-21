package com.aca.emr.service;

import java.util.List;

import com.aca.emr.dao.CustomerDao;
import com.aca.emr.model.Customer;

public class CustomerService {
	
	public static List<Customer> getAllCustomers() {
		return CustomerDao.getCustomers();
	}

	public static Customer updateCustomer(Customer updateCustomer) {
		
		return CustomerDao.updateCustomer(updateCustomer);
	}

	public static Integer insertCustomer(Customer insertCustomer) {
		
		return CustomerDao.insertCustomer(insertCustomer);
	}

	public static Integer deleteCustomer(int id) {
		
		return CustomerDao.deleteCustomer(id);
	}

}
