package com.dkmo.integrationnextjs.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.dto.TokenDto;
import com.dkmo.integrationnextjs.interfaces.TokenRefreshService;
@Service
public class RefreshTokenService implements TokenRefreshService{
    @Autowired
    private AuthenticatedUserService authenticatedService;
    @Override
    public ResponseEntity<TokenDto> refreshToken(String token) {
        if (authenticatedService.validateToken(token)==null) {
            return ResponseEntity.badRequest().body(new TokenDto(null,null));
        }
        @SuppressWarnings("null")
        ResponseCookie cookie2 = ResponseCookie.from(
            "refresh-token",authenticatedService.refreshTokenJWT(token).refreshToken())
            .httpOnly(true)
            .sameSite("Lax")
            .path("/")
            .build();
        return ResponseEntity.ok().header(org.springframework.http.HttpHeaders.SET_COOKIE,cookie2.toString()).body(authenticatedService.refreshTokenJWT(token));
    }    
}
