package com.dkmo.integrationnextjs.models;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false,unique = false)
    private long codigo;    
    private String titulo;
    private String caminho;
    private String nome;
    private String valor;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "slides_id")
    private Slides slides;
}
