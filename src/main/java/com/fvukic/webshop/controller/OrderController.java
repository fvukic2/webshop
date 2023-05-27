package com.fvukic.webshop.controller;

import com.fvukic.webshop.domain.entity.Order;
import com.fvukic.webshop.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    private List<Order> getAllOrders(){
        return orderService.getAllOrdersFromDB();
    }

    @PostMapping()
    private void saveNewOrder(@RequestBody Order order){
        orderService.saveNewOrderRequestToDB(order);
    }

    @PutMapping()
    private void updateOrder(@RequestBody Order order){
        orderService.updateOrderRequestInDB(order);
    }

    @DeleteMapping("/{id}")
    private void deleteOrder(@PathVariable int id){
        orderService.deleteOrderRequestFromDB(id);
    }
}
