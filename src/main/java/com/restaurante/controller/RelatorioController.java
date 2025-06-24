package com.restaurante.controller;

import com.restaurante.enums.Status;
import com.restaurante.service.RelatorioService;
import com.restaurante.utils.InputHandler;

import java.time.LocalDate;
import java.util.Scanner;

public class RelatorioController {

    private final RelatorioService service = new RelatorioService();
    private final Scanner sc = new Scanner(System.in);

    public void menuRelatorios() {
        int opcao;
        do {
            System.out.println("\n--- MENU RELATÓRIOS ---");
            System.out.println("1. Reservas por Data");
            System.out.println("2. Reservas por Status");
            System.out.println("3. Ocupação de Mesas");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            opcao = InputHandler.getIntInput(sc);

            switch (opcao) {
                case 1 -> gerarRelatorioPorData();
                case 2 -> gerarRelatorioPorStatus();
                case 3 -> gerarRelatorioOcupacao();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void gerarRelatorioPorData() {
        System.out.print("Data (AAAA-MM-DD): ");
        LocalDate data = InputHandler.getDateInput(sc);
        service.gerarRelatorioReservasPorData(data);
    }

    private void gerarRelatorioPorStatus() {
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
            service.gerarRelatorioReservasPorStatus(status);
        } else {
            System.out.println("Status inválido!");
        }
    }

    private void gerarRelatorioOcupacao() {
        service.gerarRelatorioOcupacaoMesas();
    }
}