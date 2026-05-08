package com.example.linkit.subscriber;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


@Service
public class SubscriberService {
    private final SubscriberRepository subscriberRepository; 

    public SubscriberService(SubscriberRepository subscriberRepository){
        this.subscriberRepository = subscriberRepository;
    }

    public SubscriberEntity create(SubscriberEntity sub){
        SubscriberEntity s = subscriberRepository.save(sub);
        return s;
    }

    public List<SubscriberEntity> getAll(){
        return subscriberRepository.findAll();
    }

    public void deactivate(Long id){
        if(subscriberRepository.existsById(id)){
            subscriberRepository.deleteById(id);
        }
        return;
    }  
    
    public Optional<SubscriberEntity> findById(Long id){
        return subscriberRepository.findById(id);
    }

}