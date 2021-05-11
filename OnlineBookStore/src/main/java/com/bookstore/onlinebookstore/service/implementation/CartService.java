package com.bookstore.onlinebookstore.service.implementation;

import com.bookstore.onlinebookstore.dto.Response;
import com.bookstore.onlinebookstore.model.Book;
import com.bookstore.onlinebookstore.model.Cart;
import com.bookstore.onlinebookstore.repository.BookStoreRepository;
import com.bookstore.onlinebookstore.repository.CartRepository;
import com.bookstore.onlinebookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartService implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookStoreRepository bookStoreRepository;





    @Override
    public Cart addBookToCart( Long bookId, Integer order_quantity,long userId) {

        Book book = bookStoreRepository.findById(bookId).orElse(null);
        if (book == null || book.getQuantity() == 0)
            return null;
        else {


            Cart cartItem = cartRepository.findByUserIdAndBookId(userId, bookId);

            if (cartItem != null) {
                cartItem.setOrderQuantity(order_quantity);
            } else {
                cartItem = new Cart();
                cartItem.setOrderQuantity(order_quantity);

                cartItem.setBook(book);
                bookStoreRepository.updateQuantityAfterOrder(book.getQuantity() - order_quantity, bookId);

            }
            cartRepository.save(cartItem);
            return cartItem;
        }
    }

    @Override
    public String updateOrderQuantity(Long bookId, Integer order_quantity) {
//        Long userId = JwtGenerator.decodeJWT(token);
        Book book = bookStoreRepository.findById(bookId).orElse(null);
        if (book == null)
            return null;
        else {
            double subtotal = 0;
            if (book.getQuantity() >= order_quantity) {
                cartRepository.updateOrderQuantity(order_quantity, bookId);
                subtotal = book.getPrice() * order_quantity;
                bookStoreRepository.updateQuantityAfterOrder(book.getQuantity() - order_quantity+1, bookId);
                return String.valueOf(subtotal);
            } else {
                return "Last "+book.getQuantity()+" are left.";
            }
        }
    }

    @Override
    public List<Cart> listCartItems(long userId) {

        return cartRepository.findByUserId(userId);
    }

    @Override
    public void removeProduct(Long bookId, long userId) {

        Book book = bookStoreRepository.findById(bookId).orElse(null);
        if (book == null)
            return ;
        Cart cartItem = cartRepository.findByUserIdAndBookId(userId, bookId);
        bookStoreRepository.updateQuantityAfterOrder(book.getQuantity() + cartItem.getOrderQuantity(), bookId);
        cartRepository.deleteByUserAndBook(userId, bookId);
    }

    @Override
    public List<Cart> getAllBooksFromWishList(long userId) {

        List<Cart> cartItems = cartRepository.findByUserId(userId).stream().filter(Cart::isInWishList)
                .collect(Collectors.toList());
        if (cartItems.isEmpty())
            return new ArrayList<>();
        return cartItems;
    }

    @Override
    public Response addBookToWishList(Long bookId,  Long userId) {
        Cart cartItem = cartRepository.findByUserIdAndBookId(userId, bookId);
        Long cartBookId = cartRepository.findDuplicateBookId(bookId);
        if (cartBookId != bookId) {
            if (cartItem != null && cartItem.isInWishList()) {
                return new Response( "Book already present in wishlist");
            } else if (cartItem != null && !cartItem.isInWishList()) {
                return new Response( "Book already added to Cart");
            } else {
                Book book = bookStoreRepository.findById(bookId).orElse(null);
                if (book == null)
                    return null;
                Cart cart = new Cart();

                cart.setBook(book);
                cart.setOrderQuantity(1);
                cart.setInWishList(true);
                cartRepository.save(cart);
                return new Response( "Book added to WishList");
            }
        }
        return new Response( "Book already present in wishlist");
    }

    @Override
    public List<Cart> deleteBookFromWishlist(Long bookId,  Long userId) {

        List<Cart> cartItems = cartRepository.findByUserId(userId).stream().filter(Cart::isInWishList)
                .collect(Collectors.toList());
        List<Cart> selectedItems = cartItems.stream().filter(cartItem -> cartItem.getBook().getBookId().equals(bookId))
                .collect(Collectors.toList());
        for (Cart book : selectedItems) {
            cartRepository.delete(book);
        }
        return cartRepository.findByUserId(userId);
    }

    @Override
    public Response addBookFromWishlistToCart(Long bookId, String token, long userId) {

        Cart cartItem = cartRepository.findByUserIdAndBookId(userId, bookId);
        if (cartItem.isInWishList()) {
            cartItem.setInWishList(false);
            cartRepository.save(cartItem);
            return new Response( "Successfully added book from wishlist to cart.");
        }
        return new Response( "Already present in cart, ready to checkout");
    }
}
