package com.restaurante.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("reservaPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
