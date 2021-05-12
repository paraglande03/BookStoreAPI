package com.bookstore.onlinebookstore.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.bookstore.onlinebookstore.dto.CustomerDto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Table(name = "ordered_items")
public @Data class Order {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Type(type = "uuid-char")
	private UUID orderId;

	@OneToMany
	private List<Book> book;

	private double totalPrice;
	private String fullName;
	private String phoneNumber;

	private String address;
	private String city;
	private String state;
	private LocationType type;
	private long pinCode;

	@CreationTimestamp
	private LocalDate orderDate;

	@OneToMany()
	public List<Cart> cartItems;

	public Order(CustomerDto customerDto) {

		this.fullName = customerDto.getFullName();
		this.address = customerDto.getAddress();
		this.city = customerDto.getCity();
		this.phoneNumber = customerDto.getPhoneNumber();
		this.pinCode = customerDto.getPinCode();
		this.type = customerDto.getType();
		this.state = customerDto.getState();
		this.orderId = orderId;

	}

}