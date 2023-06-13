package com.fvukic.webshop.payment;

import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
import com.fvukic.webshop.payment.model.PaymentRequest;
import com.fvukic.webshop.util.ErrorResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImplementation implements PaymentService {

    private PaymentRepository paymentRepository;

    public PaymentServiceImplementation(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public void deletePaymentRequest(Integer paymentId) {
        if (!paymentRepository.existsById(paymentId)){
            throw new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,paymentId);
        }
        paymentRepository.deleteById(paymentId);
    }

    @Override
    public void updatePaymentRequest(PaymentRequest paymentRequest, Integer paymentId) {
        Payment existingPayment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,paymentId));
        existingPayment.setAmount(paymentRequest.getAmount());
        existingPayment.setCustomer(paymentRequest.getCustomer());
        existingPayment.setOrder(paymentRequest.getOrder());
        paymentRepository.save(existingPayment);

    }

    private Payment getPaymentRequest(PaymentRequest paymentRequest){
        return Payment.builder().amount(paymentRequest.getAmount()).
                customer(paymentRequest.getCustomer()).
                order(paymentRequest.getOrder()).build();
    }
}
