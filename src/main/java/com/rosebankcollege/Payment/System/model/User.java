package com.rosebankcollege.Payment.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z ]+$",  message = "Name must contain only letters")
    private String fullName;

    @Pattern(regexp = "^[0-9]{13}$", message = "Id Number must be 13 digits long")
    private String idNumber;

    @Pattern(
            regexp = "^\\d{10,20}$",
            message = "Account number must be 10 to 20 digits long"
    )
    private String accountNumber;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must be at least 8 characters long and include uppercase, lowercase, digit, and special character"
    )
    private String password;


    public User() {
    }

    public User(Long id, String fullName, String idNumber, String accountNumber, String password) {
        this.id = id;
        this.fullName = fullName;
        this.idNumber = idNumber;
        this.accountNumber = accountNumber;
        this.password = password;
    }

    public @Pattern(regexp = "^[a-zA-Z ]+$") String getFullName() {
        return fullName;
    }

    public void setFullName(@Pattern(regexp = "^[a-zA-Z ]+$") String fullName) {
        this.fullName = fullName;
    }

    public @Pattern(regexp = "^[0-9]{13}$") String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(@Pattern(regexp = "^[0-9]{13}$") String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
