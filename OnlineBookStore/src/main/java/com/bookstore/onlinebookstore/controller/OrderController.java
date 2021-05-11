package com.bookstore.onlinebookstore.controller;

import com.bookstore.onlinebookstore.dto.Response;
import com.bookstore.onlinebookstore.model.Order;
import com.bookstore.onlinebookstore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @ApiOperation("For getting order summary")
    @GetMapping("/summary")
    public ResponseEntity<Response> getOrderSummary(@RequestHeader String token) {
        Order orderDetails = orderService.getSummary(token);
        return new ResponseEntity<>(new Response( "Got order summary successfully", orderDetails), HttpStatus.OK);
    }

    @ApiOperation("For placing order")
    @PostMapping("/place")
    public ResponseEntity<Response> placeOrder(@RequestHeader String token) {
        Long orderId = orderService.placeOrder(token);
        return new ResponseEntity<>(new Response("Order placed successfully with order id : ", orderId), HttpStatus.OK);
    }
}