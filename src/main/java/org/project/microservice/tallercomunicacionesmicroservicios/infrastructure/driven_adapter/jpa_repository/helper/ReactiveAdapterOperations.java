package org.project.microservice.tallercomunicacionesmicroservicios.infrastructure.driven_adapter.jpa_repository.helper;

import org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification.Notification;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.ParameterizedType;
import java.util.function.Function;

public abstract class ReactiveAdapterOperations<E, D, I, R extends R2dbcRepository<D, I>> {
    protected final R repository;
    protected final ObjectMapper mapper;
    private final Class<D> dataClass;
    private final Function<D, E> toEntityFn;

    @SuppressWarnings("unchecked")
    protected ReactiveAdapterOperations(R repository, ObjectMapper mapper, Function<D, E> toEntityFn) {
        this.repository = repository;
        this.mapper = mapper;
        this.toEntityFn = toEntityFn;
        ParameterizedType gs = (ParameterizedType) getClass().getGenericSuperclass();
        this.dataClass = (Class<D>) gs.getActualTypeArguments()[1];
    }

    protected D toData(E entity) { return mapper.map(entity, dataClass); }

    protected E toEntity(D data) { return data != null ? toEntityFn.apply(data) : null; }

    public Mono<E> save(E entity) {
        D data = toData(entity);
        return repository.save(data).map(this::toEntity);
    }

    public Mono<E> findById(I id) {
        return repository.findById(id).map(this::toEntity);
    }

    public Flux<E> findAll() {
        return repository.findAll().map(this::toEntity);
    }

    public Mono<Void> deleteById(I id) {
        return repository.deleteById(id);
    }
}
