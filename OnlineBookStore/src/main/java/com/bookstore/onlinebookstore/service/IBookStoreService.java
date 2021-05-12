package com.bookstore.onlinebookstore.service;

import com.bookstore.onlinebookstore.dto.BookDTO;
import com.bookstore.onlinebookstore.model.Book;

import java.util.List;
import java.util.UUID;

public interface IBookStoreService {

    public List<Book> getAllBooks();

    public Book createBookData(BookDTO bookDTO);

	public Book getBookDataByBookId(UUID bookId);

	public void deleteBookDataByBookId(UUID bookId);

    public Book updateBookDataByBookId(UUID bookId, BookDTO bookDTO);

    long count();

    List<Book> getBooksByBookName(String bookName);

}
