package com.restaurante.controller;

import com.restaurante.model.Cliente;
import com.restaurante.model.Mesa;
import com.restaurante.model.Reserva;
import com.restaurante.service.ClienteService;
import com.restaurante.service.MesaService;
import com.restaurante.service.ReservaService;
import com.restaurante.util.DataUtil;
import com.restaurante.util.ValidacaoUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class RestauranteController {
    private final Scanner scanner;
    private final ClienteService clienteService;
    private final MesaService mesaService;
    private final ReservaService reservaService;

    public RestauranteController() {
        this.scanner = new Scanner(System.in);
        this.clienteService = new ClienteService();
        this.mesaService = new MesaService();
        this.reservaService = new ReservaService();
    }

    public void iniciar() {
        carregarDadosIniciais();
        exibirMenuPrincipal();
    }

    private void carregarDadosIniciais() {
        // Cria alguns dados iniciais para teste
        mesaService.criarMesasIniciais();
        clienteService.criarClientesIniciais();
    }

    private void exibirMenuPrincipal() {
        int opcao;
        do {
            System.out.println("\n=== SISTEMA DE RESERVAS DE RESTAURANTE ===");
            System.out.println("1. Fazer Reserva");
            System.out.println("2. Visualizar Reserva");
            System.out.println("3. Cancelar Reserva");
            System.out.println("4. Realizar Pagamento");
            System.out.println("5. Listar Todas as Reservas");
            System.out.println("6. Listar Mesas Disponíveis");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = ValidacaoUtil.lerInteiro(scanner);

            switch (opcao) {
                case 1:
                    fazerReserva();
                    break;
                case 2:
                    visualizarReserva();
                    break;
                case 3:
                    cancelarReserva();
                    break;
                case 4:
                    realizarPagamento();
                    break;
                case 5:
                    listarTodasReservas();
                    break;
                case 6:
                    listarMesasDisponiveis();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void fazerReserva() {
        System.out.println("\n=== FAZER RESERVA ===");

        // Listar clientes
        System.out.println("Clientes cadastrados:");
        clienteService.listarTodosClientes();
        System.out.print("Digite o ID do cliente: ");
        Long clienteId = scanner.nextLong();
        scanner.nextLine(); // Limpar buffer

        // Listar mesas disponíveis
        System.out.println("\nMesas disponíveis:");
        List<Mesa> mesasDisponiveis = mesaService.listarMesasDisponiveis();
        mesasDisponiveis.forEach(System.out::println);

        System.out.print("Digite o número da mesa desejada: ");
        int numeroMesa = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        System.out.print("Digite a data e hora da reserva (dd/MM/yyyy HH:mm): ");
        String dataHoraStr = scanner.nextLine();
        LocalDateTime dataHora = DataUtil.parseDataHora(dataHoraStr);

        System.out.print("Digite a quantidade de pessoas: ");
        int quantidadePessoas = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        try {
            Reserva reserva = reservaService.criarReserva(clienteId, numeroMesa, dataHora, quantidadePessoas);
            System.out.println("\nReserva realizada com sucesso!");
            System.out.println(reserva);
        } catch (Exception e) {
            System.out.println("Erro ao fazer reserva: " + e.getMessage());
        }
    }

    private void visualizarReserva() {
        System.out.println("\n=== VISUALIZAR RESERVA ===");
        System.out.print("Digite o ID da reserva: ");
        Long reservaId = scanner.nextLong();
        scanner.nextLine(); // Limpar buffer

        try {
            Reserva reserva = reservaService.buscarReservaPorId(reservaId);
            System.out.println("\nDetalhes da Reserva:");
            System.out.println(reserva);
        } catch (Exception e) {
            System.out.println("Erro ao visualizar reserva: " + e.getMessage());
        }
    }

    private void cancelarReserva() {
        System.out.println("\n=== CANCELAR RESERVA ===");
        System.out.print("Digite o ID da reserva que deseja cancelar: ");
        Long reservaId = scanner.nextLong();
        scanner.nextLine(); // Limpar buffer

        try {
            reservaService.cancelarReserva(reservaId);
            System.out.println("Reserva cancelada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cancelar reserva: " + e.getMessage());
        }
    }

    private void realizarPagamento() {
        System.out.println("\n=== REALIZAR PAGAMENTO ===");
        System.out.print("Digite o ID da reserva: ");
        Long reservaId = scanner.nextLong();
        scanner.nextLine(); // Limpar buffer

        System.out.println("Formas de pagamento disponíveis:");
        System.out.println("1. Cartão de Crédito");
        System.out.println("2. Cartão de Débito");
        System.out.println("3. Dinheiro");
        System.out.println("4. Pix");
        System.out.print("Escolha a forma de pagamento: ");
        int formaPagamentoOpcao = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        try {
            reservaService.realizarPagamento(reservaId, formaPagamentoOpcao);
            System.out.println("Pagamento realizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao realizar pagamento: " + e.getMessage());
        }
    }

    private void listarTodasReservas() {
        System.out.println("\n=== LISTA DE TODAS AS RESERVAS ===");
        List<Reserva> reservas = reservaService.listarTodasReservas();
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva encontrada.");
        } else {
            reservas.forEach(System.out::println);
        }
    }

    private void listarMesasDisponiveis() {
        System.out.println("\n=== MESAS DISPONÍVEIS ===");
        List<Mesa> mesas = mesaService.listarMesasDisponiveis();
        if (mesas.isEmpty()) {
            System.out.println("Nenhuma mesa disponível no momento.");
        } else {
            mesas.forEach(System.out::println);
        }
    }
}
