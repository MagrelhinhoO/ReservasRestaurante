package com.restaurante.repository;

import com.restaurante.model.Reserva;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

public class ReservaRepository {
    private final EntityManagerFactory emf;
    private final EntityManager em;

    public ReservaRepository() {
        this.emf = Persistence.createEntityManagerFactory("restaurantePU");
        this.em = emf.createEntityManager();
    }

    public void salvar(Reserva reserva) {
        em.getTransaction().begin();
        if (reserva.getId() == null) {
            em.persist(reserva);
        } else {
            em.merge(reserva);
        }
        em.getTransaction().commit();
    }

    public Reserva buscarPorId(Long id) {
        return em.find(Reserva.class, id);
    }

    public void atualizar(Reserva reserva) {
        em.getTransaction().begin();
        em.merge(reserva);
        em.getTransaction().commit();
    }

    public List<Reserva> listarTodos() {
        TypedQuery<Reserva> query = em.createQuery("SELECT r FROM Reserva r", Reserva.class);
        return query.getResultList();
    }

    public List<Reserva> buscarPorCliente(Long clienteId) {
        TypedQuery<Reserva> query = em.createQuery(
                "SELECT r FROM Reserva r WHERE r.cliente.id = :clienteId", Reserva.class);
        query.setParameter("clienteId", clienteId);
        return query.getResultList();
    }

    public List<Reserva> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        TypedQuery<Reserva> query = em.createQuery(
                "SELECT r FROM Reserva r WHERE r.dataHora BETWEEN :inicio AND :fim", Reserva.class);
        query.setParameter("inicio", inicio);
        query.setParameter("fim", fim);
        return query.getResultList();
    }

    public boolean existeReservaParaMesaNoHorario(Mesa mesa, LocalDateTime dataHora) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(r) FROM Reserva r WHERE r.mesa = :mesa AND r.dataHora = :dataHora AND r.status <> 'CANCELADA'", Long.class);
        query.setParameter("mesa", mesa);
        query.setParameter("dataHora", dataHora);
        return query.getSingleResult() > 0;
    }

    public void close() {
        em.close();
        emf.close();
    }
}