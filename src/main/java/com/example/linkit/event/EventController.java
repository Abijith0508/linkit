package com.example.linkit.event;

import com.example.linkit.subscriber.SubscriberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.linkit.subscriber.SubscriberEntity;

import java.util.List;
import java.util.Optional; 

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; 

@RestController
@RequestMapping("/event")
public class EventController {
    private final SubscriberService subscriberService;
    private final EventService service; 

    public EventController(EventService service, SubscriberService subscriberService){
        this.service = service;
        this.subscriberService = subscriberService;
    }

    @GetMapping
    public List<EventEntity> getAllEvents() {
        return service.getAll();
    }

    
    @GetMapping("/{id}")
    public List<EventEntity> getEventsBySubscriber(@PathVariable("id") Long id){
        Optional<SubscriberEntity> sub = subscriberService.findById(id);
        if(sub.isPresent()){
            return service.findBySubscriber(sub.get());
        }
        return List.of();
    }
    
    @PostMapping
    public EventEntity create(@RequestBody EventEntity event){
        return service.create(event);
    }

}
