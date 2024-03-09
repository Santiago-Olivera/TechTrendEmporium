package com.BackendChallenge.TechTrendEmporium.Auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "auth")
    public ResponseEntity<AuthResponse> registerShopper(@RequestBody RegisterShopperRequest request)
    {
        return ResponseEntity.ok(authService.registerShopper(request));
    }

    @PostMapping(value = "admin/auth")
    public ResponseEntity<AuthResponseE> registerEmployee(@RequestBody RegisterEmployeeRequest request)
    {
        return ResponseEntity.ok(authService.registerEmployee(request));
    }

}
