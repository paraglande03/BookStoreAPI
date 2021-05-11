package com.bookstore.onlinebookstore.service;

import com.bookstore.onlinebookstore.dto.Response;
import com.bookstore.onlinebookstore.model.Cart;

import java.util.List;

public interface ICartService {

    Cart addBookToCart( Long bookId, Integer order_quantity,long userId);

    String updateOrderQuantity(Long bookId, Integer orderQuantity);

    public List<Cart> listCartItems(long userId);

    void removeProduct(Long bookId,long userId);

    List<Cart> getAllBooksFromWishList(long userId);

    public Response addBookToWishList(Long bookId,  Long userId);

    public List<Cart> deleteBookFromWishlist(Long bookId,  Long userId);

    public Response addBookFromWishlistToCart(Long bookId, String token, long userId);
}
