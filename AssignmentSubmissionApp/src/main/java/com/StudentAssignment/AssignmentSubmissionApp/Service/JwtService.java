package com.StudentAssignment.AssignmentSubmissionApp.Service;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.StudentAssignment.AssignmentSubmissionApp.Model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    
    private final String SECRET_KEY = "5bc61805ce097bc78950945c8ccba6a8120afae5b47c6082ff63ec17303ecff7";


    public String exctractUsername(String token) {
        return extractClaim(token, Claims :: getSubject)
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver){
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
        .parser()
        .verifyWith(getSigninKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
    }

    public String generateToken(User user){
        String token = Jwts
        .builder()
        .subject(user.getUsername())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + 24*60*60*1000) )
        .signWith(getSigninKey())
        .compact();

        return token;
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
}


