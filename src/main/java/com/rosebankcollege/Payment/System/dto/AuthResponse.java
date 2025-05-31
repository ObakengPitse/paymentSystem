package com.rosebankcollege.Payment.System.dto;

import com.rosebankcollege.Payment.System.model.Payment;

import java.util.List;

public class AuthResponse {
    private Long userId;
    private String token;
    private String fullName;
    private String accountNumber;
    private String idNumber;

    List<Payment> payments;

    public AuthResponse() {

    }

    public AuthResponse(Long userId, String token, String fullName, String accountNumber, String idNumber, List<Payment> payments) {
        this.userId = userId;
        this.token = token;
        this.fullName = fullName;
        this.accountNumber = accountNumber;
        this.idNumber = idNumber;
        this.payments = payments;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
