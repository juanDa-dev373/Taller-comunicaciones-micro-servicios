package org.project.microservice.tallercomunicacionesmicroservicios.infrastructure.driven_adapter.jpa_repository.notification;


import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NotificationRepository extends R2dbcRepository<NotificationEntity, Long> {

    @Query("""
          SELECT *
          FROM notification_entity n
          WHERE (:q IS NULL OR n.email_user ILIKE '%' || :q || '%')
          ORDER BY n.id DESC
          LIMIT :limit::int OFFSET :offset::int
    """)
    Flux<NotificationEntity> search(String q, int limit, int offset);

    @Query("""
        SELECT *
        FROM notification_entity n
        WHERE n.status = :status
        ORDER BY n.id DESC
    """)
    Flux<NotificationEntity> findByStatus(@Param("status") String status);
}
