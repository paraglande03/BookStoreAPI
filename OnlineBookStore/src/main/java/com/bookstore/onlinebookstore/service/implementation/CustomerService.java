package com.bookstore.onlinebookstore.service.implementation;

import com.bookstore.onlinebookstore.dto.CustomerDto;
import com.bookstore.onlinebookstore.model.Customer;
import com.bookstore.onlinebookstore.repository.CustomerRepository;
import com.bookstore.onlinebookstore.repository.UserRepository;
import com.bookstore.onlinebookstore.service.ICustomerService;
import com.bookstore.onlinebookstore.uitility.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public CustomerRepository customerRepository;

    @Override
    public Customer getCustomerDetails(String token) {
        Long userId = JwtGenerator.decodeJWT(token);
        Optional<Customer> customer = customerRepository.findByUserId(userId);
        return customer.get();
    }

    @Override
    public String addCustomerDetails(String token, CustomerDto customerDto) {
        Long userId = JwtGenerator.decodeJWT(token);
        Optional<UserService> user = userRepository.findById(userId);
        Optional<Customer> isCustomerAvailable = customerRepository.findByUserId(user.get().getUserId());
        if (isCustomerAvailable.isPresent()) {
            return "Your details are already saved";
        }
        Customer customer = new Customer(customerDto);
        customer.setUserId(userId);
        customerRepository.save(customer);
        return "Customer details added successfully";
    }
}