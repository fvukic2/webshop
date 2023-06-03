package com.fvukic.webshop.service.implementation;

import com.fvukic.webshop.domain.api.OrderRequest;
import com.fvukic.webshop.domain.entity.Article;
import com.fvukic.webshop.domain.entity.Order;
import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
import com.fvukic.webshop.repository.ArticleRepository;
import com.fvukic.webshop.repository.OrderRepository;
import com.fvukic.webshop.service.OrderService;
import com.fvukic.webshop.util.ErrorResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplementation implements OrderService {

    private OrderRepository orderRepository;

    private ArticleRepository articleRepository;

    public OrderServiceImplementation(OrderRepository orderRepository, ArticleRepository articleRepository) {
        this.orderRepository = orderRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public void saveNewOrderRequestToDB(OrderRequest orderRequest) {
        Order order = getOrderRequest(orderRequest);
        calculateTotalPrice(order);
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
    public void updateOrderRequestInDB(OrderRequest orderRequest,Integer orderId) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,orderId));
        existingOrder.setDescription(orderRequest.getDescription());
        existingOrder.setArticles(orderRequest.getArticles());
        existingOrder.setCustomer(orderRequest.getCustomer());
        orderRepository.save(existingOrder);
    }

    private void calculateTotalPrice(Order order){
        List<Integer> articleIds = order.getArticles().stream()
                .map(Article::getArticleId)
                .collect(Collectors.toList());

        List<Article> articles = articleRepository.findAllById(articleIds);
        double totalPrice = articles.stream()
                .mapToDouble(Article::getArticlePrice)
                .sum();

        order.setTotalPrice(totalPrice);
    }

    private Order getOrderRequest(OrderRequest orderRequest){
        return Order.builder().description(orderRequest.getDescription()).
                articles(orderRequest.getArticles())
                .customer(orderRequest.getCustomer())
                .build();
    }

}
