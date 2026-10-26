package com.fashionstore.controller;
import com.fashionstore.implement.ProductServiceImp;
import com.fashionstore.model.Product;
import com.fashionstore.service.ProductService;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/home")
    public ModelAndView home() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("products",productService.findAll());

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
