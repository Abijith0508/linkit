package com.example.linkit.delivery;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.linkit.event.EventEntity;
import com.example.linkit.event.EventService;
 

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; 

@RestController
@RequestMapping("/deliveryAttempt")
public class DeliveryController {
    private final DeliveryAttemptService service;
    private final EventService eventService;

    public DeliveryController(DeliveryAttemptService service, EventService eventService){
        this.service = service;
        this.eventService = eventService;
    }
    @GetMapping
    public List<DeliveryAttemptEntity> getAll(){
        return service.findAll();
    }

    @GetMapping("/event/{id}")
    public List<DeliveryAttemptEntity> getDeliveryAttemptsByEvent(@PathVariable("id") Long id){
        Optional<EventEntity> event = eventService.findById(id);

        if(event.isPresent()){
            return service.findByEvent(event.get());
        }
        return List.of();
    }
    


    @GetMapping("/status/{statusId}/event/{eventId}")
    public List<DeliveryAttemptEntity> getDeliveryAttemptsByStatusAndEvent(@PathVariable("statusId") DeliveryStatus status, @PathVariable("eventId") Long eventId) {
        Optional<EventEntity> event = eventService.findById(eventId);

        if(event.isPresent()){
            return service.findByStatusandEvent( status, event.get());
        }

        return List.of();
    }
    
    @PostMapping
    public DeliveryAttemptEntity create(@RequestBody DeliveryAttemptEntity deliveryAttempt){
        return service.create(deliveryAttempt);
    }
}
