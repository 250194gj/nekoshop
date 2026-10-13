package com.fashionstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam("email") String username,
                        @RequestParam("password") String password) {
        System.out.println(username);
        return "login";
    }
}
