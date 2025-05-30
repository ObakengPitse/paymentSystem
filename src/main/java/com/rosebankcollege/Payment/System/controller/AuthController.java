package com.rosebankcollege.Payment.System.controller;

import com.rosebankcollege.Payment.System.model.Employee;
import com.rosebankcollege.Payment.System.model.User;
import com.rosebankcollege.Payment.System.repo.EmployeeRepository;
import com.rosebankcollege.Payment.System.repo.UserRepository;
import com.rosebankcollege.Payment.System.security.JwtUtil;
import com.rosebankcollege.Payment.System.dto.AuthRequest;
import com.rosebankcollege.Payment.System.dto.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "https://regal-fenglisu-b4e3d4.netlify.app/")
public class AuthController {
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder encoder, JwtUtil jwtUtil, EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
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
            authResponse.setToken(token);
            authResponse.setFullName(user.getFullName());

            String accountNumber = user.getAccountNumber();
            int lengthToMask = accountNumber.length() - 4;
            String maskedPart = "*".repeat(lengthToMask);
            String last4Digits = accountNumber.substring(accountNumber.length() - 4);
            accountNumber = maskedPart + last4Digits;
            authResponse.setAccountNumber(accountNumber);

            String idNumber = user.getIdNumber();
            lengthToMask = idNumber.length() - 4;
            maskedPart = "*".repeat(lengthToMask);
            last4Digits = idNumber.substring(idNumber.length() - 4);
            idNumber = maskedPart + last4Digits;
            authResponse.setIdNumber(idNumber);

            return ResponseEntity.ok(authResponse);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/employee/login")
    public ResponseEntity<?> employeeLogin(@RequestBody Employee req) {
        Optional<Employee> employeeOpt = employeeRepository.findByEmailAddress(req.getEmailAddress());
        if (employeeOpt.isPresent() && encoder.matches(req.getPassword(), employeeOpt.get().getPassword())) {
            Employee employee = employeeOpt.get();
        }
        return null;
    }
}

