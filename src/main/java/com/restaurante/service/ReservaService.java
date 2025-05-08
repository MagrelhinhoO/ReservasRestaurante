
package com.restaurante.service;

import com.restaurante.enums.StatusReserva;
import com.restaurante.exception.ReservaException;
import com.restaurante.model.Cliente;
import com.restaurante.model.Mesa;
import com.restaurante.model.Reserva;
import com.restaurante.repository.ClienteRepository;
import com.restaurante.repository.MesaRepository;
import com.restaurante.repository.ReservaRepository;

import java.time.LocalDateTime;
import java.util.List;

public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final ClienteRepository clienteRepository;
    private final MesaRepository mesaRepository;

    public ReservaService() {
        this.reservaRepository = new ReservaRepository();
        this.clienteRepository = new ClienteRepository();
        this.mesaRepository = new MesaRepository();
    }

    public Reserva criarReserva(Long clienteId, int numeroMesa, LocalDateTime dataHora, int quantidadePessoas) throws ReservaException {
        Cliente cliente = clienteRepository.buscarPorId(clienteId);
        if (cliente == null) {
            throw new ReservaException("Cliente não encontrado!");
        }

        Mesa mesa = mesaRepository.buscarPorNumero(numeroMesa);
        if (mesa == null) {
            throw new ReservaException("Mesa não encontrada!");
        }

        if (!mesa.isDisponivel()) {
            throw new ReservaException("Mesa não está disponível para reserva!");
        }

        if (quantidadePessoas > mesa.getCapacidade()) {
            throw new ReservaException("A mesa não suporta essa quantidade de pessoas!");
        }

        // Verificar se já existe reserva para esta mesa no mesmo horário
        boolean mesaReservada = reservaRepository.existeReservaParaMesaNoHorario(mesa, dataHora);
        if (mesaReservada) {
            throw new ReservaException("Mesa já reservada para este horário!");
        }

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setMesa(mesa);
        reserva.setDataHora(dataHora);
        reserva.setQuantidadePessoas(quantidadePessoas);
        reserva.setStatus(StatusReserva.CONFIRMADA);

        mesa.setDisponivel(false);
        mesaRepository.atualizar(mesa);

        reservaRepository.salvar(reserva);
        return reserva;
    }

    public Reserva buscarReservaPorId(Long id) throws ReservaException {
        Reserva reserva = reservaRepository.buscarPorId(id);
        if (reserva == null) {
            throw new ReservaException("Reserva não encontrada!");
        }
        return reserva;
    }

    public void cancelarReserva(Long id) throws ReservaException {
        Reserva reserva = reservaRepository.buscarPorId(id);
        if (reserva == null) {
            throw new ReservaException("Reserva não encontrada!");
        }

        if (reserva.getStatus() == StatusReserva.CANCELADA) {
            throw new ReservaException("Reserva já está cancelada!");
        }

        reserva.setStatus(StatusReserva.CANCELADA);

        // Liberar a mesa
        Mesa mesa = reserva.getMesa();
        mesa.setDisponivel(true);
        mesaRepository.atualizar(mesa);

        reservaRepository.atualizar(reserva);
    }

    public void realizarPagamento(Long reservaId, int formaPagamentoOpcao) throws ReservaException {
        Reserva reserva = reservaRepository.buscarPorId(reservaId);
        if (reserva == null) {
            throw new ReservaException("Reserva não encontrada!");
        }

        if (reserva.getStatus() != StatusReserva.CONFIRMADA) {
            throw new ReservaException("Só é possível pagar reservas confirmadas!");
        }

        if (reserva.getPagamento() != null && reserva.getPagamento().getStatus().equals(StatusPagamento.PAGO)) {
            throw new ReservaException("Reserva já foi paga!");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setReserva(reserva);
        pagamento.setValor(calcularValorReserva(reserva));
        pagamento.setDataPagamento(LocalDateTime.now());
        pagamento.setStatus(StatusPagamento.PAGO);

        switch (formaPagamentoOpcao) {
            case 1:
                pagamento.setFormaPagamento(FormaPagamento.CARTAO_CREDITO);
                break;
            case 2:
                pagamento.setFormaPagamento(FormaPagamento.CARTAO_DEBITO);
                break;
            case 3:
                pagamento.setFormaPagamento(FormaPagamento.DINHEIRO);
                break;
            case 4:
                pagamento.setFormaPagamento(FormaPagamento.PIX);
                break;
            default:
                throw new ReservaException("Forma de pagamento inválida!");
        }

        reserva.setPagamento(pagamento);
        reserva.setStatus(StatusReserva.PAGA);
        reservaRepository.atualizar(reserva);
    }

    private double calcularValorReserva(Reserva reserva) {
        // Lógica simples de cálculo - pode ser aprimorada
        return reserva.getQuantidadePessoas() * 50.0; // R$50 por pessoa
    }

    public List<Reserva> listarTodasReservas() {
        return reservaRepository.listarTodos();
    }

    public List<Reserva> listarReservasPorCliente(Long clienteId) {
        return reservaRepository.buscarPorCliente(clienteId);
    }

    public List<Reserva> listarReservasPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return reservaRepository.buscarPorPeriodo(inicio, fim);
    }
}