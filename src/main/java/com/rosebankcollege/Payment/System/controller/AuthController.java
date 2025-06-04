package com.rosebankcollege.Payment.System.controller;

import com.rosebankcollege.Payment.System.model.Employee;
import com.rosebankcollege.Payment.System.model.Payment;
import com.rosebankcollege.Payment.System.model.User;
import com.rosebankcollege.Payment.System.repo.EmployeeRepository;
import com.rosebankcollege.Payment.System.repo.PaymentRepository;
import com.rosebankcollege.Payment.System.repo.UserRepository;
import com.rosebankcollege.Payment.System.security.JwtUtil;
import com.rosebankcollege.Payment.System.dto.AuthRequest;
import com.rosebankcollege.Payment.System.dto.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:3000/" , "https://regal-fenglisu-b4e3d4.netlify.app/" , "https://thriving-babka-ca929a.netlify.app/"})
public class AuthController {
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PaymentRepository paymentRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PaymentRepository paymentRepository, PasswordEncoder encoder, JwtUtil jwtUtil, EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            user.setPassword(encoder.encode(user.getPassword()));

            Optional<User> existingAccNo = userRepository.findByAccountNumber(user.getAccountNumber());
            if (existingAccNo.isPresent()) {
                return ResponseEntity.badRequest().body("User with same Account number already exists!");
            }

            Optional<User> existingIdNumber = userRepository.findByIdNumber(user.getIdNumber());
            if (existingIdNumber.isPresent()) {
                return ResponseEntity.badRequest().body("User with same ID number already exists!");
            }

            userRepository.save(user);
            return ResponseEntity.ok("Registered Successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed due to an internal error.");
        }
    }

    @PostMapping("/customer/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        Optional<User> userOpt = userRepository.findByAccountNumber(req.accountNumber);
        if (userOpt.isPresent() && encoder.matches(req.password, userOpt.get().getPassword())) {
            User user = userOpt.get();
            String token = jwtUtil.generateToken(req.accountNumber);

            AuthResponse authResponse = new AuthResponse();
            authResponse.setUserId(user.getId());
            authResponse.setToken(token);
            authResponse.setFullName(user.getFullName());
            authResponse.setAccountNumber(maskData(user.getAccountNumber()));
            authResponse.setIdNumber(maskData(user.getIdNumber()));

            List<Payment> payments = paymentRepository.findAllByUserId(user.getId());
            for(Payment payment: payments) {
                payment.setSenderAccount(maskData(payment.getSenderAccount()));
                payment.setReceiverAccount(maskData(payment.getReceiverAccount()));
            }
            authResponse.setPayments(payments);

            return ResponseEntity.ok(authResponse);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/employee/login")
    public ResponseEntity<?> employeeLogin(@RequestBody AuthRequest req) {
        Optional<Employee> employeeOpt = employeeRepository.findByEmailAddress(req.getEmailAddress());
        if (employeeOpt.isPresent() && encoder.matches(req.getPassword(), employeeOpt.get().getPassword())) {
            Employee employee = employeeOpt.get();

            String token = jwtUtil.generateToken(req.emailAddress);
            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(token);
            authResponse.setFullName(employee.getFullName());

            List<Payment> payments = paymentRepository.findAll();
            for(Payment payment: payments) {
                payment.setSenderAccount(maskData(payment.getSenderAccount()));
                payment.setReceiverAccount(maskData(payment.getReceiverAccount()));
            }
            authResponse.setPayments(payments);
            return ResponseEntity.ok(authResponse);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    public String maskData(String accNumber) {
        int lengthToMask = accNumber.length() - 4;
        String maskedPart = "*".repeat(lengthToMask);
        String last4Digits = accNumber.substring(accNumber.length() - 4);
        accNumber = maskedPart + last4Digits;
        return accNumber;
    }
}

