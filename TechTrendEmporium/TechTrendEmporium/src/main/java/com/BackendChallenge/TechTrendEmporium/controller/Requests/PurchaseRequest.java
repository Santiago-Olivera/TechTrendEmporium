package com.BackendChallenge.TechTrendEmporium.controller.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {
    private Long user_id;
    private Long sale_id;
    private String status;
}
