package com.dkmo.integrationnextjs.interfaces;

import org.springframework.http.ResponseEntity;

import com.dkmo.integrationnextjs.models.Products;

public interface ProductQueryService {
public ResponseEntity<Products> getProduct(Long codigo);
}
