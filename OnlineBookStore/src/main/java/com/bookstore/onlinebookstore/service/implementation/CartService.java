package com.bookstore.onlinebookstore.service.implementation;

import com.bookstore.onlinebookstore.model.Book;
import com.bookstore.onlinebookstore.model.Cart;
import com.bookstore.onlinebookstore.repository.BookStoreRepository;
import com.bookstore.onlinebookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;

public class CartService implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookStoreRepository bookStoreRepository;



    @Override
    public Cart addBookToCart(String token, Long bookId, Integer order_quantity) {
//        Long userId = JwtGenerator.decodeJWT(token);
        Book book = bookStoreRepository.findById(bookId).orElse(null);
        if (book == null || book.getQuantity() == 0)
            return null;
        else {
            User user = userRepository.findById(userId).orElse(null);

            Cart cartItem = cartRepository.findByUserIdAndBookId(userId, bookId);

            if (cartItem != null) {
                cartItem.setOrderQuantity(order_quantity);
            } else {
                cartItem = new Cart();
                cartItem.setOrderQuantity(order_quantity);
                cartItem.setUser(user);
                cartItem.setBook(book);
                bookStoreRepository.updateQuantityAfterOrder(book.getQuantity() - order_quantity, bookId);

            }
            cartRepository.save(cartItem);
            return cartItem;
        }
    }

    @Override
    public String updateOrderQuantity(Long bookId, Integer order_quantity, String token) {
        Long userId = JwtGenerator.decodeJWT(token);
        Book book = bookStoreRepository.findById(bookId).orElse(null);
        if (book == null)
            return null;
        else {
            double subtotal = 0;
            if (book.getQuantity() >= order_quantity) {
                cartRepository.updateOrderQuantity(order_quantity, bookId, userId);
                subtotal = book.getPrice() * order_quantity;
                bookStoreRepository.updateQuantityAfterOrder(book.getQuantity() - order_quantity+1, bookId);
                return String.valueOf(subtotal);
            } else {
                return "Last "+book.getQuantity()+" are left.";
            }
        }
    }

    @Override
    public List<Cart> listCartItems(String token) {
        Long userId = JwtGenerator.decodeJWT(token);
        return cartRepository.findByUserId(userId);
    }
}
