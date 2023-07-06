package com.fvukic.webshop.order;

import com.fvukic.webshop.article.ArticleRepository;
import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
import com.fvukic.webshop.order.model.OrderRequest;
import com.fvukic.webshop.payment.Payment;
import com.fvukic.webshop.payment.PaymentRepository;
import com.fvukic.webshop.util.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImplementation implements OrderService {

    private OrderRepository orderRepository;

    private ArticleRepository articleRepository;

    private PaymentRepository paymentRepository;

    private Validator validator;

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImplementation.class);

    public OrderServiceImplementation(OrderRepository orderRepository, ArticleRepository articleRepository, PaymentRepository paymentRepository, Validator validator) {
        this.orderRepository = orderRepository;
        this.articleRepository = articleRepository;
        this.paymentRepository = paymentRepository;
        this.validator = validator;
    }

    @Override
    public void saveNewOrderRequest(OrderRequest orderRequest) {
        logger.info("Saving new order request");
        Order order = getOrderRequest(orderRequest);
        calculateTotalPrice(order);

        Payment payment = createPayment(order);
        order.setPayment(payment);

        orderRepository.save(order);
        logger.info("Saved new order: {}", order.getOrderId());

    }

    @Override
    public List<Order> getAllOrders() {
        logger.info("Fetching all orders");
        List<Order> orders = orderRepository.findAll();
        logger.info("Fetched {} orders", orders.size());
        return orders;
    }

    @Override
    public void deleteOrderRequest(Integer orderId) {
        logger.info("Deleting order with ID: {}", orderId);
        if (!orderRepository.existsById(orderId)){
            logger.error("Error occurred while deleting order: Order with ID {} not found", orderId);
            throw new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,orderId);
        }
        orderRepository.deleteById(orderId);
        logger.info("Deleted order with ID: {}", orderId);
    }

    @Override
    public void updateOrderRequest(OrderRequest orderRequest,Integer orderId) {
        logger.info("Updating order with ID: {}", orderId);
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> {
                    logger.error("Error occurred while updating order: Order with ID {} not found", orderId);
                    return new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,orderId);
                });
        existingOrder.setDescription(orderRequest.getDescription());
        existingOrder.setArticles(orderRequest.getArticles());
        existingOrder.setCustomer(orderRequest.getCustomer());
        orderRepository.save(existingOrder);
        logger.info("Updated order with ID: {}", orderId);
    }

    private void calculateTotalPrice(Order order){
        logger.info("Calculating total price for order");
        Map<Integer, Double> pricesById = new HashMap<>();
        order.getArticles().forEach(article -> pricesById.merge(article.getArticleId(), article.getArticlePrice(), Double::sum));

        double totalPrice = pricesById.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        applyDiscount(order, totalPrice);
        order.setTotalPrice(totalPrice);
        logger.info("Calculated total price for order: {}", totalPrice);
    }

    private void applyDiscount(Order order, double totalPrice) {
        if (totalPrice > 200) {
            double discountedPrice = totalPrice * 0.85;
            order.setDiscountedPrice(discountedPrice);
            order.setDiscountReason("Discount 15% - Total price over 200");
            logger.info("Applied 15% discount for order");
        } else if (totalPrice > 100) {
            double discountedPrice = totalPrice * 0.9;
            order.setDiscountedPrice(discountedPrice);
            order.setDiscountReason("Discount 10% - Total price over 100");
            logger.info("Applied 10% discount for order");
        } else {
            order.setDiscountedPrice(totalPrice);
            order.setDiscountReason("No discount for this order!");
            logger.info("No discount applied for order");
        }
    }

    private Payment createPayment(Order order) {
        logger.info("Creating payment for order");
        Payment payment = new Payment();
        payment.setAmount(order.getTotalPrice());
        payment.setCustomer(order.getCustomer());
        payment.setOrder(order);
        logger.info("Created payment for order: {}", payment.getPaymentId());
        return payment;
    }

    private Order getOrderRequest(OrderRequest orderRequest){
        logger.info("Getting order from request");
        Order order = Order.builder().description(orderRequest.getDescription())
                .articles(orderRequest.getArticles())
                .customer(orderRequest.getCustomer())
                .payment(orderRequest.getPayment())
                .build();
        logger.info("Got order from request");
        return order;
    }

}
