package com.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="produto")
@Getter @Setter  @NoArgsConstructor @AllArgsConstructor   
public class Produto {
    @Id //javax.persistence.

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Nome é Obrigatório")
    @Size(min=2, max=30, message = "Deve ter entre {min} e {max} caracteres")
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull(message = "Preço é Obrigatório")
    @Column(name = "preco", nullable = false)
    private double preco;

    @NotNull(message = "Quantidade é Obrigatório")
    @Min(value = 1, message = "Quantidade Minima Obrigatória: {value}")
    @Column(name = "quantidade", nullable = false)
    private int quantidade;    
}
