package com.BackendChallenge.TechTrendEmporium.controller.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddCouponRequest {
    private String name;
    private Integer discount;
}
