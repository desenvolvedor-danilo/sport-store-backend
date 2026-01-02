package com.dkmo.integrationnextjs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dkmo.integrationnextjs.models.ProductsImages;
import com.dkmo.integrationnextjs.services.PhotosOfProductsService;
@RestController
@RequestMapping("/photosproducts")
@CrossOrigin(origins = "*")
public class PhotosProductsController {
    @Autowired
    private PhotosOfProductsService photosOfProductsService;
    @PostMapping("/save")
    public ResponseEntity<String> savePhoto(@RequestParam(name = "codigo")Long codigo, @RequestParam(name = "file")MultipartFile[] file){
        return photosOfProductsService.savePhoto(codigo, file);
    }
    @GetMapping("/findByProductsId")
    public ResponseEntity<List<ProductsImages>> findByProductsId(@RequestParam(name = "codigo")String codigo){
    return photosOfProductsService.findByProductsId(codigo);
    }
}

