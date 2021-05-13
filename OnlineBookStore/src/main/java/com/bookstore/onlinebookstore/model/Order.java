package com.bookstore.onlinebookstore.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
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
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	

	@CreationTimestamp
	private LocalDate orderDate;
	@OneToOne(cascade = CascadeType.DETACH)
	private Customer customer;

	@OneToMany(cascade = CascadeType.DETACH)
	public List<Cart> cartItems;



}