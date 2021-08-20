package com.example.pizzaorderservice.controllers;

import com.example.pizzaorderservice.entities.Pizza;
import com.example.pizzaorderservice.entities.PizzaOrder;
import com.example.pizzaorderservice.repositories.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.hibernate.engine.transaction.jta.platform.internal.WeblogicJtaPlatform;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
public class OrderController {


    private OrderRepository orderRepository;


    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @GetMapping("/orders")
    public List<PizzaOrder> pizzaOrders() {
        return orderRepository.findAll();
    }

    @PostMapping("/orders")
    public String addOrder(@RequestBody PizzaOrder pizzaOrder) throws JsonProcessingException {
        if (pizzaOrder.getCustomerEmail() == null && pizzaOrder.getCustomerNumber() == null) {
            return "Error: you must enter email-address or phone number";
        }

        // ask pizza service for ingredients for ordered pizza.

        long pizzaId;
        String orderedPizzaName = pizzaOrder.getOrderedPizza();
        switch (orderedPizzaName) {
            case "Vesuvio":
                pizzaId = 1L;
                break;
            case "Kababpizza":
                pizzaId = 2L;
                break;
            case "Calzone":
                pizzaId = 3L;
                break;
            case "Reale":
                pizzaId = 4L;
                break;
            default:
                return "Error: There is no pizza with that name!";
        }
        WebClient client = WebClient.create();
        WebClient.ResponseSpec responseSpec = client.get().uri("http://localhost:8080/pizzas/" + pizzaId).retrieve();
        // Returning data from pizza service to responseBody as String
        String responseBody = responseSpec.bodyToMono(String.class).block();

        // converting json to java object
        ObjectMapper objectMapper = new ObjectMapper();
        Pizza pizza = objectMapper.readValue(responseBody, Pizza.class);


        pizzaOrder.setIngredients(pizza.getIngredients());
        pizzaOrder.setOrderedPizza(pizza.getName());
        pizzaOrder.setMoneyOwed(pizza.getPrice());

        orderRepository.save(pizzaOrder);

        return "Your order has been taken! You will receive your order verification via email or sms soon.";
    }

}
