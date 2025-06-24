package com.restaurante.service;

import com.restaurante.model.Mesa;
import com.restaurante.repository.MesaRepository;

import java.util.List;

public class MesaService {

    private final MesaRepository repository = new MesaRepository();

    public void cadastrarMesa(int numero, int capacidade, boolean disponivel) {
        if (numero <= 0) {
            throw new IllegalArgumentException("Número da mesa deve ser positivo");
        }
        if (capacidade <= 0) {
            throw new IllegalArgumentException("Capacidade deve ser positiva");
        }

        Mesa mesa = new Mesa(numero, capacidade, disponivel);
        repository.salvar(mesa);
    }

    public List<Mesa> listarTodas() {
        return repository.listarTodas();
    }

    public List<Mesa> listarDisponiveis() {
        return repository.listarDisponiveis();
    }

    public Mesa buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public void atualizarMesa(Mesa mesa) {
        if (mesa == null) {
            throw new IllegalArgumentException("Mesa não pode ser nula");
        }
        repository.atualizar(mesa);
    }

    public void removerMesa(int id) {
        repository.deletar(id);
    }

    public void liberarMesa(int idMesa) {
        Mesa mesa = repository.buscarPorId(idMesa);
        if (mesa != null) {
            mesa.setDisponivel(true);
            repository.atualizar(mesa);
        }
    }

    public void ocuparMesa(int idMesa) {
        Mesa mesa = repository.buscarPorId(idMesa);
        if (mesa != null) {
            mesa.setDisponivel(false);
            repository.atualizar(mesa);
        }
    }
}