package com.dkmo.integrationnextjs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dkmo.integrationnextjs.models.ProductsImages;

public interface PhotosOfProductsRepository extends JpaRepository<ProductsImages, Long> {
 List<ProductsImages> findByProductsId(Long codigo);   
}
