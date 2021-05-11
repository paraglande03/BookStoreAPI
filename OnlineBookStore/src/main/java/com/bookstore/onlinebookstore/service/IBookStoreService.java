package com.bookstore.onlinebookstore.service;

import com.bookstore.onlinebookstore.dto.BookDTO;
import com.bookstore.onlinebookstore.model.Book;

import java.util.List;

public interface IBookStoreService {

    public List<Book> getAllBooks();

    public Book createBookData(BookDTO bookDTO);

	public Book getBookDataByBookId(long bookId);

	public void deleteBookDataByBookId(long bookId);
}
