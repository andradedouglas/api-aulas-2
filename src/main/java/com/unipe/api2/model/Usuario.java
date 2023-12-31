package com.unipe.api2.model;

import com.unipe.api2.dto.UsuarioAtualizaDadosForm;
import com.unipe.api2.dto.UsuarioForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data @AllArgsConstructor @NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-incremento
    private int id;

    @Column(nullable = false, length = 50) //tamanho 50
    private String nomeCompleto;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(unique = true, nullable = false) //coluna na tabela única e não-nula
    private String cpf;

    private int idade;

    @Column(unique = true, nullable = false) //coluna na tabela única e não-nula
    private String username;

    @Column(nullable = false)
    private String senha;

    @Column
    private String cep;

    @Column
    private String cidade;

    @Column
    private String estado;

    @Column
    private String bairro;


    public Usuario(UsuarioForm usuarioForm){
        this.nomeCompleto = usuarioForm.getNomeCompleto();
        this.email = usuarioForm.getEmail();
        this.idade = usuarioForm.getIdade();
        this.username = usuarioForm.getUsername();
        this.senha = usuarioForm.getSenha();
        this.cpf = usuarioForm.getCpf();
        this.cep = usuarioForm.getCep();
    }
    public Usuario(UsuarioAtualizaDadosForm usuarioForm){
        this.nomeCompleto = usuarioForm.getNomeCompleto();
        this.email = usuarioForm.getEmail();
        this.idade = usuarioForm.getIdade();
        this.username = usuarioForm.getUsername();
        this.cpf = usuarioForm.getCpf();
        this.cep = usuarioForm.getCep();
    }

}
