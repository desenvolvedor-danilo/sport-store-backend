package com.dkmo.integrationnextjs.services;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;
import com.dkmo.integrationnextjs.GenerationTokenJWT.GenerationJWTForMediaSocial;
import com.dkmo.integrationnextjs.enums.Roles;
import com.dkmo.integrationnextjs.models.Logins;
import com.dkmo.integrationnextjs.repository.LoginsRepository;
import com.dkmo.integrationnextjs.utils.AddCookieInResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class LoginSocialMediaService {   
    
    @Autowired
    private GenerationJWTForMediaSocial generationJWT;
    
    @Autowired
    private LoginsRepository loginsRepository;


    public RedirectView loginGoogle(OAuth2User user, HttpServletResponse response, Authentication authentication, OAuth2AuthorizedClient authorizedClient)
            throws IOException, ServletException {
        AddCookieInResponse cookies = new AddCookieInResponse();
        OAuth2AuthenticationToken typeAuth = (OAuth2AuthenticationToken) authentication;
        Logins logins = loginsRepository.findByEmail(user.getAttribute("email"));
        if (logins != null) { 
            logins.setEmail(user.getAttribute("email"));
            logins.setUsuario(user.getAttribute("name"));
            logins.setRole(Roles.USER);
            logins.setVerifiedAccount(true);
            logins.setTipoAutenticacao(typeAuth.getAuthorizedClientRegistrationId());
            if(logins.getTipoAutenticacao().equals("facebook")){   
            logins.setFotoPerfil(JsonUrl.urlFoto(authorizedClient));
            }else{
            logins.setFotoPerfil(user.getAttribute("picture"));
            }
            loginsRepository.save(logins);
        } else {
            Logins login = new Logins();
            login.setEmail(user.getAttribute("email"));
            login.setUsuario(user.getAttribute("name"));
            login.setRole(Roles.USER);
            login.setVerifiedAccount(true);
            login.setTipoAutenticacao(typeAuth.getAuthorizedClientRegistrationId());
            if(login.getTipoAutenticacao().equals("facebook")){   
            login.setFotoPerfil(JsonUrl.urlFoto(authorizedClient));
            }else{
            login.setFotoPerfil(user.getAttribute("picture"));
            }
            loginsRepository.save(login);
        }
        cookies.setCookie(user, generationJWT.setToken(user, 1),generationJWT.setToken(user, 72), response);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:3000/callback");
        return redirectView;
    }
}