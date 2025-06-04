package com.restaurante.model;

import jakarta.persistence.Entity;

@Entity
public class Gerente extends Administrador {

    private String setor;

    public Gerente() {}

    public Gerente(String nome, String email, String setor) {
        super(nome, email);
        this.setor = setor;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    @Override
    public String toString() {
        return "Gerente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", setor='" + setor + '\'' +
                '}';
    }
}
