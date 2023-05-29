package com.fvukic.webshop.controller;

import com.fvukic.webshop.domain.api.OrderRequest;
import com.fvukic.webshop.domain.entity.Order;
import com.fvukic.webshop.service.OrderService;
import com.fvukic.webshop.util.Helper;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
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
    private void saveNewOrderRequest(@Valid @RequestBody OrderRequest orderRequest, BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        orderService.saveNewOrderRequestToDB(orderRequest);
    }

    @PutMapping()
    private void updateOrderRequest(@Valid @RequestBody OrderRequest orderRequest,  BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        orderService.updateOrderRequestInDB(orderRequest);
    }

    @DeleteMapping("/{id}")
    private void deleteOrderRequest(@PathVariable int id){
        orderService.deleteOrderRequestFromDB(id);
    }
}
