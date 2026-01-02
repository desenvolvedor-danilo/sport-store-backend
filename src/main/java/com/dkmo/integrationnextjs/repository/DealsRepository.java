package com.dkmo.integrationnextjs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dkmo.integrationnextjs.models.Offers;

public interface DealsRepository extends JpaRepository<Offers,Long>{
public Offers findByCodigo(Long codigo);
}
