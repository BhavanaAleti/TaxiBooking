package com.aleti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleti.entity.Administrator;


@Repository
public interface AdminDao extends JpaRepository<Administrator, Integer> {
	
	

}
