package com.aleti.service;

import java.time.LocalDate;
import java.util.List;

import com.aleti.entity.Administrator;
import com.aleti.entity.TripBooking;
import com.aleti.exception.AdministratorExceptions;



public interface AdministratorService {

	public Administrator saveAdmin(Administrator administrator) throws AdministratorExceptions;
	
	public Administrator update(Administrator administrator) throws AdministratorExceptions;
	
	public Administrator delete(Integer id) throws AdministratorExceptions;
	
	public List<TripBooking> getAllTrips(Integer customerId) throws AdministratorExceptions;

	/* Method to fetch all trips grouped on the basis of driver*/
	public List<TripBooking> getTripsGroupedByDriver();

	/* Method to fetch all trips grouped on the basis of Customer*/
	public List<TripBooking> getTripsGroupedByCustomer();
	/* Method to fetch all trips based on the date*/
	public List<TripBooking> getTripsOrderedByDate() throws AdministratorExceptions;


	/* Method to fetch all trips based on the given date and given customerId*/
	public List<TripBooking> getTripsWithCustomerAndDate(Integer customerId, LocalDate date) throws AdministratorExceptions;
	
	
	

}
