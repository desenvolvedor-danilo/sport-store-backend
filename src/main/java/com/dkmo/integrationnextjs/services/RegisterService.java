package com.dkmo.integrationnextjs.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.dkmo.integrationnextjs.EmailService.SenderEmailMimeMessage;
import com.dkmo.integrationnextjs.dto.RequestRegisterDto;
import com.dkmo.integrationnextjs.dto.ResponseDto;
import com.dkmo.integrationnextjs.enums.Roles;
import com.dkmo.integrationnextjs.interfaces.IRegister;
import com.dkmo.integrationnextjs.models.Carrinho;
import com.dkmo.integrationnextjs.models.Logins;
import com.dkmo.integrationnextjs.models.UserRegister;
import com.dkmo.integrationnextjs.repository.LoginsRepository;
import com.dkmo.integrationnextjs.repository.RegisterRepository;
import com.dkmo.integrationnextjs.validations.ValidationCpf;

@Service
public class RegisterService
        implements IRegister {
    @Autowired
    private ValidationCpf validationCpf;
    @Autowired
    private RegisterRepository registerRepository;
    @Autowired
    private LoginsRepository loginsRepository;
    @Autowired
    private PasswordEncoder encoder;
    SenderEmailMimeMessage sender = new SenderEmailMimeMessage();
    @Override
    public ResponseEntity<ResponseDto> register(RequestRegisterDto body) {
        UserRegister user = registerRepository.findByEmail(body.email());
        if (user == null) {
            Carrinho carrinho = new Carrinho();

            Logins logins = new Logins();
            UserRegister newUser = new UserRegister();
            
            newUser.setCarrinho(carrinho);
            BeanUtils.copyProperties(body, newUser);
            newUser.setPassword(encoder.encode(body.password()));
            if (!validationCpf.validateCpf(newUser.getCpf())) {
                return ResponseEntity.status(412).build();
            }
            registerRepository.save(newUser);
            
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run(){
                    logins.setSenha(newUser.getPassword());
                    
                    logins.setRole(Roles.USER);
                    logins.setVerifiedAccount(true);
                    logins.setUsuario(newUser.getUsername());
                    logins.setEmail(newUser.getEmail());
                    logins.setName(newUser.getName());
                    logins.setUserRegister(newUser);
                    BeanUtils.copyProperties(newUser,logins);

                    logins.setCode(sender.sendEmail(body.email()));
                    loginsRepository.save(logins);
                }
            });
            thread.start();
            return ResponseEntity.ok()
                    .build();
        }
        return ResponseEntity.badRequest().build();
    }
}