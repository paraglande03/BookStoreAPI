package com.bookstore.onlinebookstore.controller;

import java.util.List;


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
			return new ResponseEntity<>(new Response("Returned all books successfully", booksList), HttpStatus.OK);
		return new ResponseEntity<>(new Response("Don't have any books!!"), HttpStatus.NOT_ACCEPTABLE);
	}

	@ApiOperation("For Inserting a book")
	@PostMapping("/create")
	public ResponseEntity<Response> createBookData(@RequestBody BookDTO bookDTO) {
		Book booksList = bookStoreService.createBookData(bookDTO);
		return new ResponseEntity<>(new Response("Inserted book data successfully!!", booksList), HttpStatus.OK);
	}

	@ApiOperation("For deleting a book details by book id")
	@DeleteMapping("/delete/{bookId}")
	public ResponseEntity<Response> deleteBookDataByBookId(@PathVariable("bookId") long bookId) {
		bookStoreService.deleteBookDataByBookId(bookId);
		return new ResponseEntity<>(new Response("Deleted successfully!!", "Deleted id:" + bookId), HttpStatus.OK);
	}

    @ApiOperation("To get book by book id")
    @GetMapping("/getBook/{bookId}")
    public ResponseEntity<Response> getBookDataByBookId(@PathVariable("bookId") long bookId)  {
        Book booksList = bookStoreService.getBookDataByBookId(bookId);
        if (booksList != null)
            return new ResponseEntity<>(new Response( "Get call for ID successfull", booksList), HttpStatus.OK);
        return new ResponseEntity<>(new Response( "Book does not exists!!"), HttpStatus.NOT_ACCEPTABLE);
    }

    @ApiOperation("For updating a book details by book id")
    @PutMapping("/update/{bookId}")
    public ResponseEntity<Response> updateBookDataByBookId(@PathVariable("bookId") long bookId,
                                                           @RequestBody BookDTO bookDTO) {
        Book booksList = bookStoreService.updateBookDataByBookId(bookId, bookDTO);
        return new ResponseEntity<>(new Response( "Updated book data successfully!!", booksList), HttpStatus.OK);
    }
	@ApiOperation("For counting number of records in database")
	@GetMapping("/count")
	public ResponseEntity<Response> list() {
		long count = bookStoreService.count();
		return new ResponseEntity<>(new Response("Got count of books successfully!!", count), HttpStatus.OK);
	}
	@ApiOperation("To get book by book name")
	@GetMapping("/getBookList/{bookName}")
	public ResponseEntity<Response> getBookDataByBookName(@PathVariable("bookName") String bookName) {
		List<Book> books = bookStoreService.getBooksByBookName(bookName);
		if (books != null)
			return new ResponseEntity<>(new Response("Get call for book successfull", books), HttpStatus.OK);
		else
			return new ResponseEntity<>(new Response("Book does not exists!!"), HttpStatus.NOT_ACCEPTABLE);
	}
}