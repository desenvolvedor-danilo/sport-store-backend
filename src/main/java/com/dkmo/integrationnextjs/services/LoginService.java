package com.dkmo.integrationnextjs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
// import com.dkmo.integrationnextjs.EmailService.SenderEmail;
import com.dkmo.integrationnextjs.authenticated.UserAuthenticated;
import com.dkmo.integrationnextjs.dto.LoginDto;
import com.dkmo.integrationnextjs.dto.TokenDto;
import com.dkmo.integrationnextjs.interfaces.AuthenticationService;
import com.dkmo.integrationnextjs.models.Account;
import com.dkmo.integrationnextjs.repository.LoginsRepository;

@Service
public class LoginService implements AuthenticationService {
    @Autowired
    private LoginsRepository loginsRepository;
    @Autowired
    private UserAuthenticated auth;

    @Override
    public ResponseEntity<TokenDto> login(LoginDto body) {
        Account user = loginsRepository.findByEmail(body.email());
        if (user != null && user.isVerifiedAccount()) {
            user.setTipoAutenticacao("basic");
            loginsRepository.save(user);
            @SuppressWarnings("null")
            ResponseCookie cookie2 = ResponseCookie.from("refresh-token", auth.getScope(body).refreshToken())
                    .httpOnly(true).sameSite("Lax").path("/").build();
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie2.toString()).body(auth.getScope(body));

        }
        return ResponseEntity.badRequest().body(null);

    }

    public String typeLogin(String email) {
        Account logins = loginsRepository.findByEmail(email);
        if (logins != null) {
            return logins.getTipoAutenticacao();
        }
        return "User not found";
    }
}
