package com.BackendChallenge.TechTrendEmporium.service;


import com.BackendChallenge.TechTrendEmporium.entity.Review;
import com.BackendChallenge.TechTrendEmporium.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review addReviewToProduct(Long productId, String review) {
        Review newReview = new Review();
        return reviewRepository.save(newReview);
    }
}
