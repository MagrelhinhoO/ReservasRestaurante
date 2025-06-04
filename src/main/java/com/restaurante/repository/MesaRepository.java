package com.restaurante.repository;

import com.restaurante.model.Mesa;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MesaRepository {

    private EntityManager em = JPAUtil.getEntityManager();

    public void salvar(Mesa mesa) {
        em.getTransaction().begin();
        em.persist(mesa);
        em.getTransaction().commit();
    }

    public Mesa buscarPorId(int id) {
        return em.find(Mesa.class, id);
    }

    public List<Mesa> listarTodas() {
        return em.createQuery("FROM Mesa", Mesa.class).getResultList();
    }

    public List<Mesa> listarDisponiveis() {
        return em.createQuery("FROM Mesa m WHERE m.disponivel = true", Mesa.class).getResultList();
    }

    public void atualizar(Mesa mesa) {
        em.getTransaction().begin();
        em.merge(mesa);
        em.getTransaction().commit();
    }

    public void deletar(int id) {
        Mesa mesa = buscarPorId(id);
        if (mesa != null) {
            em.getTransaction().begin();
            em.remove(mesa);
            em.getTransaction().commit();
        }
    }

    public void fechar() {
        em.close();
    }
}
