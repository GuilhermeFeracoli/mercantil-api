
package com.guilherme.mercantilapi.controller;

import com.guilherme.mercantilapi.dto.LoginRequest;
import com.guilherme.mercantilapi.dto.LoginResponse;
import com.guilherme.mercantilapi.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public AuthController(
            AuthenticationManager authenticationManager,
            JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        var authentication = new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password());
        authenticationManager.authenticate(authentication);
        String token = jwtService.generateToken(request.username());
        return new LoginResponse(token);
    }
}