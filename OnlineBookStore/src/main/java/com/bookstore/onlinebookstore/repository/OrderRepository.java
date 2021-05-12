package com.bookstore.onlinebookstore.repository;

import com.bookstore.onlinebookstore.model.Order;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderRepository extends JpaRepository<Order , UUID> {
}
