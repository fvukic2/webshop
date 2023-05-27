package com.fvukic.webshop.service;


import com.fvukic.webshop.domain.entity.Order;

import java.util.List;

public interface OrderService {

    void saveNewOrderRequestToDB(Order orderRequest);

    List<Order> getAllOrdersFromDB();

    void deleteOrderRequestFromDB(Integer id);

    void updateOrderRequestInDB(Order orderRequest);
}
