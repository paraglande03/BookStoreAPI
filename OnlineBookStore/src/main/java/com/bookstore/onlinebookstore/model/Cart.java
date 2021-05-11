package com.bookstore.onlinebookstore.model;

import java.beans.Transient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_items")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartId;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;



	private Integer orderQuantity;

	private boolean isInWishList;

	@Transient
	public double getSubTotal() {
		return this.book.getPrice() * orderQuantity;
	}
}