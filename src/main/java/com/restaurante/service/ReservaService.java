package com.restaurante.service;

import com.restaurante.enums.Status;
import com.restaurante.model.Cliente;
import com.restaurante.model.Mesa;
import com.restaurante.model.Reserva;
import com.restaurante.repository.MesaRepository;
import com.restaurante.repository.ReservaRepository;

import java.time.LocalDate;
import java.util.List;

public class ReservaService {

    private final ReservaRepository reservaRepo = new ReservaRepository();
    private final MesaRepository mesaRepo = new MesaRepository();

    public void realizarReserva(LocalDate data, String horario, Cliente cliente, int idMesa) {
        Mesa mesa = mesaRepo.buscarPorId(idMesa);

        if (mesa == null || !mesa.isDisponivel()) {
            System.out.println("Mesa indisponível!");
            return;
        }

        Reserva reserva = new Reserva(data, horario, Status.PENDENTE, cliente, mesa);
        reservaRepo.salvar(reserva);
        mesa.setDisponivel(false);
        mesaRepo.atualizar(mesa);

        System.out.println("Reserva realizada com sucesso.");
    }

    public void cancelarReserva(int idReserva) {
        Reserva reserva = reservaRepo.buscarPorId(idReserva);
        if (reserva != null && reserva.getStatus() != Status.CANCELADA) {
            reserva.setStatus(Status.CANCELADA);
            reservaRepo.atualizar(reserva);

            Mesa mesa = reserva.getMesa();
            mesa.setDisponivel(true);
            mesaRepo.atualizar(mesa);

            System.out.println("Reserva cancelada.");
        } else {
            System.out.println("Reserva não encontrada ou já cancelada.");
        }
    }

    public List<Reserva> listarTodas() {
        return reservaRepo.listarTodas();
    }

    public List<Reserva> listarPorData(LocalDate data) {
        return reservaRepo.listarPorData(data);
    }

    public List<Reserva> listarPorStatus(Status status) {
        return reservaRepo.listarPorStatus(status);
    }
}
