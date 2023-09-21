package com.aleti.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aleti.entity.Customer;



@Repository
public interface CustomerDao extends JpaRepository<Customer,Integer>{
	
}


