package org.project.microservice.tallercomunicacionesmicroservicios.domain.usecase;

import lombok.RequiredArgsConstructor;
import org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification.Notification;
import org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification.NotificationChannel;
import org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification.Slice;
import org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification.StatusNotification;
import org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification.gateway.NotificationGateway;
import org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification.gateway.NotificationRepositoryGateway;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationUseCase {

    private final NotificationGateway gateway;
    private final NotificationRepositoryGateway repositoryGateway;

    public Mono<Notification> sendNotification(Notification notification){
        return gateway.deliver(notification)
                .then(Mono.defer(() ->{
                        notification.setStatus(StatusNotification.SEND.getStatus());
                        return saveNotification(notification);
                }))
                .onErrorResume(ex ->{
                        notification.setStatus(StatusNotification.FAILED.getStatus());
                        return saveNotification(notification)
                                .onErrorResume(savedError-> Mono.empty())
                                .then(Mono.error(ex));
                });
    }

    public Mono<List<String>> getAllChannels(){
        return Flux.fromArray(NotificationChannel.values())
                .map(NotificationChannel::name)
                .collectList();
    }

    public Mono<Slice<Notification>> search(String q, int limit, int offset) {
        return repositoryGateway.search(q, limit + 1, offset)
                .collectList()
                .map(list -> {
                    boolean hasNext = list.size() > limit;
                    List<Notification> slice = hasNext ? list.subList(0, limit) : list;
                    long nextOffset = hasNext ? offset + limit : offset;
                    return new Slice<>(slice, nextOffset, hasNext);
                });
    }

    public Flux<Notification> findByStatus(String status){
        return repositoryGateway.findByStatus(status);
    }

    private Mono<Notification> saveNotification(Notification notification){
        return repositoryGateway.save(notification)
                .onErrorResume(Mono::error);
    }

}
