package com.restaurante.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "gerentes")
public class Gerente extends Administrador {

    @Column(nullable = false, length = 50)
    private String setor;

    public Gerente() {}

    public Gerente(String nome, String email, String setor) {
        super(nome, email);
        this.setor = setor;
    }

    // Getters e Setters
    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        if (setor == null || setor.trim().isEmpty()) {
            throw new IllegalArgumentException("Setor não pode ser vazio");
        }
        this.setor = setor;
    }

    @Override
    public String toString() {
        return "Gerente [id=" + id + ", nome=" + nome + ", email=" + email + ", setor=" + setor + "]";
    }
}