package com.BackendChallenge.TechTrendEmporium.entity.mapper;

import com.BackendChallenge.TechTrendEmporium.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserDto toDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();

    }
    public User toEntity(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .build();
    }
}
