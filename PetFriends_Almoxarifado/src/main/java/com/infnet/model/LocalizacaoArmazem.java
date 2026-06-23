package com.infnet.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalizacaoArmazem {
    private String corredor;
    private String prateleira;
    private String posicao;
}