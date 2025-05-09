package com.rosebankcollege.Payment.System.service;

import com.rosebankcollege.Payment.System.model.Payment;
import com.rosebankcollege.Payment.System.repo.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment createPayment(Payment payment) {
        payment.setCreatedAt(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> getPaymentsByAccNo(String senderAccount) {
        return paymentRepository.findBySenderAccount(senderAccount);
    }

    public Optional<Payment> getPayment(Long id) {
        return paymentRepository.findById(id);
    }
}
