package com.rosebankcollege.Payment.System.controller;

import com.rosebankcollege.Payment.System.model.User;
import com.rosebankcollege.Payment.System.repo.UserRepository;
import com.rosebankcollege.Payment.System.security.JwtUtil;
import com.rosebankcollege.Payment.System.dto.AuthRequest;
import com.rosebankcollege.Payment.System.dto.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder encoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        Optional<User> existingAccNo = userRepository.findByAccountNumber(user.getAccountNumber());
        Optional<User> existingIdNumber = userRepository.findByIdNumber(user.getIdNumber());

        if (existingAccNo.isPresent()) {
            return ResponseEntity.ok("User with Acc number already exists!");
        }

        if (existingIdNumber.isPresent()) {
            return ResponseEntity.ok("User with Id number already exists!");
        }
        userRepository.save(user);
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        Optional<User> userOpt = userRepository.findByAccountNumber(req.accountNumber);
        if (userOpt.isPresent() && encoder.matches(req.password, userOpt.get().getPassword())) {
            String token = jwtUtil.generateToken(req.accountNumber);
            return ResponseEntity.ok(new AuthResponse(token));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
