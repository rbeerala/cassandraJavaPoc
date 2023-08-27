package org.dev.kafka.poc.OrderProducer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Random;

public class Order {
    @JsonProperty("customerId")
    int customerId;
    @JsonProperty("id")
    int id;
    @JsonProperty("orderItems")
    Map<OrderItem,Integer> orderItems;
    @JsonProperty("dealer")
    String dealer;
    @JsonProperty("orderStatus")
    OrderStatus orderStatus;

    public Order(int customerId){
        this.id = new Random().nextInt(100000);
        this.customerId=customerId;
    }

    public Order(Order order) {
        this.id = new Random().nextInt(100000);
        this.setCustomerId(order.getCustomerId());
        copyOrderItems(this,order);
    }



    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getDealer() {
        return dealer;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }


    /**
     * Creates/Updates an order item
     * @param item
     * @param count
     */
    public void setItem(OrderItem item, int count){
        orderItems.put(item,count);
    }

    /**
     * Return current order's item map
     * @return
     */
    public Map<OrderItem,Integer> getOrderItems(){
        return orderItems;
    }


    /**
     * Deep Copy of orderItems : order2 to order1
     * @param order1
     * @param order2
     */
    private void copyOrderItems(Order order1, Order order2) {
        Map<OrderItem,Integer>orderItems = order2.getOrderItems();
        for(Map.Entry<OrderItem,Integer>orderItem : order2.getOrderItems().entrySet()){
          OrderItem item = new OrderItem(orderItem.getKey());
          orderItems.put(item,orderItem.getValue());
        }
    }



}
