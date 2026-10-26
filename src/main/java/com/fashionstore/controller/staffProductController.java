package com.fashionstore.controller;

import com.fashionstore.model.Product;
import com.fashionstore.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

@Controller
public class staffProductController {

    private static final Set<String> ALLOWED_EXTENSIONS =
            Set.of(".jpg", ".jpeg", ".png", ".gif");

    private final ProductService productService;

    public staffProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/staff/products/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "staff/addProduct";
    }

    @GetMapping("/staff/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.deleteById(id);
        return "redirect:/home";
    }

    @PostMapping("/staff/product/add")
    public String addStaff(
            @ModelAttribute("product") Product product,
            @RequestParam("imageFile") MultipartFile imageFile
    ) throws IOException {

        if (imageFile.isEmpty()) {
            return "redirect:/staff/products/new?error=missingImage";
        }

        String originalName = imageFile.getOriginalFilename();
        String extension = "";

        if (originalName != null) {
            int dotIndex = originalName.lastIndexOf('.');
            if (dotIndex >= 0) {
                extension = originalName.substring(dotIndex)
                        .toLowerCase(Locale.ROOT);
            }
        }

        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            return "redirect:/staff/products/new?error=invalidImage";
        }

        Path uploadDir = Path.of(
                System.getProperty("user.home"),
                "nekoshop-uploads",
                "products"
        );

        Files.createDirectories(uploadDir);

        String savedFileName = UUID.randomUUID() + extension;
        Path destination = uploadDir.resolve(savedFileName);

        try (var inputStream = imageFile.getInputStream()) {
            Files.copy(inputStream, destination);
        }

        product.setImage("/uploads/" + savedFileName);
        productService.save(product);

        return "redirect:/home";
    }
}