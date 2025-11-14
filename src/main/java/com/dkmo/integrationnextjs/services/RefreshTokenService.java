package com.dkmo.integrationnextjs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.dto.TokenDto;
import com.dkmo.integrationnextjs.interfaces.IRefreshToken;
@Service
public class RefreshTokenService implements IRefreshToken{
    @Autowired
    private AuthenticatedUserService authenticatedService;
    @Override
    public ResponseEntity<TokenDto> refreshToken(String token) {
      
        if (authenticatedService.validateToken(token)==null) {
            return ResponseEntity.badRequest().body(new TokenDto(null,null));
        }
        
        return ResponseEntity.ok().body(authenticatedService.refreshTokenJWT(token));
    }    
}
