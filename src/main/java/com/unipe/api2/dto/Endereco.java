package com.unipe.api2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    private String cep;
    private String address_type;
    private String address_name;
    private String address;
    private String state;
    private String district;
    private String lat;
    private String lng;
    private String city;
    private String city_ibge;
    private String ddd;
}
