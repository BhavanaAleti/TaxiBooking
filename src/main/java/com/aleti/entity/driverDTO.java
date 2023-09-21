package com.aleti.entity;


import lombok.Data;


@Data
public class driverDTO {
	
	private Integer userId;
	
	private String Username;
	
	private String Mobile;
	
	private Address address;
	
	private String Email;
	
	private Integer licenseNo;
	
	private Double rating;
	
	private Boolean available;

	
	private Car car;

	private TripBooking tripBooking;
}
