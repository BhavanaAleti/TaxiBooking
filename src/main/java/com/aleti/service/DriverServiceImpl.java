package com.aleti.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleti.entity.Car;
import com.aleti.entity.Driver;
import com.aleti.exception.DriverNotFoundException;
import com.aleti.exception.InvalidIdException;
import com.aleti.repository.AddressDao;
import com.aleti.repository.CarDao;
import com.aleti.repository.DriverDao;
@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverDao dDao;
	
	@Autowired
	private CarDao cDao;
	@Autowired
	private AddressDao Adao;
	
	@Override
	public Driver insertDriver(Driver driver) {
		
		return dDao.save(driver);
	}

	@Override
	public Driver viewDriverById(Integer id) throws InvalidIdException {
		if(id<1)
			throw new InvalidIdException("Id should be more than 1");
		
		Optional<Driver> opt=dDao.findById(id);
		
		return opt.orElseThrow(() -> new DriverNotFoundException("No Driver found for id: "+id));
	}

	@Override
	public Driver updateDriver(Integer id,Integer license, Boolean available) throws DriverNotFoundException{
		Optional<Driver> opt= dDao.findById(id);
		if(opt.isPresent()) {

			Driver fDriver=opt.get();
			fDriver.setLicenseNo(license);
			fDriver.setAvailable(available);
			Car fCar = fDriver.getCar();
			fCar.setDriver(fDriver);
			cDao.save(fCar);
			return dDao.save(fDriver);

		}
			
		else
			throw new DriverNotFoundException("No Driver found ");
	}

	@Override

	public String deleteDriverById(Integer id) throws DriverNotFoundException{
          Driver d1=dDao.findById(id).orElseThrow(()-> new DriverNotFoundException("No Driver found"));
        
        cDao.deleteById(d1.getCar().getCabId());
        Adao.delete(d1.getAddress());
	    dDao.delete(d1);
			
	
		return "Driver Id "+id+ " deleted ";
	}

	@Override
	public List<Driver> viewBestDriver() throws DriverNotFoundException {
		List<Driver> drivers= dDao.viewBestDriver();
		if(drivers.size()>0)
			return drivers;
		else
			throw new DriverNotFoundException("No Driver found with rating>=4.5");
	}

	
	

}
