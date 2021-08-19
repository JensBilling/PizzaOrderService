package com.example.pizzaorderservice;

import com.example.pizzaorderservice.entities.PizzaOrder;
import com.example.pizzaorderservice.repositories.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PizzaOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaOrderServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(OrderRepository orderRepository){
        return (args) -> {
            if (orderRepository.count() == 0) {
                orderRepository.save(new PizzaOrder(1L, "Jens", "jens@email.com", "12345678", "Vesuvio", 85));

            }

        };

    }

}
