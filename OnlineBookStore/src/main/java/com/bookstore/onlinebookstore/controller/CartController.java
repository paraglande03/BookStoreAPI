package com.bookstore.onlinebookstore.controller;

import com.bookstore.onlinebookstore.dto.Response;
import com.bookstore.onlinebookstore.model.Cart;
import com.bookstore.onlinebookstore.service.implementation.CartService;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {
	@Autowired
	private CartService cartService;

	@ApiOperation(value = "For getting all books in the cart")
	@GetMapping("/get")
	public ResponseEntity<Response> showCart(@RequestHeader String token) {
		List<Cart> userCart = cartService.listCartItems(token);
		return new ResponseEntity<>(new Response("Got all books from cart successfully", userCart), HttpStatus.OK);
	}

	@ApiOperation(value = "For getting count of all books in the cart")
	@GetMapping("/getCount")
	public int showCartCount(@RequestHeader String token) {
		List<Cart> userCart = cartService.listCartItems(token);
		return userCart.size();
	}
}
