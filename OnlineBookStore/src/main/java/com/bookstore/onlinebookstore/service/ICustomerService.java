package com.bookstore.onlinebookstore.service;

import com.bookstore.onlinebookstore.dto.CustomerDto;
import com.bookstore.onlinebookstore.model.Customer;

public interface ICustomerService {
    Customer getCustomerDetails(Long userId);

    String addCustomerDetails( CustomerDto customerDetails,Long userId);
}
