package com.bookstore.onlinebookstore.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bookstore.onlinebookstore.dto.CustomerDto;
import com.bookstore.onlinebookstore.model.Order;

public interface IOrderService {

	Order placeOrder(UUID cartId, CustomerDto customerDTO);

}
