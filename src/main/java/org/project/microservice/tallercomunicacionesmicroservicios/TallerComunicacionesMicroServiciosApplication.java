package org.project.microservice.tallercomunicacionesmicroservicios;

import org.reactivecommons.async.impl.config.annotations.EnableEventListeners;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableEventListeners
@SpringBootApplication
public class TallerComunicacionesMicroServiciosApplication {

    public static void main(String[] args) {
        SpringApplication.run(TallerComunicacionesMicroServiciosApplication.class, args);
    }

}
