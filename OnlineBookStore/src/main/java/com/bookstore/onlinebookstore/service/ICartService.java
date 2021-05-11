package com.bookstore.onlinebookstore.service;

import com.bookstore.onlinebookstore.dto.Response;
import com.bookstore.onlinebookstore.model.Cart;

import java.util.List;

public interface ICartService {

    Cart addBookToCart(String token, Long bookId, Integer order_quantity);

    String updateOrderQuantity(Long bookId, Integer orderQuantity, String token);

    List<Cart> listCartItems(String token);

    void removeProduct(Long bookId, String token);

    List<Cart> getAllBooksFromWishList(String token);

    Response addBookToWishList(Long bookId, String token);

    List<Cart> deleteBookFromWishlist(Long bookId, String token);

    Response addBookFromWishlistToCart(Long bookId, String token);
}
