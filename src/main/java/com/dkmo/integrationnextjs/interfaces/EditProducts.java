package com.dkmo.integrationnextjs.interfaces;

import org.springframework.http.ResponseEntity;

import com.dkmo.integrationnextjs.dto.ProductDto;
import com.dkmo.integrationnextjs.models.Products;

public interface EditProducts {
    public ResponseEntity<Products> editProductService(ProductDto productDto);
}
