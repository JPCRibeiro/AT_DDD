package com.infnet.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitConfig {
    public static final String EXCHANGE_ALMOXARIFADO = "exchange.almoxarifado.preparado";
    public static final String FILA_DESPACHAR_PEDIDO = "fila.transporte.despachar";

    @Bean
    public FanoutExchange exchangeAlmoxarifado() {
        return new FanoutExchange(EXCHANGE_ALMOXARIFADO);
    }

    @Bean
    public Queue filaDespacharPedido() {
        return new Queue(FILA_DESPACHAR_PEDIDO, true);
    }

    @Bean
    public Binding bindingTransporte(Queue filaDespacharPedido, FanoutExchange exchangeAlmoxarifado) {
        return BindingBuilder.bind(filaDespacharPedido).to(exchangeAlmoxarifado);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}