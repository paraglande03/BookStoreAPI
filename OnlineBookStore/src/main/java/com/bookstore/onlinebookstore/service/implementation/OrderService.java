package com.bookstore.onlinebookstore.service.implementation;

import com.bookstore.onlinebookstore.dto.CustomerDto;
import com.bookstore.onlinebookstore.model.Cart;
import com.bookstore.onlinebookstore.model.Order;
import com.bookstore.onlinebookstore.repository.BookStoreRepository;
import com.bookstore.onlinebookstore.repository.CartRepository;
import com.bookstore.onlinebookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class OrderService {

    @Autowired
    private BookStoreRepository bookStoreRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Order placeOrder(UUID cartID, CustomerDto customerDto){
        Order order =new Order();
        Cart cart = cartRepository.findById(cartID).orElseThrow();
        List<Cart> cartList =null;
        cartList.add(cart);

        order.setCartItems(cartList);
        order.setOrderDate(LocalDate.now());
        order.setFullName(customerDto.getFullName());
        order.setPhoneNumber(customerDto.getPhoneNumber());
        order.setCity(customerDto.getCity());
        order.setState(customerDto.getState());
        order.setType(customerDto.getType());
        order.setPinCode(customerDto.getPinCode());

        orderRepository.save(order);
        resetCart(cartID);


        return order;
    }

    public void resetCart(UUID cartId){
        cartRepository.deleteById(cartId);
    }


}
