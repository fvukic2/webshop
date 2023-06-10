package com.fvukic.webshop.order;


import com.fvukic.webshop.order.model.OrderRequest;

import java.util.List;

public interface OrderService {

    void saveNewOrderRequest(OrderRequest orderRequest);

    List<Order> getAllOrders();

    void deleteOrderRequest(Integer orderId);

    void updateOrderRequest(OrderRequest orderRequest,Integer orderId);
}
