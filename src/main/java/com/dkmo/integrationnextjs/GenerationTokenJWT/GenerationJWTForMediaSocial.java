package com.dkmo.integrationnextjs.GenerationTokenJWT;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
@Service
public class GenerationJWTForMediaSocial {
    @Value("key.auth.token")
    private String key;  

    public String setToken(OAuth2User user,Integer expirationAt){
        try {
            // String token = JWT.create()
            // Algorithm algorithm = Algorithm.HMAC256(key);
            return JWT.create()
            .withIssuer("integration-nextjs")
            .withSubject(user.getAttribute("email"))
            .withExpiresAt(LocalDateTime.now().plusHours(expirationAt).toInstant(ZoneOffset.of("-03:00")))
            // .sign(algorithm);
            .sign(Algorithm.HMAC256(key));
            // return token;

        } catch (JWTCreationException e) {
            throw new RuntimeException("Error to the create token "+e.getMessage());
        }
    }
    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            return JWT.require(algorithm).withIssuer("integration-nextjs")
            .build()
            .verify(token)
            .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }

}
