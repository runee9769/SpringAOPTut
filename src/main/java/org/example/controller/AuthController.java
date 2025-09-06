package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.entities.RefreshToken;
import org.example.models.UserInfoDto;
import org.example.response.JwtResponseDTO;
import org.example.service.JwtService;
import org.example.service.RefreshTokenService;
import org.example.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AuthController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserDetailsImpl userDetails;

    @PostMapping("auth/v1/signUp")
    public ResponseEntity signUp(@RequestBody UserInfoDto userInfoDto){
        try{
            Boolean isSignUp = userDetails.signUp(userInfoDto);
            if(Boolean.FALSE.equals(isSignUp)){
                return new ResponseEntity<>("Already exist", HttpStatus.BAD_REQUEST);
            }
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userInfoDto.getUsername());
            String jwtToken = jwtService.generateToken(userInfoDto.getUsername());
            JwtResponseDTO responseDto = JwtResponseDTO.builder()
                    .accessToken(jwtToken)
                    .token(refreshToken.getToken())
                    .build();
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Exception in User Service",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
