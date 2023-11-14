package com.unipe.api2.dto.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioAtualizaDadosForm {
    private String nomeCompleto;
    private String email;
    private String username;
    private int idade;
    private String cpf;
}