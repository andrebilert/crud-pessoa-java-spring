package com.andre.crudpessoa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ContatoDTO(
    @JsonProperty("_id") 
    Long id,
    String nameContato,
    String phoneContato,
    String emailContato
) {
}
