package com.dkmo.integrationnextjs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dkmo.integrationnextjs.models.CartItens;

public interface ItensCarrinhoRepository extends JpaRepository<CartItens,Long> {
    List<CartItens> findByCarrinhoId(Long id);
}
