package com.dkmo.integrationnextjs.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dkmo.integrationnextjs.enums.Roles;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "logins")
public class Account implements UserDetails{    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email; 
    private String name;
    private String usuario;
    private String senha;
    private boolean verifiedAccount;
    private String token;
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "USER_ID")
    private Register userRegister;
    private String code;
    private Roles role;
    private String tipoAutenticacao;
    private String fotoPerfil;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == Roles.ADMIN){
            return List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_USER")
            );
        }
        return
        List.of(
            new SimpleGrantedAuthority("ROLE_USER")
        );
        
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}


