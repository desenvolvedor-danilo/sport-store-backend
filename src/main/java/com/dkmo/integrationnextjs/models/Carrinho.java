package com.dkmo.integrationnextjs.models;
                                                                                                                                                                                                    

import java.util.ArrayList;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Data
@Entity(name = "carrinho")
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonBackReference
    @OneToOne(mappedBy = "carrinho",orphanRemoval=true)
    private UserRegister userRegister;
    
    @OneToMany(mappedBy = "carrinho", cascade =CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private  List<ItensCarrinho> itens = new ArrayList<>();
    
    public Carrinho(UserRegister userRegister){
        this.userRegister = userRegister;
    }

    public Carrinho(){}

    public void adicionarItens(Products products,int quantity){
    ItensCarrinho item = new ItensCarrinho(this, products, quantity);
    itens.add(item);
    }
    public void removeItens(Products products){
        itens.removeIf(product->product.getProducts().getId().equals(products.getId()));
    }
}
