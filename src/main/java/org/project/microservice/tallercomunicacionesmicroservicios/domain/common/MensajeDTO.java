package org.project.microservice.tallercomunicacionesmicroservicios.domain.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MensajeDTO<T> {
    private T mensaje;
}
