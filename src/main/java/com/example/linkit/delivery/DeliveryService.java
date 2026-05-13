package com.example.linkit.delivery;
import java.time.Duration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.linkit.event.EventEntity;
import com.example.linkit.event.EventService;
import com.example.linkit.event.Status;

@Service
public class DeliveryService {
    private RestClient client; 
    private DeliveryAttemptService deliveryAttemptService;
    private EventService eventService;

    public DeliveryService(DeliveryAttemptService deliveryAttemptService, EventService eventService){
        this.client = RestClient.create(); 
        this.deliveryAttemptService = deliveryAttemptService;
        this.eventService = eventService;
    }

    public DeliveryAttemptEntity deliverFn(EventEntity event){
        String url = event.getSubscriber().getUrl();
        String body = event.getBody(); 
        System.out.println(url);
        Long startTime = System.currentTimeMillis();
        Long endTime = 0L;
        try {
            ResponseEntity res =client.post()
            .uri(url).body(body).retrieve().toBodilessEntity();
            endTime = System.currentTimeMillis();
            Long duration = endTime - startTime;

            Integer statusCodeInteger = res.getStatusCode().value();

            if(res.getStatusCode().is2xxSuccessful()){
                DeliveryAttemptEntity da = new DeliveryAttemptEntity(); 
                da.setEvent(event);
                da.setStatusCode(statusCodeInteger);
                da.setResponseTimeMs(duration);
                da.setStatus(DeliveryStatus.SUCCESS);
                deliveryAttemptService.create(da);
                return da; 
            }
            else{
                DeliveryAttemptEntity da = new DeliveryAttemptEntity(); 
                da.setEvent(event);
                da.setStatusCode(statusCodeInteger);
                da.setResponseTimeMs(duration);
                da.setStatus(DeliveryStatus.FAILURE);
                deliveryAttemptService.create(da); 
                return da; 
            }
        } catch (Exception e) { 
            System.out.println(e);
            endTime = System.currentTimeMillis();

            DeliveryAttemptEntity da = new DeliveryAttemptEntity(); 
            da.setEvent(event);
            da.setStatusCode(null);
            da.setResponseTimeMs(endTime - startTime);
            da.setStatus(DeliveryStatus.FAILURE);
            deliveryAttemptService.create(da);
            return da; 

        }
        

    }

    public void deliver(EventEntity event){
        final int maxRetry = 10;
        int counter = 0;
        long waitTimeMs = 100;

        while(counter < maxRetry){
            DeliveryAttemptEntity da = deliverFn(event);
            if(da.getStatus() == DeliveryStatus.SUCCESS){
                break;
            }
            try {
                Thread.sleep(waitTimeMs);             
            } catch (Exception e) {
                
            }
            counter += 1;
            waitTimeMs *= 2;
        }

        if(counter == maxRetry){
            //failed
            event.setStatus(Status.FAILED);
            
        }
        else{
            //success
            event.setStatus(Status.DELIVERED);
        }
        eventService.create(event);
    }

}
