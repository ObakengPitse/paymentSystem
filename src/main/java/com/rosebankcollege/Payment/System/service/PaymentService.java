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
        payment.setStatus("Pending");
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        for(Payment payment: payments) {
            payment.setSenderAccount(maskAccountNo(payment.getSenderAccount()));
            payment.setReceiverAccount(maskAccountNo(payment.getReceiverAccount()));
        }
        return payments;
    }

    public List<Payment> getPaymentsByUserId(Long userId) {
        List<Payment> payments = paymentRepository.findAllByUserId(userId);
        for(Payment payment: payments) {
            payment.setSenderAccount(maskAccountNo(payment.getSenderAccount()));
            payment.setReceiverAccount(maskAccountNo(payment.getReceiverAccount()));
        }
        return payments;
    }

    public Payment getPayment(Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if(payment.isPresent()) {
            Payment foundPayment = payment.get();
            foundPayment.setSenderAccount(maskAccountNo(foundPayment.getSenderAccount()));
            foundPayment.setReceiverAccount(maskAccountNo(foundPayment.getReceiverAccount()));
            return foundPayment;
        }
        return null;
    }

    public String maskAccountNo(String accNumber) {
        int lengthToMask = accNumber.length() - 4;
        String maskedPart = "*".repeat(lengthToMask);
        String last4Digits = accNumber.substring(accNumber.length() - 4);
        accNumber = maskedPart + last4Digits;
        return accNumber;
    }
}
