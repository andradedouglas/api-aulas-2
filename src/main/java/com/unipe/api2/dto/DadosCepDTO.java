package com.unipe.api2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class DadosCepDTO {
    private String cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String service;
}

