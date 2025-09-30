package org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification;

import lombok.Getter;

@Getter
public enum TypeNotification {

    LOGIN(1, "Iniciaste sesion en tu cuenta"),
    REGISTER(2, "Registro a la plataforma"),
    RESET_PASSWORD(3, "Codigo de recuperacion");

    private final int value;
    private final String description;

    TypeNotification(int value, String description) {
        this.value = value;
        this.description = description;
    }


}
