package com.rosebankcollege.Payment.System.repo;

import com.rosebankcollege.Payment.System.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccountNumber(String accountNumber);
    Optional<User> findByIdNumber(String IdNumber);
}
