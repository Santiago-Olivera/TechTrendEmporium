package com.BackendChallenge.TechTrendEmporium.entity.mapper;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String username;

}
