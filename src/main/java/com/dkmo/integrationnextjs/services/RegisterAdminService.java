package com.dkmo.integrationnextjs.services;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.EmailService.SenderEmail;

import com.dkmo.integrationnextjs.dto.RequestRegisterDto;
import com.dkmo.integrationnextjs.dto.ResponseDto;

import com.dkmo.integrationnextjs.enums.Roles;

import com.dkmo.integrationnextjs.interfaces.UserRegistrationService;
import com.dkmo.integrationnextjs.models.Account;
import com.dkmo.integrationnextjs.models.Register;
import com.dkmo.integrationnextjs.repository.LoginsRepository;
import com.dkmo.integrationnextjs.repository.RegisterRepository;
import com.dkmo.integrationnextjs.validations.ValidationCpf;

@Service
public class RegisterAdminService implements UserRegistrationService {
  
    @Autowired
    private SenderEmail sender;
    
    ValidationCpf validation = new ValidationCpf();
    
    @Autowired
    private RegisterRepository registerAdminRepository;
    
    @Autowired
    private LoginsRepository adminRepository; 
    
    @Autowired
    private PasswordEncoder encoder;
    
    @Override
    public ResponseEntity<ResponseDto> register(RequestRegisterDto body){
        Register userVerified = registerAdminRepository.findByEmail(body.email());
        if(userVerified==null){
        Register user = new Register();
        user.setPassword(encoder.encode(body.password()));
        user.setUsername(body.username());
        BeanUtils.copyProperties(body, user);
        if(!validation.validateCpf(user.getCpf())){
            return ResponseEntity.status(412).build();
        }
        Account userAdmin = new Account();
        Roles role = Roles.ADMIN;
        userAdmin.setUserRegister(user);
        user.setLogins(userAdmin);
        userAdmin.setRole(role);
        adminRepository.save(userAdmin);
        registerAdminRepository.save(user);
        Thread thread = new Thread(new Runnable() {
            @Override 
            public void run(){
            sender.sendEmail(user.getEmail());
            }
        });
        thread.start();
    }
        return ResponseEntity.ok().build();
    }
}
