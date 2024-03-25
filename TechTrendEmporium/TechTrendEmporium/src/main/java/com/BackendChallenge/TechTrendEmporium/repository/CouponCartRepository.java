package com.BackendChallenge.TechTrendEmporium.repository;

import com.BackendChallenge.TechTrendEmporium.entity.CouponCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponCartRepository extends JpaRepository<CouponCart, Long> {
    List<CouponCart> findByCartId(Long cartId);
    CouponCart findByCartIdAndCouponId(Long id, Long couponId);
}
