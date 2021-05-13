package com.bookstore.onlinebookstore.repository;

import com.bookstore.onlinebookstore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart , UUID> {

//    @Query(value = "select * from cart_items where user_id=:userId", nativeQuery = true)
//    public List<Cart> findByUserId(Long userId);
//
//    @Query(value = "select * from cart_items where user_id=:userId", nativeQuery = true)
//    Cart findByUserIds(Long userId);

    @Query("UPDATE Cart c SET c.orderQuantity=?1 WHERE c.book.bookId=?2 ")
    @Modifying
    void updateOrderQuantity(Integer order_quantity, UUID bookId);

    @Query("DELETE FROM Cart c WHERE c.book.bookId=?1")
    @Modifying
    void deleteByBook(UUID bookId);

    @Query(value = "select * from cart_items where book_id=:bookId", nativeQuery = true)
    Cart findByBookId(UUID bookId);

    Cart findCartByBook_BookId(UUID bookID);




    @Query(value = "select book_id from cart_items where book_id=:bookId", nativeQuery = true)
    Long findDuplicateBookId(UUID bookId);
}
