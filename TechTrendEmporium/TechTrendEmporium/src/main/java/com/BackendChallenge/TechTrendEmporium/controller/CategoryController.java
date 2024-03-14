package com.BackendChallenge.TechTrendEmporium.controller;

import com.BackendChallenge.TechTrendEmporium.entity.Category;
import com.BackendChallenge.TechTrendEmporium.entity.Product;
import com.BackendChallenge.TechTrendEmporium.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /*@GetMapping("/fetch")
    public ResponseEntity<String> saveAllCategories() {
        categoryService.fetchCategoryNames();
        return new ResponseEntity<>("Categories saved successfully", HttpStatus.OK);
    }*/
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}


