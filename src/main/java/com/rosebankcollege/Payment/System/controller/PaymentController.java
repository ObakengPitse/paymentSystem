package com.rosebankcollege.Payment.System.controller;

import com.rosebankcollege.Payment.System.model.Payment;
import com.rosebankcollege.Payment.System.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = {"http://localhost:3000/" , "https://regal-fenglisu-b4e3d4.netlify.app/" , "https://thriving-babka-ca929a.netlify.app/"})
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPayment(@RequestBody Payment payment) {
        Payment savedPayment = paymentService.createPayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body("Payment successful, refNo: " + savedPayment.getId());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPayment(id));
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<List<Payment>> getPaymentByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(paymentService.getPaymentsByUserId(userId));
    }

    @PostMapping("/processPayment/{paymentId}")
    public ResponseEntity<Payment> processPayment(@PathVariable Long paymentId) {
        return ResponseEntity.ok(paymentService.processPayment(paymentId));
    }

}
