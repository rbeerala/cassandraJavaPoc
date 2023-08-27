package org.dev.kafka.poc.OrderProducer.model;

import java.util.Random;

public class OrderItem {
    private int id;
    private String name;
    int price;

    OrderItem(){
        this.id=new Random().nextInt(100000);
    }

    OrderItem(String name,int price){
        this.id=new Random().nextInt(100000);
        this.name=name;
        this.price=price;
    }

    public OrderItem(OrderItem aOrderItem) {
        this.id= aOrderItem.getId();
        this.name=aOrderItem.getName();
        this.price=aOrderItem.getPrice();
    }

    public int getId(){
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setPrice(int price){
        this.price=price;
    }

}
