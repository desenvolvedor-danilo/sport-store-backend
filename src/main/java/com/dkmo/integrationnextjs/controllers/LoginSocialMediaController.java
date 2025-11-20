package com.dkmo.integrationnextjs.controllers;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import com.dkmo.integrationnextjs.services.LoginSocialMediaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class LoginSocialMediaController {
    
    @Autowired
    private LoginSocialMediaService loginGoogleService;
    
    @GetMapping("/home")
    public RedirectView loginGoogle(@AuthenticationPrincipal OAuth2User user, HttpServletResponse response,Authentication authentication, @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) throws IOException, ServletException{
    return loginGoogleService.loginGoogle(user, response,authentication,authorizedClient) ;
    }
     
    
}
