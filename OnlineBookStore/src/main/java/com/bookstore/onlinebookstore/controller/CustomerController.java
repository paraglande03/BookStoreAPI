package com.bookstore.onlinebookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.onlinebookstore.dto.CustomerDto;
import com.bookstore.onlinebookstore.dto.Response;
import com.bookstore.onlinebookstore.model.Customer;
import com.bookstore.onlinebookstore.service.ICustomerService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	public ICustomerService customerService;

	@ApiOperation("For adding customer details")
	@PostMapping("/details")
	public ResponseEntity<Response> customerDetails(@RequestHeader(value = "userId", required = false) Long userId,
			@RequestBody CustomerDto customer) {
		String responseMessage = customerService.addCustomerDetails(customer, userId);
		return new ResponseEntity<>(new Response(responseMessage), HttpStatus.OK);
	}

	@ApiOperation("For fetching customer details")
	@GetMapping("/details")
	public ResponseEntity<Response> getCustomerDetails(@RequestHeader(value = "userId") Long userId) {
		Customer userDetails = customerService.getCustomerDetails(userId);
		Response response = new Response("Customer details sent successfully", userDetails);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}