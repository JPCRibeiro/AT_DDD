package com.infnet.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.MessageConverter;

@EnableRabbit
@Configuration
public class RabbitConfig {
    public static final String EXCHANGE_PEDIDOS = "exchange.pedidos";
    public static final String EXCHANGE_PEDIDOS_CONFIRMADOS = "exchange.pedidos.confirmados";
    public static final String FILA_ESTOQUE_RESERVADO = "fila.estoque.reservado";
    public static final String FILA_PAGAMENTO = "fila.pagamento";
    public static final String FILA_PEDIDO_PAGO = "fila.pedido.pago";
    public static final String FILA_PEDIDO_RECUSADO = "fila.pedido.recusado";

    @Bean
    public FanoutExchange exchangePedidos() {
        return new FanoutExchange(EXCHANGE_PEDIDOS);
    }

    @Bean
    public FanoutExchange exchangePedidosConfirmados() {
        return new FanoutExchange(EXCHANGE_PEDIDOS_CONFIRMADOS);
    }

    @Bean
    public Queue filaEstoqueReservado() {
        return new Queue(FILA_ESTOQUE_RESERVADO, true);
    }

    @Bean
    public Binding bindingEstoqueReservado(Queue filaEstoqueReservado) {
        return BindingBuilder.bind(filaEstoqueReservado).to(new FanoutExchange("exchange.almoxarifado"));
    }

    @Bean
    public Queue filaPagamento() {
        return new Queue(FILA_PAGAMENTO, true);
    }

    @Bean
    public Queue filaPedidoPago() {
        return new Queue(FILA_PEDIDO_PAGO, true);
    }

    @Bean
    public Queue filaPedidoRecusado() {
        return new Queue(FILA_PEDIDO_RECUSADO, true);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         MessageConverter messageConverter) {

        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }
}
