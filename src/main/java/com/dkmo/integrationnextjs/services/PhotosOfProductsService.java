package com.dkmo.integrationnextjs.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.dkmo.integrationnextjs.interfaces.FileHandler;
import com.dkmo.integrationnextjs.models.ProductsImages;
import com.dkmo.integrationnextjs.models.Products;
import com.dkmo.integrationnextjs.repository.PhotosOfProductsRepository;
import com.dkmo.integrationnextjs.repository.ProductsRepository;

@Service
public class PhotosOfProductsService {
    @Autowired
    private PhotosOfProductsRepository photosOfProductsRepository;
    @Autowired
    private ProductsRepository productsRepository;

    public ResponseEntity<String> savePhoto(Long codigo, MultipartFile[] file) {
        Products products = productsRepository.findByCodigo(codigo);
        if (products == null) {
            return ResponseEntity.badRequest().body("Produto não encontrado com o código: " + codigo);
        }
        for (MultipartFile multipartFile : file) {
            FileHandler handlerFiles = new FileHandler() {
                @Override
                public String saveFile(MultipartFile file) {
                    try {
                        var path = "src/main/resources/static/files/products/";
                        byte[] imagem = file.getBytes();
                        Path fullPath = Paths.get(path + file.getOriginalFilename());
                        Files.write(fullPath, imagem);
                        return "http://localhost:8080/files/products/" + file.getOriginalFilename();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        return null;
                    }

                }
            };

            String url = handlerFiles.saveFile(multipartFile);
            ProductsImages photosOfProducts = new ProductsImages();
            photosOfProducts.setUriPicture(url);
            photosOfProducts.setProducts(products);
            photosOfProductsRepository.save(photosOfProducts);

        }
        return ResponseEntity.ok("Fotos salvas com sucesso!");
    }

    @Cacheable("photos of products")
    public ResponseEntity<List<ProductsImages>> findByProductsId(String codigo) {
        long id = Long.parseLong(codigo);

        List<ProductsImages> photosOfProducts = photosOfProductsRepository.findByProductsId(id);
        if (!photosOfProducts.isEmpty()) {
            return ResponseEntity.ok(photosOfProductsRepository.findByProductsId(id));
        }

        return ResponseEntity.notFound().build();
    }
}
