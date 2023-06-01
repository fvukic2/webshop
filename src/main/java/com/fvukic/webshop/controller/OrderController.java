package com.fvukic.webshop.controller;

import com.fvukic.webshop.domain.api.OrderRequest;
import com.fvukic.webshop.domain.entity.Order;
import com.fvukic.webshop.service.OrderService;
import com.fvukic.webshop.util.Helper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @ApiOperation(value = "Adds new OrderRequest to database", notes = "Enter all OrderRequest parameters to add new OrderRequest object to database", response = OrderRequest.class)
    private void saveNewOrderRequest(@ApiParam(value = "OrderRequest value you pass to the database")@Valid @RequestBody OrderRequest orderRequest, BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        orderService.saveNewOrderRequestToDB(orderRequest);
    }

    @PutMapping("/{orderId}")
    @ApiOperation(value = "Updates OrderRequest in database", notes = "Enter OrderRequest id to update OrderRequest object in database", response = OrderRequest.class)
    private void updateOrderRequest(@ApiParam(value = "OrderRequest value you pass to the database")@Valid @PathVariable Integer orderId, @RequestBody OrderRequest orderRequest, BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        orderService.updateOrderRequestInDB(orderRequest,orderId);
    }

    @DeleteMapping("/{id}")
    private void deleteOrderRequest(@PathVariable int id){
        orderService.deleteOrderRequestFromDB(id);
    }
}
