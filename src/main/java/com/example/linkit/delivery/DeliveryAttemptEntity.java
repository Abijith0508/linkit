package com.example.linkit.delivery;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAttemptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    DeliveryStatus status; 

    Integer statusCode;
    Long responseTimeMs;

    @CreationTimestamp
    LocalDateTime attemptedAt;
    
    @ManyToOne
    EventEntity event;
}
