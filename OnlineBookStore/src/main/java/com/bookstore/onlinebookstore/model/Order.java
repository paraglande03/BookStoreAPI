package com.bookstore.onlinebookstore.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.bookstore.onlinebookstore.dto.CustomerDto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Table(name = "ordered_items")
public @Data class Order {

	@Id
	private long orderId;

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
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;

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