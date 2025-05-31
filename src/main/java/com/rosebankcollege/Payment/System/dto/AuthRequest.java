package com.rosebankcollege.Payment.System.dto;

public class AuthRequest {
    public String accountNumber;
    public String emailAddress;
    public String password;

    public String getAccountNumber() {
        return accountNumber;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public String getPassword() {
        return password;
    }
}
