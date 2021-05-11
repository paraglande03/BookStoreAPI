package com.bookstore.onlinebookstore.service;

import com.bookstore.onlinebookstore.model.Order;

public interface IOrderService {


    Order getSummary(String token);

    Long placeOrder(String token);
}
