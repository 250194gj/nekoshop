package com.fashionstore.controller;

import com.fashionstore.model.Product;
import com.fashionstore.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class staffProductController {
    private final ProductService productService;

    public staffProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/staff/products/new")
    public String newProduct( Model model) {
    model.addAttribute("product", new Product());
    return "staff/addProduct";
    }
    @GetMapping("/staff/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.deleteById(id);
        return "redirect:/home";
    }
    @PostMapping("/staff/product/add")
    public String addStaff(Model model, Product product) {
        productService.save(product);
        return "redirect:/home";
    }

}
