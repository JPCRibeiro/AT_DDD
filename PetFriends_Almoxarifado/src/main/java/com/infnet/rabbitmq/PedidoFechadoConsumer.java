package com.infnet.rabbitmq;

import com.infnet.event.PedidoFechadoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PedidoFechadoConsumer {
    @RabbitListener(queues = RabbitConfig.FILA_PREPARAR_PEDIDO)
    public void processarSeparacao(PedidoFechadoEvent event) {
        log.info("Recebido evento para separar pedido no almoxarifado: {}", event.getPedidoId());
    }
}