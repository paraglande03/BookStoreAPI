package com.bookstore.onlinebookstore.controller;

import com.bookstore.onlinebookstore.dto.Response;
import com.bookstore.onlinebookstore.model.Cart;
import com.bookstore.onlinebookstore.service.ICartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController
{
    @Autowired
    private ICartService iCartService;


    @ApiOperation(value = "For getting all books in the cart")
    @GetMapping("/all")
    public ResponseEntity<Response> showCart() {
        List<Cart> userCart = iCartService.listCartItems();
        return new ResponseEntity<>(new Response( "Got all books from cart successfully", userCart), HttpStatus.OK);
    }

    @ApiOperation(value = "For getting count of all books in the cart")
    @GetMapping("/count")
    public int showCartCount() {
        List<Cart> userCart = iCartService.listCartItems();
        return userCart.size();
    }

    @ApiOperation(value = "For adding the book to cart")
    @PostMapping("/add/{bookId}")
    public ResponseEntity<Response> addToCart(@PathVariable("bookId") UUID bookId) {
        Cart cartItem = iCartService.addBookToCart( bookId, 1);
        if(cartItem != null)
            return new ResponseEntity<>(new Response( "Book added to cart successfully", cartItem), HttpStatus.OK);
        return new ResponseEntity<>(new Response("Book do not exist!!"), HttpStatus.NOT_ACCEPTABLE);
    }

    @ApiOperation(value = "For updating book quantity")
    @PostMapping("/update/{bookId}/{orderQuantity}")
    public ResponseEntity<Response> updateBookOrderQuantity(@PathVariable("bookId") UUID bookId, @PathVariable("orderQuantity") Integer orderQuantity) {
        String value = iCartService.updateOrderQuantity(bookId, orderQuantity);
        if(value != null)
            return new ResponseEntity<>(new Response( value), HttpStatus.OK);
        return new ResponseEntity<>(new Response("Book do not exist!!"), HttpStatus.NOT_ACCEPTABLE);
    }

    @ApiOperation(value = "For removing book from Cart")
    @DeleteMapping("/remove/{cartId}/{bookId}")
    public ResponseEntity<Response> removeFromCart(@PathVariable("cartId") UUID cartId, @PathVariable("bookId") UUID bookId) {
        iCartService.removeProduct(cartId,bookId);
        return new ResponseEntity<>(new Response( "Book removed from cart successfully"), HttpStatus.OK);
    }

    @ApiOperation(value = "For getting all books from wishlist")
    @GetMapping("/wishlist/all")
    public List<Cart> getWishListBooks() {
        return iCartService.getAllBooksFromWishList();
    }

    @ApiOperation(value = "For adding book to wishlist")
    @PostMapping("/wishlist/add/{bookId}")
    public Response addToWishList(@PathVariable("bookId") UUID bookId) {
        return iCartService.addBookToWishList(bookId);
    }

    @ApiOperation(value = "For deleting book from wishlist")
    @DeleteMapping("/wishlist/delete/{bookId}")
    public ResponseEntity<Response> deleteBookFromWishlist(@PathVariable("bookId") UUID bookId) {
        List<Cart> cart = iCartService.deleteBookFromWishlist(bookId);
        return new ResponseEntity<>(new Response("Book removed from wishlist", cart), HttpStatus.OK);
    }

    @ApiOperation(value = "For putting books wishlist to cart")
    @PutMapping("/wishlist/addTocart/{bookId}")
    public Response addBookFromWishlistToCart(@PathVariable("bookId") UUID bookId) {
        return iCartService.addBookFromWishlistToCart(bookId);
    }

    @ApiOperation(value = "For adding book to wishlist")
    @GetMapping("/get/{bookId}")
    public Cart findCartByBook_BookId(@PathVariable("bookId") UUID bookId) {
        return iCartService.findCartByBook_BookId(bookId);
    }

}
