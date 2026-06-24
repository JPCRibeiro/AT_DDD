package com.infnet.service;

import com.infnet.model.Entrega;
import com.infnet.repository.EntregaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransporteService {
    private final EntregaRepository entregaRepository;

    public void despacharEntrega(Long pedidoId) {
        log.info("Transporte organizando o despacho do pedido {}", pedidoId);

        Entrega entrega = new Entrega();
        entrega.setIdPedido(pedidoId);
        entrega.setStatusTransporte("EM_TRANSPORTE");
        entregaRepository.save(entrega);

        log.info("Entrega do pedido {} criada e em trânsito!", pedidoId);
    }
}
