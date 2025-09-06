package org.example.request;

import lombok.Builder;
import org.example.models.UserInfoDto;

@Builder
public class AuthRequestDTO {
    private UserInfoDto userInfoDto;

    public String getUsername() {
        return userInfoDto.getUsername();
    }

    public Object getPassword() {
        return userInfoDto.getPassword();
    }
}
