package com.bookstore.onlinebookstore.service.implementation;

import com.bookstore.onlinebookstore.dto.CustomerDto;
import com.bookstore.onlinebookstore.model.Cart;
import com.bookstore.onlinebookstore.model.Order;
import com.bookstore.onlinebookstore.repository.BookStoreRepository;
import com.bookstore.onlinebookstore.repository.CartRepository;
import com.bookstore.onlinebookstore.repository.OrderRepository;
import com.bookstore.onlinebookstore.service.IOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class OrderService  implements IOrderService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;
    
    
	/*
	 * @Override public Order getSummary() {
	 * 
	 * Optional<Order> orders = orderRepository.findByUserId(userId); return
	 * orders.get(); }
	 */

    public Order placeOrder(UUID cartID, CustomerDto customerDto){
        Order order =new Order();
        Cart cart = cartRepository.findById(cartID).orElseThrow();
        List<Cart> cartList = new ArrayList<Cart>();
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
