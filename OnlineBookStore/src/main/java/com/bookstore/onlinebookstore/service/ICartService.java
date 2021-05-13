package com.bookstore.onlinebookstore.service;

import com.bookstore.onlinebookstore.dto.CartDTO;
import com.bookstore.onlinebookstore.dto.Response;
import com.bookstore.onlinebookstore.model.Book;
import com.bookstore.onlinebookstore.model.Cart;

import java.util.List;
import java.util.UUID;

public interface ICartService
{
    Cart addBookToCart( UUID bookId, Integer order_quantity);

    String updateOrderQuantity(UUID bookId, Integer orderQuantity);

    List<Cart> listCartItems();

    void removeProduct(UUID cartId,UUID bookID);

    List<Cart> getAllBooksFromWishList();

    Response addBookToWishList(UUID bookId);

    List<Cart> deleteBookFromWishlist(UUID bookId);

    Response addBookFromWishlistToCart(UUID bookId);
    Cart findCartByBook_BookId(UUID bookID);

}
