package org.auth.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    public static final String SECRET = "6f488807d491c72920b8536bf63d2f5fec3e65b8c998cb147bd7ecb9cd579745";


    public Boolean validateToken(String token , UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    public String extractUsername(String token){
        return extractClaim(token , Claims::getSubject);
    }



    public Date extractExpiration(String token){
        return extractClaim(token , Claims::getExpiration);
    }



    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }



    private <T> T extractClaim(java.lang.String token , Function<Claims ,T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }



    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }



    private Key getSignKey(){
        byte [] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String createToken(Map<String ,Claims> claims , String username){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*1))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }


}
