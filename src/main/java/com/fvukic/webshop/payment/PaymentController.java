package com.fvukic.webshop.payment;

import com.fvukic.webshop.payment.model.PaymentRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    private List<Payment> getAllPayments(){
        return paymentService.getAllPayments();
    }

    @PutMapping("/{paymentId}")
    private void updatePaymentRequest(@PathVariable Integer paymentId, @RequestBody PaymentRequest paymentRequest){
        paymentService.updatePaymentRequest(paymentRequest,paymentId);
    }

    @DeleteMapping("/{paymentId}")
    private void deletePaymentRequest(@PathVariable Integer paymentId){
        paymentService.deletePaymentRequest(paymentId);
    }
}
