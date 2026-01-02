package com.dkmo.integrationnextjs.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dkmo.integrationnextjs.filters.UserFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserFilter filter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        return http
        .oauth2Login(auth->auth.defaultSuccessUrl("/home").failureUrl("/error?error=true"))
        .csrf(csrf -> csrf.disable())
       // .cors(cors -> cors.disable())
        .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
        .authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.POST,"/user/register").permitAll()
        .requestMatchers(HttpMethod.POST,"/user/login").permitAll()
       // .requestMatchers("/login").permitAll()
        .requestMatchers(HttpMethod.GET,"/user/confirm").permitAll()
        .requestMatchers(HttpMethod.GET,"/user/redifine").permitAll()
        .requestMatchers(HttpMethod.GET,"/user/email").permitAll()
        .requestMatchers(HttpMethod.PUT,"/user/reset").permitAll()
        .requestMatchers(HttpMethod.POST,"/user/refresh-token").permitAll()
        .requestMatchers(HttpMethod.GET,"/photosproducts/findByProductsId").permitAll()
        .requestMatchers(HttpMethod.POST,"/photosproducts/save").permitAll()
        .requestMatchers(HttpMethod.GET,"/user/get-token").permitAll()
       // .requestMatchers(HttpMethod.GET,"/user/get-users").permitAll()
        .requestMatchers(HttpMethod.GET,"/user/userinfo").permitAll()
        .requestMatchers(HttpMethod.GET,"/user/type-login").permitAll()
        .requestMatchers(HttpMethod.GET,"/user/verify").permitAll()
        .requestMatchers(HttpMethod.POST,"/admin/insert").permitAll()
        .requestMatchers("/oauth2/**,/login/oauth2/**,/home").permitAll()
        .requestMatchers(HttpMethod.GET,"/admin/findbyid").permitAll()
        .requestMatchers(HttpMethod.GET,"/admin/findall").permitAll()
        .requestMatchers(HttpMethod.GET,"/admin/find-by-categoria").permitAll()
        .requestMatchers(HttpMethod.POST,"/admin/register").permitAll()
        .requestMatchers(HttpMethod.POST,"/admin/login").permitAll()
        .requestMatchers(HttpMethod.GET,"/admin/carrinho").permitAll()
        .requestMatchers(HttpMethod.POST,"/offers/create").permitAll()
        .requestMatchers(HttpMethod.GET,"/offers/findall").permitAll()
        .requestMatchers(HttpMethod.GET,"/offers/findbycodigo").permitAll()
        .requestMatchers(HttpMethod.GET,"/admin/search").permitAll()
        .requestMatchers(HttpMethod.GET,"/admin/codigo").permitAll()
        .requestMatchers(HttpMethod.POST,"/admin/slide-img").permitAll()
        .requestMatchers(HttpMethod.POST,"/shopping/add-to-cart").permitAll()
        .requestMatchers(HttpMethod.DELETE,"/shopping/remove").permitAll()
        .requestMatchers(HttpMethod.PUT,"/shopping/edit").permitAll()
        .requestMatchers(HttpMethod.GET,"/shopping/findall").permitAll()
        .requestMatchers(HttpMethod.POST,"/admin/slide-info").permitAll()
        .requestMatchers(HttpMethod.GET, "/admin/list-slides").permitAll()
        .requestMatchers(HttpMethod.PUT,"/admin/edit-slides").permitAll()
        .requestMatchers(HttpMethod.PUT, "/admin/edit-product").permitAll()
        .requestMatchers(HttpMethod.DELETE, "/admin/delete-product").permitAll()
        .requestMatchers(HttpMethod.GET, "/login/oauth2/code/google").permitAll()
        //.requestMatchers(HttpMethod.GET,"/user/get-picture-profile").permitAll()
        .requestMatchers(HttpMethod.GET,"/user/validate-token").permitAll()
        .requestMatchers(HttpMethod.GET,"/user/get-type-authentication").permitAll()
        .requestMatchers(HttpMethod.POST,"/user/edit-picture-profile").permitAll()
        .requestMatchers(HttpMethod.POST,"/photo/save-picture").permitAll()
        .requestMatchers("/files/products/**").permitAll()
        .requestMatchers("/files/slides/**").permitAll()
        .requestMatchers("/files/profiles/**").permitAll()
        .requestMatchers(HttpMethod.GET,"/user/info-login").permitAll()
        .requestMatchers(HttpMethod.GET,"/getTestes").permitAll()
        .requestMatchers(HttpMethod.POST,"/testes").permitAll()
        .anyRequest()
        .authenticated())
        .exceptionHandling(ex -> ex.authenticationEntryPoint((req,res,authEx)->{
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.setContentType("application/json");
        }))
        
        .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticate)throws Exception{
        return authenticate.getAuthenticationManager();
    }
    // @Bean
    // public CorsConfigurationSource corsConfigurationSource() {
    //     CorsConfiguration configuration = new CorsConfiguration();
    //     configuration.setAllowedOrigins(List.of("http://localhost:3000"));
    //     configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    //     configuration.setAllowedHeaders(List.of("*"));
    //     configuration.setAllowCredentials(true);

    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     source.registerCorsConfiguration("/**", configuration);
    //     return source;
    // }
}
