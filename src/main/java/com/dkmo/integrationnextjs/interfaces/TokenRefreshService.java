package com.dkmo.integrationnextjs.interfaces;

import org.springframework.http.ResponseEntity;

import com.dkmo.integrationnextjs.dto.TokenDto;

public interface TokenRefreshService {
    public ResponseEntity<TokenDto> refreshToken(String token);
}
