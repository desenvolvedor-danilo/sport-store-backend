package com.dkmo.integrationnextjs.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_register")
public class Register{
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "USER_ID")
    private long id;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,unique = true)
    private String cpf;
    @Column(nullable = false)
    private String cep;
    @Column(nullable = false)
    private String logradouro;
    @Column(nullable = false)
    private String bairro;
    @Column(nullable = false)
    private String localidade;
    @Column(nullable = false)
    private String uf;
    @JsonManagedReference
    @OneToOne(mappedBy = "userRegister", cascade = CascadeType.ALL,orphanRemoval = true)
    private Account logins;
    private String complemento;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Cart carrinho;    
    
}

