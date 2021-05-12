package com.bookstore.onlinebookstore.service.implementation;

import com.bookstore.onlinebookstore.dto.CustomerDto;
import com.bookstore.onlinebookstore.model.Cart;
import com.bookstore.onlinebookstore.model.Customer;
import com.bookstore.onlinebookstore.model.Order;
import com.bookstore.onlinebookstore.repository.BookStoreRepository;
import com.bookstore.onlinebookstore.repository.CartRepository;
import com.bookstore.onlinebookstore.repository.CustomerRepository;
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
    
    @Autowired
    private CustomerRepository customerRepository;
	/*
	 * @Override public Order getSummary() {
	 * 
	 * Optional<Order> orders = orderRepository.findByUserId(userId); return
	 * orders.get(); }
	 */

    public Order placeOrder(UUID cartID, CustomerDto customerDto){
        Order order =new Order();
       
        order.setOrderDate(LocalDate.now());
        Cart cart = cartRepository.findById(cartID).orElseThrow();
        List<Cart> cartList = new ArrayList<Cart>();
        cartList.add(cart);
        order.setCartItems(cartList);
        
        Customer customer= new Customer(customerDto);
       
        customer.setFullName(customerDto.getFullName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setCity(customerDto.getCity());
        customer.setState(customerDto.getState());
        customer.setType(customerDto.getType());
        customer.setPinCode(customerDto.getPinCode());
        customerRepository.save(customer);
        order.setCustomer(customer);
        orderRepository.save(order);
      //  resetCart(cartID);
        return order;
    }

    public void resetCart(UUID cartId){
        cartRepository.deleteById(cartId);
    }


}
