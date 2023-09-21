package com.aleti.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleti.entity.Administrator;
import com.aleti.entity.Customer;
import com.aleti.entity.TripBooking;
import com.aleti.exception.AdministratorExceptions;
import com.aleti.repository.AdminDao;
import com.aleti.repository.CarDao;
import com.aleti.repository.CustomerDao;
import com.aleti.repository.DriverDao;

import com.aleti.repository.TripDao;


@Service
public class AdministratorServiceImp implements AdministratorService {
	@Autowired
	private AdminDao adminDao;

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired 
	private DriverDao driverDao;
	
	@Autowired
	private CarDao carDao;
	
	@Autowired
	private TripDao tripDao;

	@Override
	public Administrator saveAdmin(Administrator administrator) throws AdministratorExceptions {
		System.out.println(administrator);
		return adminDao.save(administrator);
	}

	@Override
	public Administrator update(Administrator administrator) throws AdministratorExceptions {
		Optional<Administrator> opt = adminDao.findById(administrator.getUserId());
		if (opt.isPresent()) {
			//Admin existAdmin = opt.get();
			return adminDao.save(administrator);
		}
		throw new AdministratorExceptions("Invalid Id");
	}

	@Override
	public Administrator delete(Integer id) throws AdministratorExceptions {
		Administrator existingAdministrator = adminDao.findById(id).orElseThrow(() -> new AdministratorExceptions("Invalid Id"));
		adminDao.delete(existingAdministrator);

		return existingAdministrator;
	}

	@Override //all trips detail of customer
	public List<TripBooking> getAllTrips(Integer customerid) throws AdministratorExceptions {
		//customer exception
		Optional<Customer> opt = customerDao.findById(customerid);
		if(opt.isPresent()) {
			List<TripBooking> trips = tripDao.findAll();
			return trips;
		
		}
		throw new AdministratorExceptions("Invalid Id");
	}

	@Override

	public List<TripBooking> getTripsGroupedByDriver() {
		
		List<TripBooking> list = tripDao.findByDriverAscs();
		
		if(list.size() > 0)
			return list;
		else
			throw new AdministratorExceptions("No trips found");
		
	}


	@Override
	public List<TripBooking> getTripsGroupedByCustomer() {
		List<TripBooking> list = tripDao.findByCustomeridAsce();
		if(list.size() > 0)
			return list;
		else
			throw new AdministratorExceptions("No trips found");
		 
	}

	@Override
	public List<TripBooking> getTripsOrderedByDate() throws AdministratorExceptions {
		List<TripBooking> list = tripDao.findByFromdate_timeAsce();
		if(list.size() > 0)
			return list;
		else
			throw new AdministratorExceptions("No trips found");
	}

	@Override
	public List<TripBooking> getTripsWithCustomerAndDate(Integer customerId, LocalDate date) throws AdministratorExceptions {
		List<TripBooking> list = tripDao.findByCustomerIdAndFromdate_time(customerId, date);
		if(list.size() > 0)
			return list;
		else
			throw new AdministratorExceptions("No trips found for customer id "+customerId+" and date : "+date);
	}


}
