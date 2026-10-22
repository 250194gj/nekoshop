package com.fashionstore.controller;

import com.fashionstore.model.User;
import com.fashionstore.repository.UserRepository;
import com.fashionstore.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Controller
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam("email") String username,
                        @RequestParam("password") String password,
                        HttpSession session, Model model)  {
        Optional<User> optionalUser = userService.findByEmail(username);
        if (optionalUser.isEmpty()){
            model.addAttribute("error", "ユーザー存在しません。");
            return "login";
        }
        User user = optionalUser.get();
        if (!user.getPassword().equals(password)){
            model.addAttribute("error","パスワードが正しくない")   ;
            return "login";
        }
        session.setAttribute("loginUser",user);
        session.setAttribute("role",user.getRole());
        switch (user.getRole()){
            case ADMIN:
                return "redirect:/admin";
            case STAFF:
                return "redirect:/staff";
            case USER:
            default:
                return "redirect:/home";

        }
    }
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
