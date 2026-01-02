// package com.dkmo.integrationnextjs.services;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.stereotype.Service;

// import com.dkmo.integrationnextjs.dto.LoginDto;
// import com.dkmo.integrationnextjs.dto.TokenDto;
// import com.dkmo.integrationnextjs.interfaces.ILogin;
// import com.dkmo.integrationnextjs.models.Logins;
// import com.dkmo.integrationnextjs.repository.LoginsRepository;

// import jakarta.servlet.http.HttpServletResponse;
// @Service
// public class LoginAdminService implements ILogin {
//     @Autowired
//     private AuthenticationManager authenticationManager;
    
//     @Autowired
//     private AuthenticatedUserService authenticatedService;

//     @Autowired
//     private LoginsRepository adminRepository; 

//     @Override
//     public ResponseEntity<TokenDto> login(LoginDto body) {
//         Logins user =  adminRepository.findByEmail(body.email());
//         // boolean isVerified = getVerifiedAccount(user);
//         if(user!=null /*&& isVerified */ ){
//             var userAutenticationToken = new UsernamePasswordAuthenticationToken(body.email(),body.password()); 
//             authenticationManager.authenticate(userAutenticationToken);
//            TokenDto token = authenticatedService.getToken(body);
//            user.setToken(token.token());
//            adminRepository.save(user);
//            return ResponseEntity.ok().body(new TokenDto(token.token(), token.refreshToken()));
//         }
//         return ResponseEntity.badRequest().build();
//     }
// }
