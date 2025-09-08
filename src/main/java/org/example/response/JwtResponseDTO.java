package org.example.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtResponseDTO {
    private String accessToken;
    private String token;
}
