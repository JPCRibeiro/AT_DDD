package com.infnet.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueReservadoEvent {
    private Long pedidoId;
    private String produto;
    private int quantidade;
}
