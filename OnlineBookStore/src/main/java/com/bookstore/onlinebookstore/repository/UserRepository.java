package com.bookstore.onlinebookstore.repository;

import java.util.Optional;

import com.bookstore.onlinebookstore.service.implementation.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<UserService, Long> {

    @Query(value = "SELECT * FROM user where email_id=:emailId", nativeQuery = true)
    Optional<UserService> findByEmail(String emailId);

    @Query(value = "SELECT * FROM user where password=:password", nativeQuery = true)
    Optional<UserService> findByPassword(String password);

}