package com.restaurante.repository;

import com.restaurante.enums.Status;
import com.restaurante.model.Reserva;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class ReservaRepository {

    private EntityManager em = JPAUtil.getEntityManager();

    public void salvar(Reserva reserva) {
        em.getTransaction().begin();
        em.persist(reserva);
        em.getTransaction().commit();
    }

    public Reserva buscarPorId(int id) {
        return em.find(Reserva.class, id);
    }

    public List<Reserva> listarTodas() {
        return em.createQuery("FROM Reserva", Reserva.class).getResultList();
    }

    public List<Reserva> listarPorStatus(Status status) {
        return em.createQuery("FROM Reserva r WHERE r.status = :status", Reserva.class)
                .setParameter("status", status)
                .getResultList();
    }

    public List<Reserva> listarPorData(LocalDate data) {
        return em.createQuery("FROM Reserva r WHERE r.data = :data", Reserva.class)
                .setParameter("data", data)
                .getResultList();
    }

    public void atualizar(Reserva reserva) {
        em.getTransaction().begin();
        em.merge(reserva);
        em.getTransaction().commit();
    }

    public void deletar(int id) {
        Reserva reserva = buscarPorId(id);
        if (reserva != null) {
            em.getTransaction().begin();
            em.remove(reserva);
            em.getTransaction().commit();
        }
    }

    public void fechar() {
        em.close();
    }
}
