package com.BackendChallenge.TechTrendEmporium.service.Response;

import com.BackendChallenge.TechTrendEmporium.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUsersResponse {
    private Long id;
    private String username;
    private String email;
    private Role role;
}
