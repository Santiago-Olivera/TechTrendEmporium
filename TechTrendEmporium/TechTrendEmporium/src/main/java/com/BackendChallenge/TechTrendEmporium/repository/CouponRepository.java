package com.BackendChallenge.TechTrendEmporium.repository;

import com.BackendChallenge.TechTrendEmporium.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByName(String name);
}
