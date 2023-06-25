package com.example.AlAkeel.Repos;

import com.example.AlAkeel.Entities.Meal;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository

public class RestaurantRepo {
    @Autowired
    private EntityManager em;

//    @Transactional
//    public void save(RestaurantOwner owner) {
//        em.persist(owner);
//    }

    @Transactional
    public void update(Object obj) {
        em.merge(obj);
    }

//    @Transactional
//    public void save(Restaurant restaurant) {
//        em.persist(restaurant);
//    }

    @Transactional
    public void save(Object obj) {
        em.persist(obj);
    }

    @Transactional
    public <T> T findById(Class<T> tClass,int id){
        return em.find(tClass,id );
    }

    @Transactional
    public void remove(Object obj) {
        em.remove(obj);

    }

    public int query(String s, int id) {
      return   em.createQuery(s,Meal.class).setParameter("id",id).executeUpdate();
    }

}
