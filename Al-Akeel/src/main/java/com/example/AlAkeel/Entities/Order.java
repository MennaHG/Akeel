package com.example.AlAkeel.Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="Orders")
public class Order implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;


        @ManyToMany
        @Cascade({CascadeType.ALL})
    @JoinTable(
            name = "OrderXMeal",
            joinColumns=@JoinColumn(name="OrderId"),
            inverseJoinColumns=@JoinColumn(name="MealId")
    )
    private Set<Meal> meals;
    private float total;

    @Temporal(TemporalType.TIMESTAMP)

    private Date dateTime;



    @ManyToOne
    @JoinColumn(name ="runnerID")
    private Runner runner;

    @ManyToOne
    @JoinColumn(name ="restaurant")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name="customer")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private orderStatus status=orderStatus.PREPARING;

    public float getTotal() {
        return total;
    }

    public orderStatus getStatus() {
        return status;
    }

    public void setDateTime(Date now) {
        dateTime = now;
    }

    public void setStatus(orderStatus delivered) {
        status = delivered;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }
    public int getId() { return orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Set<Meal> getMeals() { return meals;
    }

    public void setPrice(float price) { this.total = total;
    }

    public Restaurant getRestaurant() { return restaurant;
    }

    public void addMeal(Meal meal) {
        if(meals == null)
            meals = new HashSet<Meal>();
        meals.add(meal);
    }
}