package com.example.AlAkeel.Entities;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.util.Set;
@Entity
public class Meal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mealID;
    @NotNull
    @Column(name = "meal_name")
    private String name;
    @NotNull
    private float price;

    @ManyToOne
    @JoinColumn(name = "restaurant")
    private Restaurant restaurant;


    @ManyToMany(mappedBy = "meals")
    private Set<Order> ordersWithThisMeal;

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
    public void setRestaurant(Restaurant rest){
        this.restaurant= rest;
    }

    public void setName(String name) { this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Restaurant getRestaurant() { return restaurant;
    }

    public Set<Order> getOrders() { return ordersWithThisMeal;
    }
}
