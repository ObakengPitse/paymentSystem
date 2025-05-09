package com.rosebankcollege.Payment.System.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HomeController {
    @RequestMapping("/")
    public String homePage() {
        return "<h1>Welcome to Payment System API</h1>";
    }
}
