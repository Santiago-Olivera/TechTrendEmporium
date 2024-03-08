package com.BackendChallenge.TechTrendEmporium.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.BackendChallenge.TechTrendEmporium.entity.User;
import com.BackendChallenge.TechTrendEmporium.repository.UserRepository;
import com.BackendChallenge.TechTrendEmporium.JWT.JwtService;
import com.BackendChallenge.TechTrendEmporium.entity.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails user=userRepository.findByEmail(request.getEmail()).orElseThrow(()->new RuntimeException("User not found"));
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .email(request.getEmail())
                .username(user.getUsername())
                .build();
    }

    public AuthResponse registerShopper(RegisterShopperRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode( request.getPassword()))
                .role(Role.SHOPPER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }
}