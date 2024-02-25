package com.compassuol.sp.challenge.msuser.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public class JwtUtils {

    public static final String JWT_BEARER = "Bearer ";
    public static final String JWT_AUTHORIZATION= "Authorization";
    public static final String SECRET_KEY = "0123456789-0123456789-0123456789";
    public static final Long  EXPIRE_DAYS=0L;
    public static final Long EXPIRE_HOURS=0L;
    public static final Long EXPIRE_MINUTES=2L;


    private JwtUtils() {
    }
    private static javax.crypto.SecretKey generateKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
    private static Date toExpireDate(Date start){
        LocalDateTime dateTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime end = dateTime.plusDays(EXPIRE_DAYS).plusHours(EXPIRE_HOURS).plusMinutes(EXPIRE_MINUTES);
        return Date.from(end.atZone(ZoneId.systemDefault()).toInstant());
    }
    public static JwtToken createToken(String username){
        Date issueAt = new Date();
        Date limit  = toExpireDate(issueAt);
        String token = Jwts.builder()
                .header().add("typ","JWT")
                .and()
                .subject(username)
                .issuedAt(issueAt)
                .expiration(limit)
                .signWith(generateKey())
                .claim("username",username)
                .compact();
        return new JwtToken(token);
    }
    private static Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(generateKey())
                    .build()
                    .parseSignedClaims(refactorToken(token)).getPayload();
        } catch (JwtException ex) {
            System.out.println("Error: "+ex.getMessage());
        }

        return null;
    }

    public static String getUsernameFromToken(String token){
        return Objects.requireNonNull(getClaimsFromToken(token)).getSubject();
    }

    public static boolean isTokenValid(String token){
        try {
            Jwts.parser()
                    .verifyWith(generateKey())
                    .build()
                    .parseSignedClaims(refactorToken(token));
            return true;
        }catch (JwtException ex){
            System.out.println("Error: "+ex.getMessage());

        }
        return false;
    }

    private static String refactorToken(String token) {
        if(token.contains(JWT_BEARER)){
            return token.substring(JWT_BEARER.length());
        }
        return token;
    }
}
