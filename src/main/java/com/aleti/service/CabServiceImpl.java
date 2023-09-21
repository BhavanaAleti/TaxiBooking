package com.aleti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleti.entity.Car;
import com.aleti.entity.Driver;
import com.aleti.exception.NotFoundException;
import com.aleti.repository.CarDao;
import com.aleti.repository.DriverDao;

@Service("CabService")
public class CabServiceImpl implements CabService {

	@Autowired
	private CarDao cDao;
	

	@Autowired
	private DriverDao dDao;




	@Override
	public Car updateCab(Integer id, String type, Integer rate) throws NotFoundException {
		// TODO Auto-generated method stub
		java.util.Optional<Car> opt = cDao.findById(id);
	
		  if(opt.isPresent())
		  {
			  Car fCar =opt.get();
				fCar.setCarType(type);
				fCar.setRatePerKm(rate);
				Driver fDriver= fCar.getDriver();
				fDriver.setCar(fCar);;
				dDao.save(fDriver);
				return cDao.save(fCar);
			  
			  
			  /*
			  //Driver driver = dDao.getDriverByCabId(cab.getCabId());
		
			  Cab cab1 = opt.get();
			  Driver cabDriver=cab1.getDriver();
			  cabDriver.setCab(cab1);
			  dDao.save(cabDriver);
			 return cDao.save(cab1);*/
			  
			  
		  }else
		  {
			  throw new NotFoundException("Cab Not Found");
		  }
		 

			

	}

	

	@Override
	public List<String> viewCabsOfType() throws NotFoundException {
		// TODO Auto-generated method stub
		List<String> cabs = cDao.viewCarType();
		return cabs;
	}

	@Override
	public int countCabsOfType() throws NotFoundException {
		// TODO Auto-generated method stub
		List<Car> listcab = cDao.findAll();
		return listcab.size();
	}
	
	



	
	
}
