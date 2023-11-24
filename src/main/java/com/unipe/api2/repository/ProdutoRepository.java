package com.unipe.api2.repository;

import com.unipe.api2.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

    Optional<Produto> findProdutoByNome(String nome);

}
