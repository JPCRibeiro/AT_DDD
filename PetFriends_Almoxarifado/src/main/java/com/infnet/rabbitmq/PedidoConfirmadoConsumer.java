package com.infnet.rabbitmq;

import com.infnet.event.PedidoConfirmadoEvent;
import com.infnet.event.PedidoFechadoEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PedidoConfirmadoConsumer {
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitConfig.FILA_PREPARAR_PEDIDO)
    public void processarSeparacao(PedidoFechadoEvent event) {
        log.info("Recebido evento para separar pedido no almoxarifado: {}", event.getPedidoId());

        PedidoConfirmadoEvent confirmado = PedidoConfirmadoEvent
                .builder()
                .pedidoId(event.getPedidoId())
                .build();

        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_PEDIDOS_CONFIRMADOS, "", confirmado);
        log.info("Pedido confirmado e enviado para transporte: {}", event.getPedidoId());
    }
}