package com.rosebankcollege.Payment.System.repo;

import com.rosebankcollege.Payment.System.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findBySenderAccount(String senderAccount);
}
