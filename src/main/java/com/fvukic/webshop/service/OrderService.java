package com.fvukic.webshop.service;


import com.fvukic.webshop.domain.api.OrderRequest;
import com.fvukic.webshop.domain.entity.Order;

import java.util.List;

public interface OrderService {

    void saveNewOrderRequest(OrderRequest orderRequest);

    List<Order> getAllOrders();

    void deleteOrderRequest(Integer orderId);

    void updateOrderRequest(OrderRequest orderRequest,Integer orderId);
}
