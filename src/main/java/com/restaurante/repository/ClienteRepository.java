package com.restaurante.repository;

import com.restaurante.model.Cliente;
import jakarta.persistence.EntityManager;
import java.util.List;

public class ClienteRepository {

    private EntityManager em = JPAUtil.getEntityManager();

    public void salvar(Cliente cliente) {
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
    }

    public Cliente buscarPorId(int id) {
        return em.find(Cliente.class, id);
    }

    public List<Cliente> listarTodos() {
        return em.createQuery("FROM Cliente", Cliente.class).getResultList();
    }

    public void atualizar(Cliente cliente) {
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
    }

    public void deletar(int id) {
        Cliente cliente = buscarPorId(id);
        if (cliente != null) {
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
        }
    }

    public void fechar() {
        em.close();
    }
}
