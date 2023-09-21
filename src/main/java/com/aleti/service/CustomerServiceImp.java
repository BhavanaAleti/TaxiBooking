package com.aleti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleti.repository.AddressDao;
import com.aleti.repository.CustomerDao;
import com.aleti.entity.Address;
import com.aleti.entity.Customer;
import com.aleti.exception.InvalidIdException;
import com.aleti.exception.NullException;


@Service
public class CustomerServiceImp implements CustomerService {
 
	@Autowired
	private CustomerDao cdao;
    @Autowired
    private AddressDao Adao;
	
	
	@Override
    	public Customer saveCustomer(Customer customer) {
		return cdao.save(customer);
	}


	@Override
	public Customer findCustomer(Integer id) throws InvalidIdException {
	

		Customer ct=cdao.findById(id).orElseThrow(() -> new InvalidIdException("Customer with ID "+id+" does not exit.."));
		
		
		return ct;
		
	}
	@Override
	public Customer updateCustomer(Customer customer, Integer id) throws InvalidIdException {
		
		Customer c1=cdao.findById(id).orElseThrow(() -> new InvalidIdException("Customer with ID "+id+" does not exit.."));
		
	Integer aid=	c1.getAddress().getId();
		
		c1.setAddress(customer.getAddress());
		c1.setEmail(customer.getEmail());
		c1.setPhoneNumber(customer.getPhoneNumber());
		c1.setPassword(customer.getPassword());
		c1.setUsername(customer.getUsername());
		Address a1=Adao.findById(aid).orElseThrow(() -> new InvalidIdException("Address with ID "+aid+" does not exit.."));
		Adao.delete(a1);
		Adao.save(customer.getAddress());
		
		return c1;
	}


	@Override
	public String deleteCustomer(Integer id) throws InvalidIdException {
		// TODO Auto-generated method stub
		Customer ct=cdao.findById(id).orElseThrow(() -> new InvalidIdException("Customer with ID "+id+" does not exit.."));
		Adao.delete(ct.getAddress());
		cdao.delete(ct);
		
		return "delete...";
	}


	@Override
	public List<Customer> allCustomer() throws NullException {
		List<Customer> c1  =cdao.findAll();
		if(c1.size()==0)
			throw new NullException("EMPTY NO DATA AVAILABLE");
		return c1;
	}


	@Override
	public Customer vaildCustomer(String Email, String Password) throws InvalidIdException {
		// TODO Auto-generated method stub
		
		List<Customer> c1  =cdao.findAll();
		for(int i= 0; i < c1.size(); i++) {
			if(c1.get(i).getEmail().equals(Email) && c1.get(i).getPassword().equals(Password))
			     return c1.get(i);
			}
		 throw new InvalidIdException("Invalid Email and password");
	}





	

}
