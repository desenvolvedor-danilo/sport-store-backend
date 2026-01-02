package com.dkmo.integrationnextjs.interfaces;

import org.springframework.http.ResponseEntity;
import com.dkmo.integrationnextjs.dto.RequestRegisterDto;


public interface EditInfoProducts{    
    public ResponseEntity<String> editInfo(RequestRegisterDto user);
    public ResponseEntity<String> editAddress(RequestRegisterDto userAddress);
}
