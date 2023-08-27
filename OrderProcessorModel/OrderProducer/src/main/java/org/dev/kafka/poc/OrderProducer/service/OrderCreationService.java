package org.dev.kafka.poc.OrderProducer.service;

import org.dev.kafka.poc.OrderProducer.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that is responsible for creating orders and pushing them to "Orders" Kafka Topic
 */

@Service
public class OrderCreationService {

    Map<Integer,Order> orders = new HashMap<>();
    public Order getOrder(String id) {
        return orders.get(id);
    }

    public Order createOrder(Order order) {
        Order newOrder = new Order(order);
        return newOrder;
    }
}
