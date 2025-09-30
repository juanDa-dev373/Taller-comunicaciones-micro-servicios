package org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.EnumSet;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    private NotificationChannel channelNotification;

    private Long id;
    private String asunto;
    private String htmlBody;
    private String textBody;

    private String emailUser;
    private String toSms;
    private String toWhatsapp;
    private String status;




}
