package com.bookstore.onlinebookstore.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.ToString;

@ToString
public class BookDTO {
	//@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = " Author Name is Invalid")
	public String authorName;
	//@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = " Book Name is Invalid")
	public String bookName;

	public Integer quantity;
	//@NotBlank(message = "Price Cannot be empty")
	public double price;

	public String bookDetails;

}