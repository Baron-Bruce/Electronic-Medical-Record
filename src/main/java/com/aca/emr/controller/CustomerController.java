package com.aca.emr.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aca.emr.model.Customer;
import com.aca.emr.service.CustomerService;


@Path("/customers")
public class CustomerController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getAllCustomers() {
		return CustomerService.getAllCustomers();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Customer updateCustomer(Customer updateCustomer) {
		
		return CustomerService.updateCustomer(updateCustomer);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Integer insertCustomer(Customer insertCustomer) {
		
		return CustomerService.insertCustomer(insertCustomer);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer deleteCustomer (@PathParam("id") int id) {
		
		System.out.println("Deleted");
		return CustomerService.deleteCustomer(id);
		
	}	

}
