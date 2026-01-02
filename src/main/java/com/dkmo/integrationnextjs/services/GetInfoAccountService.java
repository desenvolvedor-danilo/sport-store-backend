package com.dkmo.integrationnextjs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.interfaces.AccountInfoProvider;
import com.dkmo.integrationnextjs.models.Account;
import com.dkmo.integrationnextjs.models.Register;
import com.dkmo.integrationnextjs.repository.LoginsRepository;
import com.dkmo.integrationnextjs.repository.RegisterRepository;

@Service
public class GetInfoAccountService implements AccountInfoProvider {
    @Autowired
    private RegisterRepository registerRepository;
    @Autowired
    private LoginsRepository loginsRepository;

    @Override
    @Cacheable("user info")
    public Register getInfoUsers(String email) {
        Register user = registerRepository.findByEmail(email);
        return user;
    }

    @Override
     @Cacheable("usernames")
    public ResponseEntity<String> getUsers(String email) {
        Account user = loginsRepository.findByEmail(email);
        if (user != null) {
            return ResponseEntity.ok().body(user.getUsuario());
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @Cacheable("verified accounts")
    public boolean getVerifiedAccount(Account email) {
        Account user = loginsRepository.findByEmail(email.getEmail());
        if (user != null) {

            if (user.isVerifiedAccount()) {
                return true;
            }
            return false;
        }
        return false;

    }
}
