package com.aleti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aleti.entity.Car;
import com.aleti.service.CabService;

@RestController
public class TaxiController {
	
	@Autowired
	private CabService cService;
	
	@PutMapping("/cabs")
    public ResponseEntity<Car> updateCabHandler(@RequestParam Integer id,
												@RequestParam String type,
												@RequestParam Integer rate)
    {
		
		Car updatedCar = cService.updateCab(id,type,rate);
		return new ResponseEntity<Car>(updatedCar,HttpStatus.ACCEPTED);
		
    	
		
    }
	
	@GetMapping("/cabs")
	public ResponseEntity<List<String>> viewCabsHandler(@RequestBody String carType)
	{
		
		List<String> cabs = cService.viewCabsOfType();
		
        return new ResponseEntity<List<String>>(cabs,HttpStatus.OK);
		
	}
	@GetMapping("/cabsCount")
     public String countCabsOfType()
     {
    	int countCab = cService.countCabsOfType();
    	
    	return "Number of Cabs Abvailable " + countCab;
     }
	
	
}
