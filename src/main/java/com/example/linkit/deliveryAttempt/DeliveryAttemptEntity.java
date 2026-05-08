package com.example.linkit.deliveryAttempt;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.example.linkit.event.EventEntity;
 
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class DeliveryAttemptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    DeliveryStatus status; 

    Integer statusCode;
    Double responseTimeMs;

    @CreationTimestamp
    LocalDateTime attemptedAt;
    
    @ManyToOne
    EventEntity event;
}
