package com.dkmo.integrationnextjs.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.models.Account;
import com.dkmo.integrationnextjs.repository.LoginsRepository;
@Service
public class GetInfoLogins {
    @Autowired
    private LoginsRepository loginsRepository;
    
    public Account getInfoLogins(String email) {
        Account logins = loginsRepository.findByEmail(email);
        return logins;  
}
}
