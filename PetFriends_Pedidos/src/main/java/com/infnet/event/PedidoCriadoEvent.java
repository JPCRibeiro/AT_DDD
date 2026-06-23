package com.infnet.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoCriadoEvent {
    private Long pedidoId;
    private String produto;
    private Integer quantidade;
}