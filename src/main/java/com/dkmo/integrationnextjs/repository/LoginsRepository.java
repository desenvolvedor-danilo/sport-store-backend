package com.dkmo.integrationnextjs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dkmo.integrationnextjs.models.Account;

public interface LoginsRepository extends JpaRepository<Account,Long>{
    public Account findByEmail(String email);
    public Account findByCode(String code);
    public Account findByUsuario(String username);
}
