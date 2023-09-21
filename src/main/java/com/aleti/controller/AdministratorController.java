package com.aleti.controller;



import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aleti.entity.Administrator;
import com.aleti.entity.TripBooking;
import com.aleti.service.AdministratorService;
@RestController
@RequestMapping("/administrator")
public class AdministratorController {
	
	@Autowired
	private AdministratorService administratorService;


	/* API to add an admin with the provided details */
	@PostMapping("/")
	public ResponseEntity<Administrator> insertAdmin(@RequestBody Administrator administrator) {
		Administrator savedAdministrator = administratorService.saveAdmin(administrator);
		return new ResponseEntity<Administrator>(savedAdministrator,HttpStatus.OK);
	}



	/* API to update details of an admin with the provided details */
	@PatchMapping("/")
	public ResponseEntity<String> updateAdmin(@RequestBody Administrator administrator) {
		Administrator updatedAdministrator = administratorService.update(administrator);
		return new ResponseEntity<String>("admin updated "+ updatedAdministrator,HttpStatus.ACCEPTED);
	}


	/* API to delete the admin for the provided valid id for an admin*/
	@DeleteMapping("/{id}")
	public ResponseEntity<Administrator> deleteAdministrator(@PathVariable("id") Integer id) {
		Administrator returnAdministrator = administratorService.delete(id);
		return new ResponseEntity<Administrator>(returnAdministrator,HttpStatus.OK);
	}


	@GetMapping("/trips/{customerId}")
	public ResponseEntity<List<TripBooking>> getAllTrips(@PathVariable("customerId") Integer customerId){
		
		List<TripBooking> trips= administratorService.getAllTrips(customerId);
		return new ResponseEntity<List<TripBooking>>(trips,HttpStatus.OK);
	}
	
	@GetMapping("/trips/driver")
	public ResponseEntity<List<TripBooking>> getTripsGroupedByDriver(){
		
		List<TripBooking> trips= administratorService.getTripsGroupedByDriver();
		return new ResponseEntity<List<TripBooking>>(trips,HttpStatus.OK);
	}
	

	@GetMapping("/customerTrips")
	public List<TripBooking> getTripsGroupedByCustomer(){
		return administratorService.getTripsGroupedByCustomer();
	}
	
	@GetMapping("/dateTrips")
	public List<TripBooking> getTripsOrderedByDate(){
		return administratorService.getTripsOrderedByDate();
	}
	
	@GetMapping("trips/{customerId}/{date}")
	public List<TripBooking> getTripsWithCustomerAndDate(@PathVariable("customerId") Integer customerId, @PathVariable("date") String date){
		//LocalDate date1 = LocalDate.parse(date);
		return administratorService.getTripsWithCustomerAndDate(customerId, LocalDate.parse(date));

	}
}
