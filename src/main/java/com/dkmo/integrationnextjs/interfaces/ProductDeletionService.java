package com.dkmo.integrationnextjs.interfaces;

import org.springframework.http.ResponseEntity;

public interface ProductDeletionService {
public ResponseEntity<String> deleteProduct(long codigo);
}
