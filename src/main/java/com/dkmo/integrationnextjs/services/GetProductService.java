package com.dkmo.integrationnextjs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.models.Products;
import com.dkmo.integrationnextjs.models.UserRegister;
import com.dkmo.integrationnextjs.repository.ProductsRepository;
import com.dkmo.integrationnextjs.repository.RegisterRepository;

@Service
public class GetProductService {
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private RegisterRepository registerRepository;
    
    @SuppressWarnings("null")
    public Page<Products>getProducts(Pageable pageable){
        return productsRepository.findAll(pageable);
        
    }
    @SuppressWarnings("null")
    public ResponseEntity<Products> getProductsById(Long id){
        Optional<Products> product = productsRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok().body(product.get());
        }
        return ResponseEntity.badRequest().build();
    }
    @Cacheable("products by category")
    public ResponseEntity<List<Products>> getProductsForCategory(String categoria){
        List<Products> products = productsRepository.findByCategoria(categoria);
        return ResponseEntity.ok().body(products);
    }
    public ResponseEntity<List<Products>> getProductsForKeyWord(String key){
        List<Products> products = productsRepository.findByNomeContaining(key);
        return ResponseEntity.ok().body(products);
    }
    public List<Products>getProductsByCartShop(String email){
        UserRegister user = registerRepository.findByEmail(email);
        List<Products> products = productsRepository.findByCarrinhoId(user.getCarrinho().getId());
        if(!products.isEmpty()){
            return products;
        }
        return null;
    }
}
