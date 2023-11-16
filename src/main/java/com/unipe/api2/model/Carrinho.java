package com.unipe.api2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "carrinho")
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-incremento
    private int id;
    private float valorTotal;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario dono;

    @OneToMany(mappedBy="carrinho")
    private List<Produto> produtos;

    public Carrinho(Usuario dono, Produto produto){
        this.dono = dono;
        produtos = new ArrayList<>();
        produtos.add(produto);
        valorTotal = produto.getValor();
    }

    public void addProduto(Produto produto){
        produtos.add(produto);
        valorTotal += produto.getValor();
    }
    public boolean removeProduto(Produto produto){
        if (!produtos.contains(produto)) return false;
        produtos.remove(produto);
        valorTotal -= produto.getValor();
        return true;
    }
}
