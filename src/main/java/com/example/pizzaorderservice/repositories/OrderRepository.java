package com.example.pizzaorderservice.repositories;

import com.example.pizzaorderservice.entities.PizzaOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<PizzaOrder, Long> {
}
