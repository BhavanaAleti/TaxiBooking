package com.aleti.service;



import java.util.List;

import com.aleti.entity.Customer;
import com.aleti.exception.InvalidIdException;
import com.aleti.exception.NullException;

public interface CustomerService {

	public Customer saveCustomer(Customer customer);
	public Customer findCustomer(Integer id)throws InvalidIdException;
	public Customer updateCustomer(Customer customer,Integer id)throws InvalidIdException;
	public String deleteCustomer(Integer id)throws InvalidIdException;
	public List<Customer> allCustomer()throws NullException;
	public Customer vaildCustomer(String Email,String Password)throws InvalidIdException;
	
}
