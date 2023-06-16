package com.fvukic.webshop.payment;

import com.fvukic.webshop.payment.model.PaymentRequest;
import com.fvukic.webshop.util.Helper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @ApiOperation(value = "Updates PaymentRequest in database", notes = "Enter PaymentRequest id to update PaymentRequest object in database", response = PaymentRequest.class)
    private void updatePaymentRequest(@ApiParam(value = "PaymentRequest value you pass to the database")@Valid @PathVariable Integer paymentId, @RequestBody PaymentRequest paymentRequest, BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        paymentService.updatePaymentRequest(paymentRequest,paymentId);
    }

    @DeleteMapping("/{paymentId}")
    private void deletePaymentRequest(@PathVariable Integer paymentId){
        paymentService.deletePaymentRequest(paymentId);
    }
}
