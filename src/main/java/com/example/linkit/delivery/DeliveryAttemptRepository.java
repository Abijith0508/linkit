package com.example.linkit.delivery;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.linkit.event.EventEntity; 

public interface DeliveryAttemptRepository extends JpaRepository<DeliveryAttemptEntity, Long> {
    List<DeliveryAttemptEntity> findByEvent(EventEntity event);
    List<DeliveryAttemptEntity> findByStatus(DeliveryStatus status);
    List<DeliveryAttemptEntity> findByStatusAndEvent(DeliveryStatus status, EventEntity event);

}

