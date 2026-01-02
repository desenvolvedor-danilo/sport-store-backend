package com.dkmo.integrationnextjs.models;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Products {

@GeneratedValue(strategy = GenerationType.AUTO)
@Id
private Long id;
@Column(nullable = false,unique = true,length = 10)
private Long codigo;
private String categoria;
private String nomeImagem;
private String nome;
private String edicao;
private double precoAntigo;
private double precoNovo;
private double desconto;
private double parcelado;
@OneToMany(fetch = FetchType.EAGER)
private List<ProductsImages> photosOfProducts;
private double valorParcela;
@Lob
@Column(length = 50000)
private String descricao;
@ManyToOne
@JsonBackReference
private Cart carrinho;
}


  