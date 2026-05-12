package com.example.linkit.delivery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.linkit.event.EventEntity;

@Service
public class DeliveryService {
    private RestClient client; 
    private DeliveryAttemptService deliveryAttemptService;

    public DeliveryService(DeliveryAttemptService deliveryAttemptService){
        this.client = RestClient.create(); 
        this.deliveryAttemptService = deliveryAttemptService;
    }

    public void deliver(EventEntity event){
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
            }
            else{
                DeliveryAttemptEntity da = new DeliveryAttemptEntity(); 
                da.setEvent(event);
                da.setStatusCode(statusCodeInteger);
                da.setResponseTimeMs(duration);
                da.setStatus(DeliveryStatus.FAILURE);
                deliveryAttemptService.create(da); 
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
        }
        

    }
}
