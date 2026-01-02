package com.dkmo.integrationnextjs.interfaces;

import org.springframework.http.ResponseEntity;
import com.dkmo.integrationnextjs.models.Account;
import com.dkmo.integrationnextjs.models.Register;
public interface AccountInfoProvider{
    public Register getInfoUsers(String email);
    public ResponseEntity<String> getUsers(String email);
    public boolean getVerifiedAccount(Account email);
    
}
