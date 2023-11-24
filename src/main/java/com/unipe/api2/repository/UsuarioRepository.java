package com.unipe.api2.repository;

import com.unipe.api2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{


    Optional<Usuario> findUsuarioByCpf(String cpf);
    Optional<Usuario> findUsuarioByUsernameAndSenha(String username, String senha);
}
