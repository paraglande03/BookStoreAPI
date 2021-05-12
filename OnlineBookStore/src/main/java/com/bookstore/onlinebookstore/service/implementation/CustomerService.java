package com.bookstore.onlinebookstore.service.implementation;

import com.bookstore.onlinebookstore.dto.CustomerDto;
import com.bookstore.onlinebookstore.model.Book;
import com.bookstore.onlinebookstore.model.Customer;
import com.bookstore.onlinebookstore.repository.CustomerRepository;
import com.bookstore.onlinebookstore.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	public CustomerRepository customerRepository;

	@Override
	public Customer getCustomerDetails(Long userId) {

		return customerRepository.findByUserId(userId);
	}

	@Override
	public String addCustomerDetails(CustomerDto customerDto, Long userId) {
		Customer customer = new Customer(customerDto);
		customer.setUserId(userId);
		customerRepository.save(customer);
		return "Customer details added successfully";
	}
}