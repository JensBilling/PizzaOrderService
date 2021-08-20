package com.example.pizzaorderservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PizzaOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String customerName;
    private String customerEmail;
    private String customerNumber;
    private String orderedPizza;
    private String ingredients;
    private int moneyOwed;

    public PizzaOrder() {
    }

    public PizzaOrder(Long id, String customerName, String customerEmail, String customerNumber, String orderedPizza, String ingredients, int moneyOwed) {
        this.id = id;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerNumber = customerNumber;
        this.orderedPizza = orderedPizza;
        this.moneyOwed = moneyOwed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getOrderedPizza() {
        return orderedPizza;
    }

    public void setOrderedPizza(String orderedPizza) {
        this.orderedPizza = orderedPizza;
    }

    public int getMoneyOwed() {
        return moneyOwed;
    }

    public void setMoneyOwed(int moneyOwed) {
        this.moneyOwed = moneyOwed;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
