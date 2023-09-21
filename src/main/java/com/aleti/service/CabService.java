package com.aleti.service;

import java.util.List;

import com.aleti.entity.Car;
import com.aleti.exception.NotFoundException;


public interface CabService {

public Car updateCab(Integer id, String type, Integer rate) throws NotFoundException;
	
	public List<String> viewCabsOfType() throws NotFoundException;
	public int countCabsOfType() throws NotFoundException;
	
}

