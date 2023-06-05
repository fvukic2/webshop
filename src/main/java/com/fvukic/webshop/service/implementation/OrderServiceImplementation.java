package com.fvukic.webshop.service.implementation;

import com.fvukic.webshop.domain.api.OrderRequest;
import com.fvukic.webshop.domain.entity.Article;
import com.fvukic.webshop.domain.entity.Order;
import com.fvukic.webshop.domain.entity.Payment;
import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
import com.fvukic.webshop.repository.ArticleRepository;
import com.fvukic.webshop.repository.OrderRepository;
import com.fvukic.webshop.repository.PaymentRepository;
import com.fvukic.webshop.service.OrderService;
import com.fvukic.webshop.util.ErrorResponse;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplementation implements OrderService {

    private OrderRepository orderRepository;

    private ArticleRepository articleRepository;

    private PaymentRepository paymentRepository;

    private Validator validator;

    public OrderServiceImplementation(OrderRepository orderRepository, ArticleRepository articleRepository, PaymentRepository paymentRepository, Validator validator) {
        this.orderRepository = orderRepository;
        this.articleRepository = articleRepository;
        this.paymentRepository = paymentRepository;
        this.validator = validator;
    }

    @Override
    public void saveNewOrderRequest(OrderRequest orderRequest) {
        Order order = getOrderRequest(orderRequest);
        calculateTotalPrice(order);

        Payment payment = createPayment(order);
        order.setPayment(payment);

        orderRepository.save(order);

    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrderRequest(Integer orderId) {
        if (orderRepository.existsById(orderId)){
            throw new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,orderId);
        }
        orderRepository.deleteById(orderId);
    }

    @Override
    public void updateOrderRequest(OrderRequest orderRequest,Integer orderId) {
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

        if (totalPrice > 200) {
            double discountedPrice = totalPrice * 0.85; // 15% popusta
            order.setDiscountedPrice(discountedPrice);
            order.setDiscountReason("Discount 15% - Total price over 200");
        } else if (totalPrice > 100) {
            double discountedPrice = totalPrice * 0.9; // 10% popusta
            order.setDiscountedPrice(discountedPrice);
            order.setDiscountReason("Discount 10% - Total price over 100");
        } else {
            order.setDiscountedPrice(totalPrice);
            order.setDiscountReason("No discount for this order!");
        }

        order.setTotalPrice(totalPrice);
    }

    private Payment createPayment(Order order) {
        Payment payment = new Payment();
        payment.setAmount(order.getTotalPrice());
        payment.setCustomer(order.getCustomer());
        payment.setOrder(order);

        return payment;
    }

    private Order getOrderRequest(OrderRequest orderRequest){
        return Order.builder().description(orderRequest.getDescription()).
                articles(orderRequest.getArticles())
                .customer(orderRequest.getCustomer())
                .payment(orderRequest.getPayment())
                .build();
    }

}
