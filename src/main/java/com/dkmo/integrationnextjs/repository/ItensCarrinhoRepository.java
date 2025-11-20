package com.dkmo.integrationnextjs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dkmo.integrationnextjs.models.ItensCarrinho;

public interface ItensCarrinhoRepository extends JpaRepository<ItensCarrinho,Long> {
    List<ItensCarrinho> findByCarrinhoId(Long id);
}
