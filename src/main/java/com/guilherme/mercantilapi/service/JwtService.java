
package com.guilherme.mercantilapi.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {
    private final SecretKey secretKey;
    private final long expirationMillis;
    public JwtService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration-ms:900000}") long expirationMillis) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        this.expirationMillis = expirationMillis;
    }
    public String generateToken(String username) {
        Instant now = Instant.now();
        Instant expiration = now.plusMillis(expirationMillis);
        return Jwts.builder()
                .subject(username)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiration))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }
    public Claims validateToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}