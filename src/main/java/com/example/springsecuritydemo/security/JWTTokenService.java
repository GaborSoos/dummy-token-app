package com.example.springsecuritydemo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
public class JWTTokenService {

    public String generateJWTToken(PlayerDto playerDto) {
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder()
                    .claim("kingdomId", playerDto.getKingdomId())
                    .claim("kingdomName", playerDto.getKingdomName())
                    .claim("username", playerDto.getUsername())
                    .signWith(key).compact();
            return jwt;
    }
}
