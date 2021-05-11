package com.bookstore.onlinebookstore.repository;

//import org.springframework.data.jpa.repository.JpaRepository;
//
import com.bookstore.onlinebookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookStoreRepository extends JpaRepository<Book, Long> {

    @Query("UPDATE Book SET quantity=?1 WHERE bookId=?2")
    @Modifying
    public void updateQuantityAfterOrder(Integer quantity, Long bookId);

    @Query(value = "select * from book order by created_date_and_time desc", nativeQuery = true)
    public List<Book> findBookOrderByCreatedDateAndTimeDesc();

    @Query(value = "select * from book where book_name LIKE %?1% ", nativeQuery = true)
    public List<Book> findBooksByBookName(String bookName);

}
