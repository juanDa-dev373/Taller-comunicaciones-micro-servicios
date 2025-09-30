package org.project.microservice.tallercomunicacionesmicroservicios.infrastructure.entry_points.listener.request;

import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public record SendNotificationRequest (
        String requestId,
        Map<String,String> recipients,
        List<Content> contents,
        Map<String,Object> metadata
){
    @Builder
    public static record Content(String channel, String subject, String htmlBody, String textBody) {}
}
