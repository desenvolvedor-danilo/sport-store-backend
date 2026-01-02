package com.dkmo.integrationnextjs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dkmo.integrationnextjs.interfaces.FileHandler;
import com.dkmo.integrationnextjs.models.Products;
import com.dkmo.integrationnextjs.repository.ProductsRepository;
import com.dkmo.integrationnextjs.utils.ProductsFilesSave;
@Service
public class ProductInsertService {
    
    @Autowired
    private ProductsRepository productsRepository;
    
    public ResponseEntity<String> insertProductTeste(Products product, MultipartFile file){
        FileHandler handlerFiles = new ProductsFilesSave();
        product.setNomeImagem(handlerFiles.saveFile(file));
        productsRepository.save(product);
        return ResponseEntity.ok().body("Salvo com sucesso");
        

    }
}
