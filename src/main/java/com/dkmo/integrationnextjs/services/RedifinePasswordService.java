package com.dkmo.integrationnextjs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.EmailService.SenderEmail;
import com.dkmo.integrationnextjs.dto.LoginDto;
import com.dkmo.integrationnextjs.interfaces.PasswordResetService;
import com.dkmo.integrationnextjs.models.Account;
import com.dkmo.integrationnextjs.models.Register;
import com.dkmo.integrationnextjs.repository.LoginsRepository;
import com.dkmo.integrationnextjs.repository.RegisterRepository;
@Service
public class RedifinePasswordService implements PasswordResetService{
@Autowired
private RegisterRepository registerRepository;
@Autowired
private LoginsRepository loginsRepository;
@Autowired
    private SenderEmail send;
    @Override
    public String redifinePassword(String email) {
        Account user = loginsRepository.findByEmail(email);
        if (user != null) {
            String code = send.sendEmail(email);
            user.setCode(code);
            loginsRepository.save(user);
        }
        return null;
    }

    @Override
    public ResponseEntity<String> resetPassword(String email, LoginDto password) {
        Register user = registerRepository.findByEmail(email);
        if (user != null) {
            user.setPassword(password.password());
            return ResponseEntity.ok().body(user.getPassword());
        }
        return ResponseEntity.badRequest().build();

    }
    }

