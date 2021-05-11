package com.bookstore.onlinebookstore.controller;

import java.util.List;
import javax.validation.Valid;

import com.bookstore.onlinebookstore.dto.BookDTO;
import com.bookstore.onlinebookstore.dto.Response;
import com.bookstore.onlinebookstore.model.Book;
import com.bookstore.onlinebookstore.service.IBookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/book")
public class BookController {

    @Autowired
    public IBookStoreService bookStoreService;

    @ApiOperation("For getting all books")
    @GetMapping("/getBooks")
    public ResponseEntity<Response> getAllBooks() {
        List<Book> booksList = bookStoreService.getAllBooks();
        if (booksList != null)
            return new ResponseEntity<>(new Response( "Returned all books successfully", booksList), HttpStatus.OK);
        return new ResponseEntity<>(new Response( "Don't have any books!!"), HttpStatus.NOT_ACCEPTABLE);
    }


    @ApiOperation("For Inserting a book")
    @PostMapping("/create")
    public ResponseEntity<Response> createBookData(@RequestBody BookDTO bookDTO) {
        Book booksList = bookStoreService.createBookData(bookDTO);
        return new ResponseEntity<>(new Response( "Inserted book data successfully!!", booksList), HttpStatus.OK);
    }
}