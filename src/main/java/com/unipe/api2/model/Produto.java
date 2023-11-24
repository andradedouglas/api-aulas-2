package com.unipe.api2.model;

import com.unipe.api2.dto.FakeProductDTO;
import com.unipe.api2.dto.ProdutoForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-incremento
    private int id;

    @Column(unique = true, nullable = false, length = 100) //tamanho 50
    private String nome;

    @Column(nullable = false)
    private float valor;

    @Column
    private String descricao;

    @Column
    private String categoria;

    @Column
    private String urlImage;



    @OneToMany(mappedBy = "produto")
    private List<ItemCarrinho> itensCarrinho;



    public Produto(ProdutoForm produtoForm){
        this.nome = produtoForm.getNome();
        this.valor = produtoForm.getValor();
    }

    public Produto(FakeProductDTO fakeProductDTO){
        this.nome = fakeProductDTO.getTitle();
        this.valor = fakeProductDTO.getPrice();
        this.descricao = fakeProductDTO.getDescription();
        this.categoria = fakeProductDTO.getCategory();
        this.urlImage = fakeProductDTO.getImage();
    }
}
