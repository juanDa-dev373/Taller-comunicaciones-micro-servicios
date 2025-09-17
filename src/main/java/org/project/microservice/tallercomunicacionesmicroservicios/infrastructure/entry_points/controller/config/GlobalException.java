package org.project.microservice.tallercomunicacionesmicroservicios.infrastructure.entry_points.controller.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    private static final Logger log = LoggerFactory.getLogger(GlobalException.class);

    /*@ExceptionHandler(Exception.class)
    public ResponseEntity<MensajeDTO<?>> generalException(Exception e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                MensajeDTO.builder().mensaje(e.getMessage()).build()
        );
    }

    @ExceptionHandler(PrestamoException.class)
    public ResponseEntity<MensajeDTO<?>> errorException(PrestamoException e){
        log.error(e.getMessage());
        return ResponseEntity.status(e.getCodigo()).body(
                MensajeDTO.builder().mensaje(e.getMessage()).build()
        );
    }*/
}