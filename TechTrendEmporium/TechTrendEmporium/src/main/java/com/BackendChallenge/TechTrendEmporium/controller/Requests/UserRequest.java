package com.BackendChallenge.TechTrendEmporium.controller.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Long user_id;
    private String username;
    private String password;
    private String email;
}