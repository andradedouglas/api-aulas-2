package com.unipe.api2.repository;

import com.unipe.api2.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho,Integer> {

    @Query("SELECT c FROM Carrinho c WHERE c.dono.id = :idUsuario")
    Optional<Carrinho> findCarrinhoByDono(@Param("idUsuario")int idUsuario);
}
