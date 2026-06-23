package com.infnet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequestDTO implements Serializable {
    private String produto;
    private Integer quantidade;
}