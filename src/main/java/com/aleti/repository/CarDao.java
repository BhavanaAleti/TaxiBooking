package com.aleti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aleti.entity.Car;

@Repository
public interface CarDao extends JpaRepository<Car, Integer> {

	
	@Query("select distinct carType from Cab")
	public List<String> viewCarType();


}
