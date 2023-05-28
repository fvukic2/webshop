package com.fvukic.webshop.service;

import com.fvukic.webshop.domain.api.OrderRequest;
import com.fvukic.webshop.domain.entity.Order;
import com.fvukic.webshop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImplementation(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void saveNewOrderRequestToDB(OrderRequest orderRequest) {
        Order order = getOrderRequest(orderRequest);
        orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrdersFromDB() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrderRequestFromDB(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void updateOrderRequestInDB(OrderRequest orderRequest) {
        Order order = getOrderRequest(orderRequest);
        orderRepository.save(order);
    }

    private Order getOrderRequest(OrderRequest orderRequest){
        return Order.builder().description(orderRequest.getDescription()).
                totalPrice(orderRequest.getTotalPrice()).
                articles(orderRequest.getArticles()).build();
    }

}
