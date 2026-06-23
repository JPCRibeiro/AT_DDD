package com.infnet.controller;

import com.infnet.dto.PedidoRequestDTO;
import com.infnet.dto.PedidoResponseDTO;
import com.infnet.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService service;

    @PostMapping
    public PedidoResponseDTO criarPedido(@RequestBody PedidoRequestDTO request) {
        return service.criarPedido(request);
    }
}
