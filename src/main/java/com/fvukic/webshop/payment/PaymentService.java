package com.fvukic.webshop.payment;

import com.fvukic.webshop.order.model.OrderRequest;
import com.fvukic.webshop.payment.model.PaymentRequest;

import java.util.List;

public interface PaymentService {


    List<Payment> getAllPayments();

    void deletePaymentRequest(Integer paymentId);

    void updatePaymentRequest(PaymentRequest paymentRequest, Integer paymentId);


}
