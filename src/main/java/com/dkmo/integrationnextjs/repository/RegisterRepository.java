package com.dkmo.integrationnextjs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dkmo.integrationnextjs.models.Register;


public interface RegisterRepository extends JpaRepository<Register,Long>{
    Register findByEmail(String user);
    Register findByUsername(String username);
}
