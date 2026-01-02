package com.dkmo.integrationnextjs.interfaces;

import org.springframework.http.ResponseEntity;

public interface VerificationCodeService {
    public ResponseEntity<String> verifyCode(String code);
}
