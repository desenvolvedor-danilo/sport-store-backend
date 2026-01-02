package com.dkmo.integrationnextjs.models;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "itens_carrinho")
public class CartItens {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "carrinho_id")
@JsonBackReference
private Cart carrinho;

@ManyToOne
@JsonBackReference
private Products products;

private double price;
private String productName;
private String urlImage;

private int quantity;
private double totalPrice;
public CartItens(){}    
public CartItens(Cart carrinho,Products products,int quantity){
this.carrinho = carrinho;
this.products = products;
this.quantity = quantity;
this.totalPrice = products.getPrecoNovo() * quantity;
this.productName = products.getNome();
this.urlImage = products.getNomeImagem(); 
this.price = products.getPrecoNovo();   
}
}