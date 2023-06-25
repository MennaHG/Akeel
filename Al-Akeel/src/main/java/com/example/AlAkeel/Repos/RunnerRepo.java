package com.example.AlAkeel.Repos;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RunnerRepo {
    @Autowired
    private EntityManager em;

    @Transactional
    public void save(Object obj) {
        em.persist(obj);
    }

    @Transactional
    public <T> T find(Class<T> tClass,int id){
        return em.find(tClass,id );
    }

    @Transactional
    public void update(Object obj) {
        em.merge(obj);
    }

}
