package org.example.repository;

import org.example.entities.RefreshToken;
import org.example.entities.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository <RefreshToken,Integer> {
    Optional<RefreshToken> findByToken(String token1);
//    UserInfo findByUsernameAndPassword(String username, String password); finds the table of userInfo
}
