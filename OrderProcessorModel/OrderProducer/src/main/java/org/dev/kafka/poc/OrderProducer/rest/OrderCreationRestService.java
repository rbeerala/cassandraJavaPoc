package org.dev.kafka.poc.OrderProducer.rest;

import org.dev.kafka.poc.OrderProducer.model.Order;
import org.dev.kafka.poc.OrderProducer.service.OrderCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderCreationRestService {

    @Autowired
    private OrderCreationService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable String id){
       Order requestOrder = orderService.getOrder(id);
       if(requestOrder==null){
           return ResponseEntity.notFound().build();
       }else{
           return ResponseEntity.ok(requestOrder);
       }
    }

    @PostMapping("/")
    public int createOrder(@RequestBody Order order){
        Order createdOrder = orderService.createOrder(order);
        return createdOrder.getId();
    }

}
