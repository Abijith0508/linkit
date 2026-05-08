package com.example.linkit.event;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.linkit.subscriber.SubscriberEntity;


public interface EventRepository extends JpaRepository<EventEntity, Long> {
    List<EventEntity> findBySubscriber(SubscriberEntity subscriber);
}
