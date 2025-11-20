package com.dkmo.integrationnextjs.utils;

import org.springframework.security.oauth2.core.user.OAuth2User;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
public class AddCookieInResponse {
    
    public void setCookie(OAuth2User user,String token,String refreshToken,HttpServletResponse response){

        String[] name = PreLoadingCookies();
        String[] value = PreLoadingCookies(user, token,refreshToken);
        for(int i=0;i<name.length;i++){
            Cookie cookie = new Cookie(name[i], value[i]);
            cookie.setHttpOnly(false);
            cookie.setSecure(false);
            response.addCookie(cookie);
        }
    }
    private String[] PreLoadingCookies(){
        String[] nameStrings = { "email", "name", "token", "refreshToken" };
        return nameStrings;
    }
    private String[] PreLoadingCookies(OAuth2User user,String token,String refreshToken){
        @SuppressWarnings("null")
        String[] valueStrings = { user.getAttribute("email"),user.getAttribute("name").toString().replaceAll("\s",""), token, refreshToken};
        return valueStrings;
    }
    
}
