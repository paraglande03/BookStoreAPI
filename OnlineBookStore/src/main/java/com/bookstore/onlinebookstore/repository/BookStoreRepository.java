package com.bookstore.onlinebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.onlinebookstore.model.Book;

public interface BookStoreRepository extends JpaRepository<Book, Long> {

}
