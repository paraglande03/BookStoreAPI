package com.bookstore.onlinebookstore.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bookstore.onlinebookstore.dto.BookDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2" , strategy = "uuid2")
	@Type(type = "uuid-char")
	private UUID bookId;

	private String authorName;
	private String bookName;

	private Integer quantity;
	public Double price;
	private String bookDetails;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date_and_time")
	private Date createdDateAndTime;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date_and_time")
	private Date updatedDateAndTime;

	public Book(BookDTO bookDTO) {
		this.updateBookDataByBookId(bookDTO);
	}

	public void updateBookDataByBookId(BookDTO bookDTO) {

		this.authorName = bookDTO.authorName;
		this.bookName = bookDTO.bookName;
		this.quantity = bookDTO.quantity;
		this.price = bookDTO.price;
		this.bookDetails = bookDTO.bookDetails;

	}

}