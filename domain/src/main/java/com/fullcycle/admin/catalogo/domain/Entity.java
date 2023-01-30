package com.fullcycle.admin.catalogo.domain;

import com.fullcycle.admin.catalogo.domain.event.DomainEvent;
import com.fullcycle.admin.catalogo.domain.event.DomainEventPublisher;
import com.fullcycle.admin.catalogo.domain.validation.ValidationHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static java.util.Objects.hash;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

public abstract class Entity<ID extends Identifier> {

    protected final ID id;

    private final List<DomainEvent> domainEvents;

    protected Entity(final ID id, final List<DomainEvent> domainEvents) {
        requireNonNull(id, "'id' should not be null");
        this.id = id;
        this.domainEvents = new ArrayList<>(domainEvents == null ? Collections.emptyList() : domainEvents);
    }

    public abstract void validate(ValidationHandler handler);

    public ID getId() {
        return id;
    }

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    public void publishDomainEvents(final DomainEventPublisher<DomainEvent> publisher) {
        if (isNull(publisher)) {
            return;
        }

        getDomainEvents().forEach(publisher::publishEvent);

        this.domainEvents.clear();
    }

    public void registerEvent(final DomainEvent event) {
        if (event != null) {
            this.domainEvents.add(event);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Entity<?> entity = (Entity<?>) o;
        return getId().equals(entity.getId());
    }

    @Override
    public int hashCode() {
        return hash(getId());
    }
}
