package com.rosebankcollege.Payment.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    @Pattern(
            regexp = "^\\d{10,20}$",
            message = "Account number must be 10 to 20 digits long"
    )
    private String senderAccount;
    @Pattern(
            regexp = "^\\d{10,20}$",
            message = "Account number must be 10 to 20 digits long"
    )
    private String receiverAccount;

    @Digits(integer = 10, fraction = 2, message = "Amount must be a valid number with up to two decimal places")
    private BigDecimal amount;
    @Pattern(
            regexp = "^[A-Z]{3}$",
            message = "Currency must be a 3-letter uppercase ISO 4217 code (e.g., ZAR, USD)"
    )
    private String currency;
    private String provider;
    @Pattern(
            regexp = "^[A-Z]{4}[A-Z]{2}[A-Z0-9]{2}([A-Z0-9]{3})?$",
            message = "Enter Valid SWIFT code e.g. FIRNZAJJ"
    )
    private String swiftCode;
    private String status;
    private LocalDateTime createdAt;

    public Payment() {
    }

    public Payment(Long id, Long userId, String senderAccount, String receiverAccount, BigDecimal amount, String currency, LocalDateTime createdAt, String provider, String swiftCode, String status) {
        this.id = id;
        this.userId = userId;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.amount = amount;
        this.currency = currency;
        this.createdAt = createdAt;
        this.provider = provider;
        this.swiftCode = swiftCode;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(String senderAccount) {
        this.senderAccount = senderAccount;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
