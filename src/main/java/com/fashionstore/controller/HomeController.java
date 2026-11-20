package com.fashionstore.controller;
import com.fashionstore.implement.ProductServiceImp;
import com.fashionstore.model.Product;
import com.fashionstore.service.ProductService;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/home")
    public ModelAndView home(HttpSession session ) {
String csrfToken = (String ) session.getAttribute("csrfToken");
if (csrfToken == null) {
    csrfToken= UUID.randomUUID().toString();
    session.setAttribute("csrfToken", csrfToken);
}

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("products",productService.findAll());
        modelAndView.addObject("csrfToken",csrfToken);

        return modelAndView;
    }
    @GetMapping("/products/{id}")
    public ModelAndView findById(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("detail");
        modelAndView.addObject("product",productService.findById(id));
        return modelAndView;
    }
    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }




}
