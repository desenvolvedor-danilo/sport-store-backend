package com.dkmo.integrationnextjs.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.dkmo.integrationnextjs.models.Cart;


public interface ShoppingRepository extends JpaRepository<Cart,Long>{
    
}
