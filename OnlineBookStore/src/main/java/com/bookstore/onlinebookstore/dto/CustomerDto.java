package com.bookstore.onlinebookstore.dto;

import com.bookstore.onlinebookstore.model.LocationType;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomerDto {

	private String fullName;
	private String phoneNumber;
	private String address;
	private String city;
	private String state;
	private  LocationType type;
	private long pinCode;
	
}