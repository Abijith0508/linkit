package com.example.linkit.deliveryAttempt;

import java.util.List; 

import org.springframework.stereotype.Service;

import com.example.linkit.event.EventEntity;

@Service
public class DeliveryAttemptService {
    private final DeliveryAttemptRepository repository; 

    public DeliveryAttemptService(DeliveryAttemptRepository repository){
        this.repository = repository;
    }

    public DeliveryAttemptEntity create(DeliveryAttemptEntity deliveryAttempt){
        return repository.save(deliveryAttempt);
    }

    public List<DeliveryAttemptEntity> findAll(){
        return repository.findAll();
    }

    public List<DeliveryAttemptEntity> findByEvent(EventEntity event){
        return repository.findByEvent(event);
    }

    public List<DeliveryAttemptEntity> findByStatusandEvent(DeliveryStatus status, EventEntity event){
        return repository.findByStatusAndEvent(status, event);
    }
    
    public List<DeliveryAttemptEntity> findByStatus(DeliveryStatus status){
        return repository.findByStatus(status);
    }
}
