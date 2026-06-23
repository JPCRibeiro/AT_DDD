package com.infnet.service;

import com.infnet.dto.PedidoRequestDTO;
import com.infnet.dto.PedidoResponseDTO;
import com.infnet.event.PedidoCriadoEvent;
import com.infnet.model.Pedido;
import com.infnet.model.PedidoStatus;
import com.infnet.rabbitmq.RabbitConfig;
import com.infnet.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final RabbitTemplate rabbitTemplate;

    public PedidoResponseDTO criarPedido(PedidoRequestDTO request) {
        Pedido pedido = Pedido
                .builder()
                .produto(request.getProduto())
                .quantidade(request.getQuantidade())
                .status(PedidoStatus.CRIADO)
                .build();
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        log.info("Pedido criado: {}", pedidoSalvo.getId());

        PedidoCriadoEvent event = PedidoCriadoEvent
                .builder()
                .pedidoId(pedidoSalvo.getId())
                .produto(request.getProduto())
                .quantidade(request.getQuantidade())
                .build();

        log.info("Publicando evento PedidoCriado: {}", event.getPedidoId());
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_PEDIDOS, "", event);
        log.info("Aguardando novos pedidos...");
        return PedidoResponseDTO
                .builder()
                .id(pedidoSalvo.getId())
                .produto(pedidoSalvo.getProduto())
                .quantidade(pedidoSalvo.getQuantidade())
                .status(pedidoSalvo.getStatus().toString())
                .build();
    }
}
