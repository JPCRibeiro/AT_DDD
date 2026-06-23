package com.infnet.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoEntrega {
    private String logradouro;
    private String numero;
    private String cep;
    private String cidade;
    private String estado;
}