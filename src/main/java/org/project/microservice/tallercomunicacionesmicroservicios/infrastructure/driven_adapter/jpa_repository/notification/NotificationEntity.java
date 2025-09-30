package org.project.microservice.tallercomunicacionesmicroservicios.infrastructure.driven_adapter.jpa_repository.notification;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notification")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String asunto;
    private String htmlBody;
    private String textBody;
    private String emailUser;
    private String toSms;
    private String toWhatsapp;
    private String status;
    private String channel;
}
