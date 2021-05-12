package com.bookstore.onlinebookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.bookstore.onlinebookstore.dto.CustomerDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	private String fullName;
	private String phoneNumber;
	private String address;
	private String city;
	private String state;
	private LocationType type;
	private long pinCode;
	/*
	 * @OneToOne private Order order;
	 */

	public Customer(CustomerDto customerDto) {
		this.fullName = customerDto.getFullName();
		this.phoneNumber = customerDto.getPhoneNumber();

		this.address = customerDto.getAddress();
		this.state = customerDto.getState();
		this.city = customerDto.getCity();

		this.type = customerDto.getType();
		this.pinCode = customerDto.getPinCode();
	}

	
}
