package com.aleti.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.aleti.entity.Address;

public interface AddressDao extends JpaRepository<Address, Integer> {

}
