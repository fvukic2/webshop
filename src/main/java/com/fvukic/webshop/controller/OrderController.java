package com.fvukic.webshop.controller;

import com.fvukic.webshop.domain.api.OrderRequest;
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
    private void saveNewOrderRequest(@RequestBody OrderRequest orderRequest){
        orderService.saveNewOrderRequestToDB(orderRequest);
    }

    @PutMapping()
    private void updateOrderRequest(@RequestBody OrderRequest orderRequest){
        orderService.updateOrderRequestInDB(orderRequest);
    }

    @DeleteMapping("/{id}")
    private void deleteOrderRequest(@PathVariable int id){
        orderService.deleteOrderRequestFromDB(id);
    }
}
