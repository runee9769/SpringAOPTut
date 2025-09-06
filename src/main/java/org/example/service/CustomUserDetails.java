package org.example.service;

import lombok.Getter;
import org.example.entities.UserInfo;
import org.example.entities.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends UserInfo implements UserDetails {
    private String userName;
    private String password;
    @Getter
    Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UserInfo byUsername){
        this.userName = byUsername.getUsername();
        this.password = byUsername.getPassword();

        List<GrantedAuthority> auths = new ArrayList<>();
        for(UserRole role: byUsername.getRoles()){
            auths.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
        }
        this.authorities = auths;
    }

    @Override
    public String getUsername(){
        return userName;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }
}
