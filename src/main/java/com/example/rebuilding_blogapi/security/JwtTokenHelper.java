package com.example.rebuilding_blogapi.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtTokenHelper {

    private final String secretkey="Adithyan123456789012345678901234567890123456789012345678901234567890";
    private final long expiration_duration=5 * 60 * 60 * 1000; 

    Key key = Keys.hmacShaKeyFor(secretkey.getBytes(StandardCharsets.UTF_8));

    public String createtoken(UserDetails userdetails){
        return Jwts.builder()
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis()+expiration_duration))
        .setSubject(userdetails.getUsername())
        .signWith(key, SignatureAlgorithm.HS512)
        .compact();
    }
    private Claims getclaimsfromtoken(String token){ //getdata from the token
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();   
    }

    public String getusername(String token){
        return getclaimsfromtoken(token).getSubject();
    }

    public boolean istokenexpired(String token){
        Date expirydate=getclaimsfromtoken(token).getExpiration();
        return expirydate.before(new Date());
    }

    public boolean isvalidtoken(String token,UserDetails userdetails){
        String username=getclaimsfromtoken(token).getSubject();
        return username.equals(userdetails.getUsername())&&!istokenexpired(token);
    }




}
