package org.project.microservice.tallercomunicacionesmicroservicios.infrastructure.entry_points.controller.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class Notification {

    @PostMapping
    public ResponseEntity<String> sedNotification(){
        return ResponseEntity.ok("Se ha producido un error");
    }

    @GetMapping
    public ResponseEntity<String> getNotifications() {
        return ResponseEntity.ok().body("notification");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> notificationById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok().body("notification");
    }
    @GetMapping("/{id}/attempts")
    public ResponseEntity<String> attemptsChannel(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok().body("notification");
    }

}
