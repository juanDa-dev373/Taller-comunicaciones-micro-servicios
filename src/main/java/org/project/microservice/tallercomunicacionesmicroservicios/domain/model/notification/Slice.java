package org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification;

import java.util.List;

public record Slice<T>(List<T> items, long nextOffset, boolean hasNext) {}

