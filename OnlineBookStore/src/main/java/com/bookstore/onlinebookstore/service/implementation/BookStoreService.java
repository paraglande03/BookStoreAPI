package com.bookstore.onlinebookstore.service.implementation;

import com.bookstore.onlinebookstore.dto.BookDTO;
import com.bookstore.onlinebookstore.model.Book;
import com.bookstore.onlinebookstore.repository.BookStoreRepository;
import com.bookstore.onlinebookstore.service.IBookStoreService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookStoreService implements IBookStoreService {
	@Autowired
	public BookStoreRepository bookstoreRepository;

	@Override
	public List<Book> getAllBooks() {
		List<Book> booksList = bookstoreRepository.findAll();
		if (booksList.isEmpty()) {
			return null;
		}
		return booksList;
	}

	@Override
	public Book createBookData(BookDTO bookDTO) {
		Book bookData = null;
		bookData = new Book(bookDTO);
		return bookstoreRepository.save(bookData);
	}

	public Book getBookDataByBookId(UUID bookId) {
		return bookstoreRepository.findById(bookId).orElse(null);
	}

	@Override
	public void deleteBookDataByBookId(UUID bookId) {
		Book bookData = this.getBookDataByBookId(bookId);
		bookstoreRepository.delete(bookData);
	}
	@Override
	public Book updateBookDataByBookId(UUID bookId, BookDTO bookDTO) {
		Book bookData = this.getBookDataByBookId(bookId);
		bookData.updateBookDataByBookId(bookDTO);
		return bookstoreRepository.save(bookData);
	}

	public long count() {
		return bookstoreRepository.count();
	}


	@Override
	public List<Book> getBooksByBookName(String bookName) {
		List<Book> booksList = bookstoreRepository.findBooksByBookName(bookName);
		if (booksList.isEmpty()) {
			return null;
		}
		return booksList;
	}
}
