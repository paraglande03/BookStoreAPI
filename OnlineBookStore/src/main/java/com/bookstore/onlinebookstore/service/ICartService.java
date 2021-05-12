package com.bookstore.onlinebookstore.service;

import com.bookstore.onlinebookstore.dto.Response;
import com.bookstore.onlinebookstore.model.Cart;

import java.util.List;
import java.util.UUID;

public interface ICartService {

    Cart addBookToCart(UUID bookId, Integer order_quantity, long userId);

    String updateOrderQuantity(UUID bookId, Integer orderQuantity);

    public List<Cart> listCartItems(long userId);

    void removeProduct(UUID bookId,long userId);

    List<Cart> getAllBooksFromWishList(long userId);

    public Response addBookToWishList(UUID bookId,  Long userId);

    public List<Cart> deleteBookFromWishlist(UUID bookId,  Long userId);

    public Response addBookFromWishlistToCart(UUID bookId,  long userId);
}
