package com.infnet.service;

import com.infnet.event.EstoqueReservadoEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemEstoqueService {
    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE_ALMOXARIFADO = "exchange.almoxarifado";

    public void verificarEReservarEstoque(Long pedidoId) {
        log.info("Verificando e reservando estoque para o pedido {}", pedidoId);

        // Lógica: Se tem estoque, reserva. (Se não tivesse, publicaria um EstoqueRejeitadoEvent)

        EstoqueReservadoEvent event = new EstoqueReservadoEvent(pedidoId);
        rabbitTemplate.convertAndSend(EXCHANGE_ALMOXARIFADO, "", event);

        log.info("Estoque reservado. Evento publicado para o Pedidos.");
    }
}