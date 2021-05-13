package com.bookstore.onlinebookstore.service.implementation;
import com.bookstore.onlinebookstore.dto.Response;
import com.bookstore.onlinebookstore.model.Book;
import com.bookstore.onlinebookstore.model.Cart;
import com.bookstore.onlinebookstore.repository.BookStoreRepository;
import com.bookstore.onlinebookstore.repository.CartRepository;
import com.bookstore.onlinebookstore.repository.OrderRepository;
import com.bookstore.onlinebookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CartService implements ICartService {

    @Autowired
    private BookStoreRepository bookStoreRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart addBookToCart(UUID bookId, Integer order_quantity) {
        Book book = bookStoreRepository.findById(bookId).orElse(null);
        if (book == null || book.getQuantity() == 0)
            return null;
        else {
            Cart cartItem = cartRepository.findByBookId(bookId);
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
    public String updateOrderQuantity(UUID bookId, Integer orderQuantity) {
        Book book = bookStoreRepository.findById(bookId).orElseThrow();
        if (book == null)
            return null;
        else {
            double subtotal = 0;
            if (book.getQuantity() >= orderQuantity) {
                cartRepository.updateOrderQuantity(orderQuantity, bookId);
                subtotal = book.getPrice() * orderQuantity;
                bookStoreRepository.updateQuantityAfterOrder(book.getQuantity() - orderQuantity + 1, bookId);
                return String.valueOf(subtotal);
            } else {
                return "Last "+book.getQuantity()+" are left.";
            }
        }
    }

    @Override
    public List<Cart> listCartItems() {
        return cartRepository.findAll();
    }

    @Override
    public void removeProduct(UUID cartId,UUID bookId) {
        Book book = bookStoreRepository.findById(bookId).orElseThrow();
        if (book == null)
            return ;
        Cart cartItem = cartRepository.findById(cartId).orElseThrow();
        bookStoreRepository.updateQuantityAfterOrder(book.getQuantity() + cartItem.getOrderQuantity(), bookId);
        cartRepository.deleteById(cartId);
    }

    @Override
    public List<Cart> getAllBooksFromWishList() {
        return null;
    }

    @Override
    public Response addBookToWishList(UUID bookId) {
        return null;
    }

    @Override
    public List<Cart> deleteBookFromWishlist(UUID bookId) {
        return null;
    }

    @Override
    public Response addBookFromWishlistToCart(UUID bookId) {
        return null;
    }

    @Override
    public Cart findCartByBook_BookId(UUID bookID) {
        return cartRepository.findCartByBook_BookId(bookID);
    }

}

