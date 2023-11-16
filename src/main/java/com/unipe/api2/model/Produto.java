package com.unipe.api2.model;

import com.unipe.api2.dto.form.ProdutoForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-incremento
    private int id;

    @Column(unique = true, nullable = false, length = 50) //tamanho 50
    private String nome;

    @Column(nullable = false)
    private float valor;



    public Produto(ProdutoForm produtoForm){
        this.nome = produtoForm.getNome();
        this.valor = produtoForm.getValor();
    }
}
