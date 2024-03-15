package com.BackendChallenge.TechTrendEmporium.controller;

import com.BackendChallenge.TechTrendEmporium.entity.Product;
import com.BackendChallenge.TechTrendEmporium.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getProducts(
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {

        if (category != null) {
            List<Product> productsByCategory = productService.getProductsByCategory(category);
            if (productsByCategory == null || productsByCategory.isEmpty()) {
                return new ResponseEntity<>("Category not found: " + category, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(productsByCategory, HttpStatus.OK);
            }
        } else {
            List<Product> allProducts = productService.getAllProducts(page, size);
            return new ResponseEntity<>(allProducts, HttpStatus.OK);

        }
    }





}
