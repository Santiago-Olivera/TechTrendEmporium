package com.BackendChallenge.TechTrendEmporium.controller;

import com.BackendChallenge.TechTrendEmporium.controller.Requests.AddReviewRequest;
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
    public ResponseEntity<String> addReviewToProduct(@PathVariable("product_id") Long productId, @RequestBody AddReviewRequest request) {
        Review newReview = reviewService.addReviewToProduct(request.getUser(), productId, request.getComment());
        if (newReview != null) {
            return ResponseEntity.ok("Review added");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "{product_id}/reviews")
    public ResponseEntity<List<Object[]>> getReviewsForProduct(@PathVariable("product_id") Long productId) {
        List<Object[]> reviews = reviewService.getReviewsByProduct(productId);
        if (reviews != null) {
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
