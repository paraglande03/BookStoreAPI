package com.bookstore.onlinebookstore.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bookstore.onlinebookstore.dto.CustomerDto;
import com.bookstore.onlinebookstore.dto.Response;
import com.bookstore.onlinebookstore.model.Order;
import com.bookstore.onlinebookstore.service.IOrderService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@ApiOperation("For placing order/")
	@PostMapping("/place/{cartId}")
	public ResponseEntity<Response> placeOrder(@RequestBody CustomerDto customerDTO,@PathVariable("cartId") UUID cartId) {
		Order orderId = orderService.placeOrder(cartId,customerDTO);
		return new ResponseEntity<>(new Response("Order placed successfully with order id : ", orderId), HttpStatus.OK);
	}
}