package com.example.rebuilding_blogapi.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtAuthRequest {
    private String username;
    private String password;
}
