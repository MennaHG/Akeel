package com.example.AlAkeel.Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Runner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    @Column(unique = true)

    private String name;
    private String password;
    private runnerStatus status=runnerStatus.AVAILABLE;
    private double deliveryFees;
    @Column(name="trips_count")
    private int tripsCount=0;

    @OneToMany(mappedBy = "runner")
    private Set<Order> orders;

    public int getTripsCount() {
        return tripsCount;
    }

    public void setTripsCount(int tripsCount) {
        this.tripsCount = tripsCount;
    }

    public void incrementTC(){tripsCount++;}
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(String statusStr) {
        if(statusStr=="Available")
        {
            this.status=runnerStatus.AVAILABLE;
        }
        if(statusStr=="Busy")
        {
            this.status=runnerStatus.BUSY;
        }
    }

    public double getDeliveryFees() {
        return deliveryFees;
    }

    public void setDeliveryFees(double deliveryFees) {
        this.deliveryFees = deliveryFees;
    }






}