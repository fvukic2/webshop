package com.fvukic.webshop.service;


import com.fvukic.webshop.domain.api.OrderRequest;
import com.fvukic.webshop.domain.entity.Order;

import java.util.List;

public interface OrderService {

    void saveNewOrderRequestToDB(OrderRequest orderRequest);

    List<Order> getAllOrdersFromDB();

    void deleteOrderRequestFromDB(Integer id);

    void updateOrderRequestInDB(OrderRequest orderRequest,Integer orderId);
}
