package com.example.linkit.subscriber;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/subscriber")
public class SubscriberController {
    private final SubscriberService service;
    
    public SubscriberController(SubscriberService service){
        this.service = service;
    }
    @GetMapping
    public List<SubscriberEntity> getAll(){
        return service.getAll(); 
    }

    @GetMapping("/{id}")
    public Optional<SubscriberEntity>find(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PostMapping
    public SubscriberEntity create(@RequestBody SubscriberEntity sub){
        return service.create(sub);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deactivate(id);
        return;
    }
}
