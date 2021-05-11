package com.bookstore.onlinebookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomerDto {


	private String fullName;
	private String phoneNumber;
	private String locality;
	private String landMark;
	private String address;
	private String city;
	private String state;
	private String locationType;
	private long pinCode;
	private int userId;
}