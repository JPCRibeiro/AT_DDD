package com.infnet.rabbitmq;

import com.infnet.event.EstoqueReservadoEvent;
import com.infnet.event.PedidoConfirmadoEvent;
import com.infnet.model.Pedido;
import com.infnet.model.PedidoStatus;
import com.infnet.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EstoqueReservadoConsumer {
    private final PedidoRepository pedidoRepository;
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "fila.estoque.reservado")
    public void processarConfirmacaoEstoque(EstoqueReservadoEvent event) {
        log.info("Recebido confirmação de estoque reservado para o pedido: {}", event.getPedidoId());

        // 1. Atualiza o status do pedido para CONFIRMADO
        Pedido pedido = pedidoRepository.findById(event.getPedidoId()).orElseThrow();
        pedido.setStatus(PedidoStatus.CONFIRMADO);
        pedidoRepository.save(pedido);

        log.info("Status do pedido {} atualizado para CONFIRMADO", pedido.getId());

        // 2. Dispara o evento de que o pedido está oficialmente confirmado
        PedidoConfirmadoEvent confirmadoEvent = PedidoConfirmadoEvent.
                builder()
                .pedidoId(event.getPedidoId())
                .status(PedidoStatus.CONFIRMADO.name())
                .build();
        rabbitTemplate.convertAndSend("exchange.pedidos.confirmados", "", confirmadoEvent);
    }
}
