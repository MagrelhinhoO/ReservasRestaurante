package com.restaurante.model;

import jakarta.persistence.*;

@Entity
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int numero;
    private int capacidade;
    private boolean disponivel;

    public Mesa() {}

    public Mesa(int numero, int capacidade, boolean disponivel) {
        this.numero = numero;
        this.capacidade = capacidade;
        this.disponivel = disponivel;
    }

    public int getId() {
        return id;
    }

    public int getNumero() {
        return numero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Mesa{" +
                "id=" + id +
                ", numero=" + numero +
                ", capacidade=" + capacidade +
                ", disponivel=" + disponivel +
                '}';
    }
}
