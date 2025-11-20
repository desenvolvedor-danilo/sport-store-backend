package com.dkmo.integrationnextjs.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.dkmo.integrationnextjs.models.Carrinho;


public interface ShoppingRepository extends JpaRepository<Carrinho,Long>{
    
}
