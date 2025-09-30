package org.project.microservice.tallercomunicacionesmicroservicios.infrastructure.entry_points.controller.config;

import org.project.microservice.tallercomunicacionesmicroservicios.domain.common.MensajeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    private static final Logger log = LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensajeDTO<?>> generalException(Exception e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                MensajeDTO.builder().mensaje(e.getMessage()).build()
        );
    }

}