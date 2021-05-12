package com.bookstore.onlinebookstore.service.implementation;

import com.bookstore.onlinebookstore.dto.CustomerDto;
import com.bookstore.onlinebookstore.model.Book;
import com.bookstore.onlinebookstore.model.Cart;
import com.bookstore.onlinebookstore.model.Order;
import com.bookstore.onlinebookstore.repository.BookStoreRepository;
import com.bookstore.onlinebookstore.repository.CartRepository;
import com.bookstore.onlinebookstore.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CartService {

    @Autowired
    private BookStoreRepository bookStoreRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List <Book> addBook(UUID bookId){
        Cart cart = new Cart();
        Book book = bookStoreRepository.findById(bookId).orElseThrow();
        List<Book> books = null;
        books.add(book);
        cart.setBook(books);
        cartRepository.save(cart);
        return books;

    }







}
