package com.restaurante.model;

import com.restaurante.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate data;
    private String horario;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    public Reserva() {}

    public Reserva(LocalDate data, String horario, Status status, Cliente cliente, Mesa mesa) {
        this.data = data;
        this.horario = horario;
        this.status = status;
        this.cliente = cliente;
        this.mesa = mesa;
    }

    public int getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public String getHorario() {
        return horario;
    }

    public Status getStatus() {
        return status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", data=" + data +
                ", horario='" + horario + '\'' +
                ", status=" + status +
                ", cliente=" + (cliente != null ? cliente.getNome() : "null") +
                ", mesa=" + (mesa != null ? mesa.getNumero() : "null") +
                '}';
    }
}
