package com.bookstore.onlinebookstore.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
public @Data class Cart {

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