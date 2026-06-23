package com.infnet.rabbitmq;

import com.infnet.event.PedidoPreparadoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PedidoPreparadoConsumer {
    @RabbitListener(queues = RabbitConfig.FILA_DESPACHAR_PEDIDO)
    public void processarDespacho(PedidoPreparadoEvent event) {
        log.info("Recebido evento para despachar transporte do pedido: {}", event.getPedidoId());
    }
}