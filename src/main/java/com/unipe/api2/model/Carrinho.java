package com.unipe.api2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-incremento
    private int id;
    private float valorTotal;

    @OneToOne
    private Usuario dono;

    @OneToMany
    private List<Produto> produtos;
}
