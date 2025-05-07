package com.rosebankcollege.Payment.System.dto;

public class AuthResponse {
    private String token;
    private String fullName;
    private String accountNumber;
    private String idNumber;

    public AuthResponse() {

    }

    public AuthResponse(String token, String fullName, String accountNumber, String idNumber) {
        this.token = token;
        this.fullName = fullName;
        this.accountNumber = accountNumber;
        this.idNumber = idNumber;
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
}
