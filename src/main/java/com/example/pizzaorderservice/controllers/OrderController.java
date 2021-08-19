package com.example.pizzaorderservice.controllers;

import com.example.pizzaorderservice.entities.PizzaOrder;
import com.example.pizzaorderservice.repositories.OrderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")
    public List<PizzaOrder> pizzaOrders() {
        return orderRepository.findAll();
    }

    @PostMapping("/orders")
    public void addOrder(@RequestBody PizzaOrder pizzaOrder){
        orderRepository.save(pizzaOrder);
    }

}
