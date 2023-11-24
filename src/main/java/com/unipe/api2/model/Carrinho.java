package com.unipe.api2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-incremento
    private int id;

    private float valorTotal;

    @OneToOne
    private Usuario dono;

    @OneToMany(mappedBy = "carrinho")
    private List<ItemCarrinho> itensCarrinho;


    public Carrinho(Usuario dono, ItemCarrinho itemCarrinho){
        this.dono = dono;
        itensCarrinho = new ArrayList<>();
        itensCarrinho.add(itemCarrinho);
        valorTotal = itemCarrinho.getProduto().getValor() * itemCarrinho.getQuantidade();
    }

    public Carrinho(Usuario dono){
        this.dono = dono;
        itensCarrinho = new ArrayList<>();
    }

    public void addItem(ItemCarrinho itemCarrinho){
        itensCarrinho.add(itemCarrinho);
        valorTotal += itemCarrinho.getProduto().getValor() * itemCarrinho.getQuantidade();
    }

    public ItemCarrinho encontraItem(Produto produto){
        int idProduto = produto.getId();
        for (ItemCarrinho item : itensCarrinho){
            if (item.getProduto().getId() == idProduto)
                return item;
        }
        return null;
    }

    public boolean removeItem(ItemCarrinho itemCarrinho){
        if (!itensCarrinho.contains(itemCarrinho)) return false;
        itensCarrinho.remove(itemCarrinho);
        valorTotal -= itemCarrinho.getProduto().getValor() * itemCarrinho.getQuantidade();
        return true;
    }
}
