package i.forget.springboot.boilerplate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping("/status")
    public String status() {
        return "Restaurant Backend is running!";
    }
}