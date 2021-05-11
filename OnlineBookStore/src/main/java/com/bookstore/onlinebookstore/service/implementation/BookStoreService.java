package com.bookstore.onlinebookstore.service.implementation;

import com.bookstore.onlinebookstore.dto.BookDTO;
import com.bookstore.onlinebookstore.model.Book;
import com.bookstore.onlinebookstore.repository.BookStoreRepository;
import com.bookstore.onlinebookstore.service.IBookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
    public class BookStoreService implements IBookStoreService {
        @Autowired
        public BookStoreRepository bookstoreRepository;

        @Override
        public List<Book> getAllBooks() {
            List<Book> booksList = bookstoreRepository.findAll();
            if (booksList.isEmpty()) {
                return null;
            }
            return booksList;
        }


    @Override
    public Book createBookData(BookDTO bookDTO) {
        Book bookData = null;
        bookData = new Book(bookDTO);
        return bookstoreRepository.save(bookData);
    }

    }
