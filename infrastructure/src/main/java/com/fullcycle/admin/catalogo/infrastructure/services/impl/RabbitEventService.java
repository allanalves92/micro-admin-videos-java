package com.fullcycle.admin.catalogo.infrastructure.services.impl;

import com.fullcycle.admin.catalogo.infrastructure.configuration.json.Json;
import com.fullcycle.admin.catalogo.infrastructure.services.EventService;
import org.springframework.amqp.rabbit.core.RabbitOperations;


import static java.util.Objects.requireNonNull;

public class RabbitEventService implements EventService {

    private final String exchange;

    private final String routingKey;

    private final RabbitOperations ops;

    public RabbitEventService(
            final String exchange,
            final String routingKey,
            final RabbitOperations ops
    ) {
        this.exchange = requireNonNull(exchange);
        this.routingKey = requireNonNull(routingKey);
        this.ops = requireNonNull(ops);
    }

    @Override
    public void send(final Object event) {
        this.ops.convertAndSend(this.exchange, this.routingKey, Json.writeValueAsString(event));
    }
}