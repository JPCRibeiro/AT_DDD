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
    public static final String EXCHANGE_PEDIDOS_CONFIRMADOS = "exchange.pedidos.confirmados";
    public static final String FILA_LOGISTICA = "fila.transporte.logistica";

    @Bean
    public FanoutExchange exchangePedidosConfirmados() {
        return new FanoutExchange(EXCHANGE_PEDIDOS_CONFIRMADOS);
    }

    @Bean
    public Queue filaLogistica() {
        return new Queue(FILA_LOGISTICA, true);
    }

    @Bean
    public Binding bindingLogistica(Queue filaLogistica, FanoutExchange exchangePedidosConfirmados) {
        return BindingBuilder.bind(filaLogistica).to(exchangePedidosConfirmados);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}