package org.project.microservice.tallercomunicacionesmicroservicios.infrastructure.entry_points.controller.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {

    public static final String QUEUE_NOTIF = "notifications.queue";
    public static final String EXCHANGE_NOTIF = "notifications";
    public static final String ROUTING_KEY = "notify";

    @Bean
    public Queue notificationsQueue() {
        return new Queue(QUEUE_NOTIF, true);
    }

    @Bean
    public DirectExchange notificationsExchange() {
        return new DirectExchange(EXCHANGE_NOTIF, true, false);
    }

    @Bean
    public Binding notificationsBinding(Queue notificationsQueue, DirectExchange notificationsExchange) {
        return BindingBuilder.bind(notificationsQueue).to(notificationsExchange).with(ROUTING_KEY);
    }

}

