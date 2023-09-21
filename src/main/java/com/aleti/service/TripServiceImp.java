package com.aleti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleti.entity.Driver;
import com.aleti.entity.TripBooking;
import com.aleti.exception.DriverNotFoundException;
import com.aleti.exception.InvalidIdException;
import com.aleti.repository.CustomerDao;
import com.aleti.repository.DriverDao;
import com.aleti.repository.TripDao;


@Service
public class TripServiceImp implements TripService {
    @Autowired
	TripDao trip;
    @Autowired
    CustomerDao cdao;
    @Autowired
    DriverDao ddao;
	
	@Override
	public TripBooking AddTrip(TripBooking tb) throws InvalidIdException {
		
		cdao.findById(tb.getCustomerId()).orElseThrow(() -> new InvalidIdException("Customer with ID "+tb.getCustomerId()+" does not exit.."));
		List<Driver> drivers= ddao.findByAvailable() ;
		if(drivers.size()==0)
		{
			throw new DriverNotFoundException("Sorry No driver Available just now...");
		}
//		drivers.forEach((e)->System.out.println(e.getUserId()));
//		System.out.println(drivers.size());
		drivers.get(0).setAvailable(false);
		ddao.save(drivers.get(0));
		 Integer km = tb.getKm();
	     Integer price=drivers.get(0).getCar().getRatePerKm();
	     tb.setTotalamount(km*price);
		 tb.setDriver(drivers.get(0));
		return trip.save(tb);
	}

	
	@Override
	public List<TripBooking> alltrip() {
		
		return trip.findAll(); 
	}

	@Override
	public TripBooking updateTrip(TripBooking tb,Integer id) throws InvalidIdException {
		
	
		TripBooking c1=trip.getById(id);
		
		c1.setCustomerId(tb.getCustomerId());
		c1.setFrom_location(tb.getFrom_location());
		c1.setTo_location(tb.getTo_location());
		c1.setFromdate_time(tb.getFromdate_time());
		c1.setTodate_time(tb.getTodate_time());
		c1.setKm(tb.getKm());
		
            trip.save(c1);		
		return c1;
	}

	@Override
	public String deletetrip(Integer id) throws InvalidIdException {
		TripBooking ct=trip.findById(id).orElseThrow(() -> new InvalidIdException("TripBooking with ID "+id+" does not exit.."));
		
		ct.setDriver(null);
		trip.delete(ct);

		return "delete...";
	}


	@Override
	public TripBooking tripEnd(Integer id) throws InvalidIdException {
		
		TripBooking ct=trip.findById(id).orElseThrow(() -> new InvalidIdException("TripBooking with ID "+id+" does not exit.."));
	    
		Integer driverid=ct.getDriver().getUserId();
		Driver dt=ddao.findById(driverid).orElseThrow(() -> new InvalidIdException("Drive with ID "+driverid+" does not exit.."));
	 
		dt.setAvailable(true);
		ddao.save(dt);
		ct.setPayment(true);
	     
	     
		return trip.save(ct);
	}

}
