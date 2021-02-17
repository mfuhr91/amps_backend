package com.mutual.amps.security.jwt;

import java.security.Key;
import java.util.Date;

import com.mutual.amps.security.UsuarioPrincipal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(Authentication authentication) {
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        return Jwts.builder().setSubject(usuarioPrincipal.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000)).signWith(SECRET_KEY).compact();
    }

    public String getNombreUsuarioFromToken(String token) {
        return ((Claims) Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parse(token).getBody())
        .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parse(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("token mal formado " +e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("token no soportado " +e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("token expirado " +e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("token vacío " +e.getMessage());
        }
        return false;
    }
}
