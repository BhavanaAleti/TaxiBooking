package com.aleti.service;

import com.aleti.entity.CustomerDTO;

public interface UserLogIn {
	
	public String logIntoAccount(CustomerDTO userDto);
	
	public String logOutFromAccount(String key);
}
