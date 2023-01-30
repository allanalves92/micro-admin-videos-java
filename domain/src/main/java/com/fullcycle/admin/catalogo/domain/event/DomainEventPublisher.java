package com.fullcycle.admin.catalogo.domain.event;

@FunctionalInterface
public interface DomainEventPublisher<T extends DomainEvent> {

    void publishEvent(T event);
}
