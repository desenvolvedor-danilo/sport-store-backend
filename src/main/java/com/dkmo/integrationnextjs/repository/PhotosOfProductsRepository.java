package com.dkmo.integrationnextjs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dkmo.integrationnextjs.models.PhotosOfProducts;

public interface PhotosOfProductsRepository extends JpaRepository<PhotosOfProducts, Long> {
 List<PhotosOfProducts> findByProductsId(Long codigo);   
}
