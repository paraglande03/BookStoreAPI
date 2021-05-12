package com.bookstore.onlinebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.onlinebookstore.model.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
