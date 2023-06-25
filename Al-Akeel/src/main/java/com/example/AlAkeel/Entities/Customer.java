package com.example.AlAkeel.Entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class Customer  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    @Column(unique = true)
    private String name;
    private String password;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<Order> orders;



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



}