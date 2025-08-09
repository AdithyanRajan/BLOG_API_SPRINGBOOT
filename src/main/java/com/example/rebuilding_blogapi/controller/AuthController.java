package com.example.rebuilding_blogapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rebuilding_blogapi.dto.JwtAuthRequest;
import com.example.rebuilding_blogapi.dto.JwtAuthResponse;
import com.example.rebuilding_blogapi.security.CustomUserDetailsService;
import com.example.rebuilding_blogapi.security.JwtTokenHelper;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtTokenHelper jwtTokenHelper;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody JwtAuthRequest request) {
        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        }
        catch(BadCredentialsException e){
            throw new RuntimeException("Invalid Email and Password");
        }
        UserDetails userDetails=customUserDetailsService.loadUserByUsername(request.getUsername());
        String token=jwtTokenHelper.createtoken(userDetails);
        return ResponseEntity.ok(new JwtAuthResponse(token));


    }
    
}
