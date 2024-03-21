package com.BackendChallenge.TechTrendEmporium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.BackendChallenge.TechTrendEmporium.entity.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
