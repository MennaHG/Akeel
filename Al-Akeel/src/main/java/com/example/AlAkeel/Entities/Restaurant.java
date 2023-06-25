package com.example.AlAkeel.Entities;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.io.Serializable;
import java.util.Set;
@Entity
public class Restaurant implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int restaurantID;
        public int getRestaurantID() {
            return restaurantID;
        }
        private String name;

        @ManyToOne
        @JoinColumn(name ="owner")
        private RestaurantOwner owner;

        @Cascade(CascadeType.PERSIST)
      //  @Cascade(CascadeType.MERGE)
        @OneToMany(mappedBy = "restaurant",fetch = FetchType.LAZY)
        private Set<Meal> meals ;
        @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
        private Set<Order> orders;

        public void setOwnerID(RestaurantOwner owner) {
                this.owner=owner;
        }

        public String getName() {
                return name;
        }

        public Set<Meal> getMeals() {
                return meals;
        }

        public RestaurantOwner getOwner() {
                return owner;
        }

        public Set<Order> getOrders() {
                return orders;
        }
}
