package com.unipe.api2.dto.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UsuarioForm {
    private String nomeCompleto;
    private String email;
    private String username;
    private String senha;
    private int idade;
    private String cpf;
}
