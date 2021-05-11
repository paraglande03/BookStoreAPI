package com.bookstore.onlinebookstore.service;

import com.bookstore.onlinebookstore.dto.CustomerDto;
import com.bookstore.onlinebookstore.model.Customer;

public interface ICustomerService {
    Customer getCustomerDetails(String token);

    String addCustomerDetails(String token, CustomerDto customerDetails);
}
