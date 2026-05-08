package com.example.linkit.subscriber;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepository extends JpaRepository<SubscriberEntity, Long> {
    Optional<SubscriberEntity>findByName(String name);
}
