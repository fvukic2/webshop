package com.fvukic.webshop.payment;

import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
import com.fvukic.webshop.payment.model.PaymentRequest;
import com.fvukic.webshop.util.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImplementation implements PaymentService {

    private PaymentRepository paymentRepository;

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImplementation.class);

    public PaymentServiceImplementation(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> getAllPayments() {
        logger.info("Fetching all payments");
        List<Payment> payments = paymentRepository.findAll();
        logger.info("Fetched {} payments", payments.size());
        return payments;
    }

    @Override
    public void deletePaymentRequest(Integer paymentId) {
        logger.info("Deleting payment with ID: {}", paymentId);
        if (!paymentRepository.existsById(paymentId)){
            logger.error("Error occurred while deleting payment: Payment with ID {} not found", paymentId);
            throw new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,paymentId);
        }
        paymentRepository.deleteById(paymentId);
        logger.info("Deleted payment with ID: {}", paymentId);
    }

    @Override
    public void updatePaymentRequest(PaymentRequest paymentRequest, Integer paymentId) {
        logger.info("Updating payment with ID: {}", paymentId);
        Payment existingPayment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> {
                    logger.error("Error occurred while updating payment: Payment with ID {} not found", paymentId);
                    return new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,paymentId);
                });
        existingPayment.setAmount(paymentRequest.getAmount());
        existingPayment.setCustomer(paymentRequest.getCustomer());
        existingPayment.setOrder(paymentRequest.getOrder());
        paymentRepository.save(existingPayment);
        logger.info("Updated payment with ID: {}", paymentId);

    }

    private Payment getPaymentRequest(PaymentRequest paymentRequest){
        logger.info("Getting payment from request");
        Payment payment = Payment.builder().amount(paymentRequest.getAmount())
                .customer(paymentRequest.getCustomer())
                .order(paymentRequest.getOrder()).build();
        logger.info("Got payment from request");
        return payment;
    }
}
