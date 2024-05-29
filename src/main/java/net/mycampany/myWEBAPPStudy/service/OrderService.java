package net.mycampany.myWEBAPPStudy.service;

import net.mycampany.myWEBAPPStudy.Model.Cart;
import net.mycampany.myWEBAPPStudy.Model.Order;
import net.mycampany.myWEBAPPStudy.Model.OrderProduct;
import net.mycampany.myWEBAPPStudy.Model.Product;
import net.mycampany.myWEBAPPStudy.repository.OrderProductRepository;
import net.mycampany.myWEBAPPStudy.repository.OrderRepository;
import net.mycampany.myWEBAPPStudy.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;


    public List<Order> allorderlist(){
        return orderRepository.findAll();
    }

    public Order createOrderFromCart(List<Cart> cartItems) {
        // Create a new order
        Order order = new Order();
        float totalAmount = 0;

        // Save the order to generate its ID
        order = orderRepository.save(order);

        // Iterate over cart items
        for (Cart cartItem : cartItems) {
            // Retrieve product details
            Product product = productRepository.getproduct(cartItem.getProduct().getId());
            if (product == null) {
                // Handle product not found scenario
                throw new IllegalArgumentException("Product not found for ID: " + cartItem.getProduct().getId());
            }

            // Create order product
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProduct(product);
            orderProduct.setQuantity(cartItem.getQuantity());
            orderProduct.setTotalPrice(cartItem.getTotal());

            // Set the order reference in the order product
            orderProduct.setOrder(order);

            // Add the order product to the order
            order.getOrderProducts().add(orderProduct);

            // Update total amount
            totalAmount += cartItem.getTotal();
        }

        order.setOrderDate(new Date());

        // Update total amount for the order
        order.setTotal_amount(totalAmount);

        // Update and save the order
        return orderRepository.save(order);
    }

    public List<Order> display_data(){
        return orderRepository.order_pending();
    }

    public String approveOrder(Integer orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus("approved");

            orderRepository.save(order);

            return "success";
        } else {
            throw new RuntimeException("Order not found");
        }
    }

    public String denyOrder(Integer orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus("denied");
            orderRepository.save(order);
            return "Success";
        } else {
            throw new RuntimeException("Order not found");
        }
    }



}












