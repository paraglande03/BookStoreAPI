package com.bookstore.onlinebookstore.repository;

import java.util.Optional;

import com.bookstore.onlinebookstore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM ordered_items where user_id=:userId", nativeQuery = true)
    Optional<Order> findByUserId(Long userId);

}