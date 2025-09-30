package org.project.microservice.tallercomunicacionesmicroservicios.infrastructure.entry_points.controller.notification;

import lombok.RequiredArgsConstructor;
import org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification.Notification;
import org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification.Slice;
import org.project.microservice.tallercomunicacionesmicroservicios.domain.usecase.NotificationUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.domain.Pageable;
import java.util.List;


@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationUseCase notificationUseCase;

    @GetMapping("")
    public ResponseEntity<Mono<Slice<Notification>>> getNotifications(@RequestParam(defaultValue = "") String q,
                                                                      @RequestParam(defaultValue = "0") int offset,
                                                                      @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(notificationUseCase.search(q, limit, offset));
    }

    @GetMapping("/{status}/status")
    public ResponseEntity<Flux<Notification>> notificationById(@PathVariable(name = "status") String status) {
        return ResponseEntity.ok().body(notificationUseCase.findByStatus(status));
    }
    @GetMapping("/channels")
    public ResponseEntity<Mono<List<String>>> attemptsChannel() {
        return ResponseEntity.ok(notificationUseCase.getAllChannels());
    }

}
