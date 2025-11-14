package com.dkmo.integrationnextjs.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfigurationShopping implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry ){
        registry.addMapping("/shopping/**")
        .allowedHeaders("*")
        .allowedMethods("*")
        .allowedOrigins("http://localhost:3000");
    }

}
