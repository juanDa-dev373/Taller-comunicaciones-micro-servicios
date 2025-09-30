package org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification;

import lombok.Getter;

@Getter
public enum StatusNotification {

    SEND("SEND"), PENDING("PENDING"), FAILED("FAILED");

    private final String status;

    StatusNotification(String status){
        this.status = status;
    }

}
