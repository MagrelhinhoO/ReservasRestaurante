package com.restaurante.controller;

import com.restaurante.enums.Status;
import com.restaurante.model.Cliente;
import com.restaurante.model.Reserva;
import com.restaurante.service.ClienteService;
import com.restaurante.service.ReservaService;
import com.restaurante.utils.InputHandler;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReservaController {

    private final ReservaService reservaService = new ReservaService();
    private final ClienteService clienteService = new ClienteService();
    private final Scanner sc = new Scanner(System.in);

    public void menuReserva() {
        int opcao;
        do {
            System.out.println("\n--- MENU RESERVA ---");
            System.out.println("1. Nova Reserva");
            System.out.println("2. Confirmar Reserva");
            System.out.println("3. Cancelar Reserva");
            System.out.println("4. Listar Todas");
            System.out.println("5. Listar por Data");
            System.out.println("6. Listar por Status");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            opcao = InputHandler.getIntInput(sc);

            switch (opcao) {
                case 1 -> realizarReserva();
                case 2 -> confirmarReserva();
                case 3 -> cancelarReserva();
                case 4 -> listarTodas();
                case 5 -> listarPorData();
                case 6 -> listarPorStatus();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void realizarReserva() {
        System.out.println("\n--- NOVA RESERVA ---");

        System.out.print("ID do Cliente: ");
        int idCliente = InputHandler.getIntInput(sc);
        Cliente cliente = clienteService.buscarPorId(idCliente);

        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        System.out.print("Data (AAAA-MM-DD): ");
        LocalDate data = InputHandler.getDateInput(sc);

        System.out.print("Horário (HH:mm): ");
        String horario = InputHandler.getTimeInput(sc);

        System.out.print("Número de Pessoas: ");
        int numPessoas = InputHandler.getIntInput(sc);

        System.out.print("ID da Mesa: ");
        int idMesa = InputHandler.getIntInput(sc);

        reservaService.realizarReserva(data, horario, numPessoas, cliente, idMesa);
    }

    private void confirmarReserva() {
        System.out.print("ID da Reserva: ");
        int id = InputHandler.getIntInput(sc);
        reservaService.confirmarReserva(id);
        System.out.println("Reserva confirmada!");
    }

    private void cancelarReserva() {
        System.out.print("ID da Reserva: ");
        int id = InputHandler.getIntInput(sc);
        reservaService.cancelarReserva(id);
        System.out.println("Reserva cancelada!");
    }

    private void listarTodas() {
        List<Reserva> reservas = reservaService.listarTodas();
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva cadastrada.");
        } else {
            reservas.forEach(System.out::println);
        }
    }

    private void listarPorData() {
        System.out.print("Data (AAAA-MM-DD): ");
        LocalDate data = InputHandler.getDateInput(sc);
        List<Reserva> reservas = reservaService.listarPorData(data);

        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva para esta data.");
        } else {
            reservas.forEach(System.out::println);
        }
    }

    private void listarPorStatus() {
        System.out.println("Status: 1-Pendente 2-Confirmada 3-Cancelada");
        System.out.print("Escolha: ");
        int escolha = InputHandler.getIntInput(sc);
        Status status = switch (escolha) {
            case 1 -> Status.PENDENTE;
            case 2 -> Status.CONFIRMADA;
            case 3 -> Status.CANCELADA;
            default -> null;
        };

        if (status != null) {
            List<Reserva> reservas = reservaService.listarPorStatus(status);
            if (reservas.isEmpty()) {
                System.out.println("Nenhuma reserva com este status.");
            } else {
                reservas.forEach(System.out::println);
            }
        } else {
            System.out.println("Status inválido!");
        }
    }
}