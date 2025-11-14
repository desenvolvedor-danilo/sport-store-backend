package com.dkmo.integrationnextjs.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.models.Logins;
import com.dkmo.integrationnextjs.repository.LoginsRepository;
@Service
public class GetInfoLogins {
    @Autowired
    private LoginsRepository loginsRepository;
    
    public Logins getInfoLogins(String email) {
        Logins logins = loginsRepository.findByEmail(email);
        return logins;  
}
}
