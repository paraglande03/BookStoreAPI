package com.bookstore.onlinebookstore.service;

import com.bookstore.onlinebookstore.model.Cart;

import java.util.List;

public interface ICartService {

    public Cart addBookToCart(String token, Long bookId, Integer order_quantity);

    public String updateOrderQuantity(Long bookId, Integer order_quantity, String token);

    public List<Cart> listCartItems(String token);
}
