package com.bookstore.onlinebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.onlinebookstore.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookStoreRepository extends JpaRepository<Book, Long> {

    @Query(value = "select * from book where book_name LIKE %?1% ", nativeQuery = true)
    public List<Book> findBooksByBookName(String bookName);
}
