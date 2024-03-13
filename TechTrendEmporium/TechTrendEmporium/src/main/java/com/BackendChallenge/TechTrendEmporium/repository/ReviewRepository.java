package com.BackendChallenge.TechTrendEmporium.repository;

import com.BackendChallenge.TechTrendEmporium.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Long> {
}
