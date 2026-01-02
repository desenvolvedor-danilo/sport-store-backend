package com.dkmo.integrationnextjs.filters;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.dkmo.integrationnextjs.models.Account;
import com.dkmo.integrationnextjs.repository.LoginsRepository;
import com.dkmo.integrationnextjs.services.AuthenticatedUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Component
public class UserFilter extends OncePerRequestFilter{
    
    @Autowired
    private AuthenticatedUserService authenticatedService;
    
    @Autowired
    private LoginsRepository registerRepository;
    
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
            try {
            String token = extraiTokenHeader(request);        
            if(token!=null){
            String login = authenticatedService.validateToken(token);
            Account user =registerRepository.findByEmail(login);
            var authentication = new UsernamePasswordAuthenticationToken(user,token,user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
            filterChain.doFilter(request, response);     
            } catch (Exception e) {
            SecurityContextHolder.clearContext();
            // System.out.println(e.getMessage());
            throw new BadCredentialsException("Token invalido",e.getCause());
            
            }
            
    }
    public String extraiTokenHeader(HttpServletRequest request){
        var header = request.getHeader("Authorization");
        if(header == null){
            return null;
        }
        return header.replace("Bearer","").trim();
    }
}
