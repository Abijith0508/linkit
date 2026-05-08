package com.example.linkit.event;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.linkit.subscriber.SubscriberEntity;

@Service
public class EventService{
    private final EventRepository eventRepository;

    public EventService(EventRepository repo){
        this.eventRepository = repo;
    }

    public EventEntity create(EventEntity entity){
        return eventRepository.save(entity);
    }

    public List<EventEntity> findBySubscriber(SubscriberEntity sub){
        return eventRepository.findBySubscriber(sub);
    }

    public Optional<EventEntity> findById(Long id){
        return eventRepository.findById(id);
    }

    public List<EventEntity> getAll(){
        return eventRepository.findAll();
    }
}