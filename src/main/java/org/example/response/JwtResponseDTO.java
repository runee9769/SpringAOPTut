package org.example.response;

import lombok.Builder;

@Builder
public class JwtResponseDTO {
    private String accessToken;
    private String token;
}
