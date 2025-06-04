package com.restaurante.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected String nome;
    protected String email;

    public Administrador() {}

    public Administrador(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
