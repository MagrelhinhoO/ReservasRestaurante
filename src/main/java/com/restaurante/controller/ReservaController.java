package com.restaurante.controller;

import com.restaurante.enums.Status;
import com.restaurante.model.Cliente;
import com.restaurante.model.Reserva;
import com.restaurante.service.ClienteService;
import com.restaurante.service.ReservaService;

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
            System.out.println("1. Realizar Reserva");
            System.out.println("2. Cancelar Reserva");
            System.out.println("3. Listar Reservas");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> realizar();
                case 2 -> cancelar();
                case 3 -> listar();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void realizar() {
        System.out.print("ID do Cliente: ");
        int idCliente = sc.nextInt();
        sc.nextLine();
        Cliente cliente = clienteService.buscarPorId(idCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Data (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(sc.nextLine());
        System.out.print("Horário (HH:mm): ");
        String horario = sc.nextLine();
        System.out.print("ID da Mesa: ");
        int idMesa = sc.nextInt();

        reservaService.realizarReserva(data, horario, cliente, idMesa);
    }

    private void cancelar() {
        System.out.print("ID da Reserva: ");
        int id = sc.nextInt();
        reservaService.cancelarReserva(id);
    }

    private void listar() {
        List<Reserva> reservas = reservaService.listarTodas();
        reservas.forEach(System.out::println);
    }
}
