package com.infnet.rabbitmq;

import com.infnet.event.PedidoConfirmadoEvent;
import com.infnet.service.TransporteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PedidoConfirmadoConsumer {
    private final TransporteService transporteService;

    @RabbitListener(queues = "fila.transporte.logistica")
    public void processar(PedidoConfirmadoEvent event) {
        log.info("Recebido Pedido Confirmado! Iniciando preparativos de transporte para o pedido: {}", event.getPedidoId());
        transporteService.despacharEntrega(event.getPedidoId());
    }
}