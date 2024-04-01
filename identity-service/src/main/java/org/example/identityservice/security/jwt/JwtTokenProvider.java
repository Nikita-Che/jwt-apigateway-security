package org.example.identityservice.security.jwt;


import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.identityservice.dto.AuthenticationUserDto;
import org.example.identityservice.model.RoleNameEnum;
import org.example.identityservice.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("NDQ1ZjAzNjQtMzViZi00MDRjLTljZjQtNjNjYWIyZTU5ZDYw")
    private String secret;

    @Value("3600")
    private long validityInMilliseconds;

    private final JwtUserDetailsService userDetailsService;

    public String createToken(AuthenticationUserDto user) {
        Claims claims = Jwts.claims().setSubject(user.email());
        claims.put("roles", getRoleNames(user.roleNames().stream().toList()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(6);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                log.info("IN validateToken - JWT token has expired");
                return false;
            }
            log.info("IN validateToken - JWT token valid");
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("IN validateToken  - expired or invalid JWT token");
            return false;
        }
    }

    private List<String> getRoleNames(List<RoleNameEnum> userRoles) {
        List<String> result = new ArrayList<>();
        userRoles.forEach(role -> result.add(role.name()));
        return result;
    }
}