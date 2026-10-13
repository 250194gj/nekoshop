package com.fashionstore.controller;
import com.fashionstore.implement.ProductServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private final ProductServiceImp productServiceImp;
    public HomeController(ProductServiceImp productServiceImp) {
        this.productServiceImp = productServiceImp;
    }
    @GetMapping("/")
    public String home() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("products",productServiceImp.findAll());

        return "home";
    }
}
