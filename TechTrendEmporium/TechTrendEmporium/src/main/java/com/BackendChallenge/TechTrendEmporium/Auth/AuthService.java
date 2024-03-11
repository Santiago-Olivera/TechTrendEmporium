package com.BackendChallenge.TechTrendEmporium.Auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        if (user.isLogged()) {
            System.out.println("User already logged in");
            return AuthResponse.builder()
                    .token("User already logged in")
                    .email("User already logged in")
                    .username("User already logged in")
                    .build();
        } else {
            user.setLogged(true);
            userRepository.save(user);
        }
        String token = jwtService.getToken(user);
        user.setLogged(true);
        userRepository.save(user);
        return AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
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

    public AuthResponseE registerEmployee(RegisterEmployeeRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode( request.getPassword()))
                .role(Role.EMPLOYEE)
                .build();

        userRepository.save(user);

        return AuthResponseE.builder()
                .token(jwtService.getToken(user))
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    public AuthResponse logout(LogoutRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user=userRepository.findByEmail(request.getEmail()).orElseThrow(()->new RuntimeException("User not found"));
        if (user.isLogged()) {
            user.setLogged(false);
            userRepository.save(user);
            return AuthResponse.builder()
                    .token("OK")
                    .build();
        } else {
            System.out.println("User already logged out");
            return AuthResponse.builder()
                    .token("User already logged out")
                    .build();
        }
    }
}