package com.restaurante.service;

import com.restaurante.enums.Status;
import com.restaurante.model.Cliente;
import com.restaurante.model.Mesa;
import com.restaurante.model.Reserva;
import com.restaurante.repository.MesaRepository;
import com.restaurante.repository.ReservaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ReservaService {

    private final ReservaRepository reservaRepo = new ReservaRepository();
    private final MesaRepository mesaRepo = new MesaRepository();

    public void realizarReserva(LocalDate data, String horario, int numPessoas, Cliente cliente, int idMesa) {
        validarDadosReserva(data, horario, numPessoas, cliente, idMesa);

        Mesa mesa = mesaRepo.buscarPorId(idMesa);
        verificarDisponibilidadeMesa(mesa, data, horario, numPessoas);

        Reserva reserva = new Reserva(data, horario, Status.PENDENTE, numPessoas, cliente, mesa);
        reservaRepo.salvar(reserva);

        mesa.setDisponivel(false);
        mesaRepo.atualizar(mesa);
    }

    public void confirmarReserva(int idReserva) {
        Reserva reserva = reservaRepo.buscarPorId(idReserva);
        if (reserva == null) {
            throw new IllegalArgumentException("Reserva não encontrada");
        }

        reserva.setStatus(Status.CONFIRMADA);
        reservaRepo.atualizar(reserva);
    }

    public void cancelarReserva(int idReserva) {
        Reserva reserva = reservaRepo.buscarPorId(idReserva);
        if (reserva == null) {
            throw new IllegalArgumentException("Reserva não encontrada");
        }

        reserva.setStatus(Status.CANCELADA);
        reservaRepo.atualizar(reserva);

        Mesa mesa = reserva.getMesa();
        mesa.setDisponivel(true);
        mesaRepo.atualizar(mesa);
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

    public List<Reserva> listarPorCliente(int idCliente) {
        return reservaRepo.listarPorCliente(idCliente);
    }

    private void validarDadosReserva(LocalDate data, String horario, int numPessoas, Cliente cliente, int idMesa) {
        if (data == null || data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data inválida ou no passado");
        }
        if (horario == null || horario.trim().isEmpty()) {
            throw new IllegalArgumentException("Horário inválido");
        }
        if (numPessoas <= 0) {
            throw new IllegalArgumentException("Número de pessoas deve ser positivo");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }
        if (idMesa <= 0) {
            throw new IllegalArgumentException("ID da mesa inválido");
        }
    }

    private void verificarDisponibilidadeMesa(Mesa mesa, LocalDate data, String horario, int numPessoas) {
        if (mesa == null) {
            throw new IllegalArgumentException("Mesa não encontrada");
        }
        if (!mesa.isDisponivel()) {
            throw new IllegalArgumentException("Mesa já reservada");
        }
        if (mesa.getCapacidade() < numPessoas) {
            throw new IllegalArgumentException("A mesa não suporta esta quantidade de pessoas");
        }

        LocalTime novoHorario = LocalTime.parse(horario);
        List<Reserva> reservasNaData = reservaRepo.listarPorData(data);

        for (Reserva r : reservasNaData) {
            if (r.getMesa().getId() == mesa.getId()) {
                LocalTime horarioExistente = LocalTime.parse(r.getHorario());
                if (novoHorario.isAfter(horarioExistente.minusHours(2)) {
                    throw new IllegalArgumentException("Conflito de horário com reserva existente");
                }
            }
        }
    }
}