package com.bookstore.onlinebookstore.repository;

import java.util.List;
import java.util.Optional;

import com.bookstore.onlinebookstore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByUserId(Long userId);

   
}