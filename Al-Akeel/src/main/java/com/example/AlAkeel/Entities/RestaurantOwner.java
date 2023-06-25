package com.example.AlAkeel.Entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class RestaurantOwner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    @Column(unique = true)

    private String name;
    private String password;

   @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
   private Set<Restaurant> rests;



    public int getUserID() { return userID;
    }

    public String getName() {
        return name;
    }

    public Set<Restaurant> getRests() {
        return rests;
    }

    public String getPassword() { return password;
    }
}