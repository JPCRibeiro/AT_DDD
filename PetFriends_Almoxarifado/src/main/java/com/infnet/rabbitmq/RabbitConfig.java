package com.infnet.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitConfig {
    public static final String EXCHANGE_PEDIDOS = "exchange.pedidos";
    public static final String EXCHANGE_PEDIDOS_CONFIRMADOS = "exchange.pedidos.confirmados";
    public static final String FILA_PREPARAR_PEDIDO = "fila.almoxarifado.preparar";

    @Bean
    public FanoutExchange exchangePedidos() {
        return new FanoutExchange(EXCHANGE_PEDIDOS);
    }

    @Bean
    public FanoutExchange exchangePedidosConfirmados() {
        return new FanoutExchange(EXCHANGE_PEDIDOS_CONFIRMADOS);
    }

    @Bean
    public Queue filaPrepararPedido() {
        return new Queue(FILA_PREPARAR_PEDIDO, true);
    }

    @Bean
    public Binding bindingAlmoxarifado(Queue filaPrepararPedido, FanoutExchange exchangePedidos) {
        return BindingBuilder.bind(filaPrepararPedido).to(exchangePedidos);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         MessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }
}