package com.rosebankcollege.Payment.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z ]+$",  message = "Name must contain only letters")
    private String fullName;
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "Invalid email address format"
    )
    private String emailAddress;
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must be at least 8 characters long and include uppercase, lowercase, digit, and special character"
    )
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "Invalid email address format"
    ) String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(@Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "Invalid email address format"
    ) String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must be at least 8 characters long and include uppercase, lowercase, digit, and special character"
    ) String getPassword() {
        return password;
    }

    public void setPassword(@Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must be at least 8 characters long and include uppercase, lowercase, digit, and special character"
    ) String password) {
        this.password = password;
    }

    public @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only letters") String getFullName() {
        return fullName;
    }

    public void setFullName(@Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only letters") String fullName) {
        this.fullName = fullName;
    }
}
