package com.BackendChallenge.TechTrendEmporium.service.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductQuantity {
    private Long productId;
    private int quantity;
}