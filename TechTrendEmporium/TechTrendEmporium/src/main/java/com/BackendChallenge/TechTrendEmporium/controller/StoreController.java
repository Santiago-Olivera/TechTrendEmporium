package com.BackendChallenge.TechTrendEmporium.controller;

import com.BackendChallenge.TechTrendEmporium.entity.Product;
import com.BackendChallenge.TechTrendEmporium.entity.Review;
import com.BackendChallenge.TechTrendEmporium.service.ProductService;
import com.BackendChallenge.TechTrendEmporium.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store/products")
public class StoreController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping(value = "{product_id}")
    public ResponseEntity<Product> getProductById(@PathVariable("product_id") Long productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "{product_id}/reviews/add")
    public ResponseEntity<Product> addReviewToProduct(@PathVariable("product_id") Long productId, @RequestBody String review) {
        Review newReview = reviewService.addReviewToProduct(productId, review);
        if (newReview != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
