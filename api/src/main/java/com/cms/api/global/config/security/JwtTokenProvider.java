package com.cms.api.global.config.security;

import com.cms.api.domain.auth.domain.AuthDetails;
import com.cms.api.domain.auth.service.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${auth.jwt.secret}")
    private String secretKey;

    @Value("${auth.jwt.access.exp}")
    private int accessTokenExpiration;

    @Value("${auth.jwt.refresh.exp}")
    private int refreshTokenExpiration;

    @Value("${auth.jwt.http.header}")
    private String header;

    private final AuthDetailsService authDetailsService;

    @PostConstruct
    private void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String generateAccessToken(String studentNo) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .setSubject(studentNo)
                .claim("type", "access_token")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String generateRefreshToken(String studentNo) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .setSubject(studentNo)
                .claim("type", "refresh_token")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        AuthDetails authDetails = authDetailsService.loadUserByUsername(this.getUserStudentNo(token));
        return new UsernamePasswordAuthenticationToken(authDetails, "", authDetails.getAuthorities());
    }

    public String getUserStudentNo(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(this.header);
    }

    public boolean validateToken(String token, String type) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return claims.getBody().getExpiration().after(new Date()) && claims.getBody().get("type").equals(type);
        } catch (Exception e) {
            return false;
        }
    }

}
