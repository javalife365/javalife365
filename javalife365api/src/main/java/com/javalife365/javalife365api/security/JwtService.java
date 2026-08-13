package com.javalife365.javalife365api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET =
            "12345678901234567890123456789012345678901234567890";

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String email, String firstName, String lastName, String role) {
        return Jwts.builder()
                .subject(email)
                .claim("name", this.getFirstNameAndLastName(firstName, lastName))
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 8)))
                .signWith(key)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    private String getFirstNameAndLastName(String firstName, String lastName) {
        String _firstName = String.valueOf(firstName.charAt(0)) + firstName.substring(1);
        String _lastName = String.valueOf(firstName.charAt(0)) + firstName.substring(1);
        return _firstName + " " + _lastName;
    }


}
