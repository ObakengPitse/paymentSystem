package com.rosebankcollege.Payment.System.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HomeController {
    @RequestMapping("/")
    public String homePage() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Payment System - Home</title>\n" +
                "</head>\n" +
                "<body style=\"font-family: Arial, sans-serif; margin: 0; background-color: #f9f9f9;\">\n" +
                "\n" +
                "    <!-- Header -->\n" +
                "    <header style=\"background-color: #0d6efd; color: white; padding: 20px; text-align: center;\">\n" +
                "        <h1>Welcome to the Payment System</h1>\n" +
                "        <p>Fast, Secure, and Reliable Transactions</p>\n" +
                "    </header>\n" +
                "\n" +
                "    <!-- Navigation -->\n" +
                "    <nav style=\"background-color: #003a75; padding: 10px; text-align: center;\">\n" +
                "        <a href=\"#\" style=\"color: white; margin: 0 15px; text-decoration: none; font-weight: bold;\">Home</a>\n" +
                "        <a href=\"#about\" style=\"color: white; margin: 0 15px; text-decoration: none; font-weight: bold;\">About</a>\n" +
                "        <a href=\"/login.html\" style=\"color: white; margin: 0 15px; text-decoration: none; font-weight: bold;\">Login</a>\n" +
                "        <a href=\"/register.html\" style=\"color: white; margin: 0 15px; text-decoration: none; font-weight: bold;\">Register</a>\n" +
                "    </nav>\n" +
                "\n" +
                "    <!-- Hero Section -->\n" +
                "    <section style=\"background-color: #e9ecef; padding: 40px; text-align: center;\">\n" +
                "        <h2>Your All-in-One Payment Platform</h2>\n" +
                "        <p>Send and receive payments safely and efficiently anytime, anywhere.</p>\n" +
                "    </section>\n" +
                "\n" +
                "    <!-- About Section -->\n" +
                "    <section id=\"about\" style=\"padding: 40px; max-width: 800px; margin: auto;\">\n" +
                "        <h2>About This System</h2>\n" +
                "        <p>\n" +
                "            This Payment System is designed to help users securely manage financial transactions.\n" +
                "            Whether you're making payments, managing accounts, or viewing transaction history,\n" +
                "            our platform ensures encrypted, seamless operations.\n" +
                "        </p>\n" +
                "        <p>Features include:</p>\n" +
                "        <ul>\n" +
                "            <li>Secure user authentication</li>\n" +
                "            <li>Easy-to-use payment forms</li>\n" +
                "            <li>Real-time transaction tracking</li>\n" +
                "            <li>Role-based access controls</li>\n" +
                "        </ul>\n" +
                "    </section>\n" +
                "\n" +
                "    <!-- Footer -->\n" +
                "    <footer style=\"background-color: #0d6efd; color: white; text-align: center; padding: 20px; margin-top: 40px;\">\n" +
                "        <p>&copy; 2025 Payment System. All rights reserved.</p>\n" +
                "    </footer>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";
    }
}
