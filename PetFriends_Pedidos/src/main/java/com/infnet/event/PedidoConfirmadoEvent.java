package com.infnet.event;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoConfirmadoEvent {
    private Long pedidoId;
    @Enumerated(EnumType.STRING)
    private String status;
}
