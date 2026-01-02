package com.dkmo.integrationnextjs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.models.Account;
import com.dkmo.integrationnextjs.repository.LoginsRepository;
@Service
public class GetAuthentication {
    @Autowired
    private LoginsRepository loginsRepository;
    public String getTypeAuthentication(String email){
        Account logins = loginsRepository.findByEmail(email);
        return logins.getTipoAutenticacao();
    }
}
