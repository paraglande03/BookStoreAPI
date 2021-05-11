package com.bookstore.onlinebookstore.service.implementation;

import com.bookstore.onlinebookstore.model.Cart;
import com.bookstore.onlinebookstore.model.Order;
import com.bookstore.onlinebookstore.repository.CartRepository;
import com.bookstore.onlinebookstore.repository.OrderRepository;
import com.bookstore.onlinebookstore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Random;

public class OrderService implements IOrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Order getSummary(String token) {
        Long userId = JwtGenerator.decodeJWT(token);
        Optional<Order> orders = orderRepository.findByUserId(userId);
        return orders.get();
    }

    @Override
    public Long placeOrder(String token) {
        Long userId = JwtGenerator.decodeJWT(token);
        User user = userRepository.findById(userId).orElse(null);

        Long orderId = (long) 0;
        boolean isorderIdUnique = false;
        while (!isorderIdUnique) {
            orderId = (long) (100000 + new Random().nextInt(900000));
            Optional<Order> order = orderRepository.findById(orderId);
            if (!order.isPresent()) {
                isorderIdUnique = true;
            }
        }
        Cart carts=cartRepository.findByUserIds(userId);
        List<Cart> cart = cartRepository.findByUserId(userId);
        double totalPrice = cart.stream().mapToDouble(book -> book.getSubTotal()).sum();
        Order order = new Order(orderId, userId, cart, totalPrice);
        orderRepository.save(order);
        String orderMail = mailData.getOrderMail(orderId, user.getFullName(), totalPrice, cart);
        mailData.sendMessage("Order confirmation mail",user.getEmailId(),user.getFullName(),",","\n Your order has been successfully placed.","\nDetails: \n",orderMail);
        return orderId;
    }
}
