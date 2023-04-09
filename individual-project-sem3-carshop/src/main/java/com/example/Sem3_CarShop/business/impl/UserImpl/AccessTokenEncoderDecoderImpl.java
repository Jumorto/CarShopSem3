package com.example.Sem3_CarShop.business.impl.UserImpl;

import com.example.Sem3_CarShop.business.UserUseCases.AccessTokenDecoder;
import com.example.Sem3_CarShop.business.UserUseCases.AccessTokenEncoder;
import com.example.Sem3_CarShop.business.exceptions.InvalidAccessTokenException;
import com.example.Sem3_CarShop.domain.UserDomain.AccessToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccessTokenEncoderDecoderImpl implements AccessTokenEncoder, AccessTokenDecoder {
    private final Key key;

    public AccessTokenEncoderDecoderImpl(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String encode(AccessToken accessToken) {
        Map<String, Object> claimsMap = new HashMap<>();

        if (accessToken.getEmail() != null) {
            claimsMap.put("email", accessToken.getEmail());
        }

        if (accessToken.getUserId() != null) {
            claimsMap.put("userId", accessToken.getUserId());
        }

        if (accessToken.getUsername() != null) {
            claimsMap.put("username", accessToken.getUsername());
        }

        if (accessToken.getDescription() != null) {
            claimsMap.put("description", accessToken.getDescription());
        }

        if (accessToken.getPhone() != null) {
            claimsMap.put("phone", accessToken.getPhone());
        }

//        if (accessToken.getPhoto() != null) {
//            claimsMap.put("photo", accessToken.getPhoto());
//        }

        if(accessToken.getRole() != null){
            claimsMap.put("role", accessToken.getRole());
        }

        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject("subject")
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .addClaims(claimsMap)
                .signWith(key)
                .compact();
    }

    @Override
    public AccessToken decode(String accessTokenEncoded) {
        try {
            Jwt jwt = Jwts.parserBuilder().setSigningKey(key).build().parse(accessTokenEncoded);
            Claims claims = (Claims) jwt.getBody();

            List<String> role = claims.get("role", List.class);

            return AccessToken.builder()
                    .subject("subject")
                    .userId(claims.get("userId", Long.class))
                    .email(claims.get("email", String.class))
                    .username(claims.get("username", String.class))
                    .description(claims.get("description", String.class))
                    .phone(claims.get("phone",String.class))
                    //.photo(claims.get("photo", String.class))//тва става стринг
                    .role(role)
                    .build();
        } catch (JwtException e) {
            throw new InvalidAccessTokenException(e.getMessage());
        }
    }
}
