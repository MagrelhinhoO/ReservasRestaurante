package com.restaurante.service;

import com.restaurante.enums.Status;
import com.restaurante.model.Reserva;
import com.restaurante.repository.ReservaRepository;

import java.time.LocalDate;
import java.util.List;

public class RelatorioService {

    private final ReservaRepository reservaRepository = new ReservaRepository();

    public void gerarRelatorioReservasPorData(LocalDate data) {
        List<Reserva> reservas = reservaRepository.listarPorData(data);
        System.out.println("\n=== RESERVAS PARA " + data + " ===");
        reservas.forEach(System.out::println);
        System.out.println("Total: " + reservas.size() + " reserva(s)");
    }

    public void gerarRelatorioReservasPorStatus(Status status) {
        List<Reserva> reservas = reservaRepository.listarPorStatus(status);
        System.out.println("\n=== RESERVAS " + status + " ===");
        reservas.forEach(System.out::println);
        System.out.println("Total: " + reservas.size() + " reserva(s)");
    }

    public void gerarRelatorioOcupacaoMesas() {
        long totalMesas = reservaRepository.countMesas();
        long mesasOcupadas = reservaRepository.countMesasOcupadas();
        double percentual = (double) mesasOcupadas / totalMesas * 100;

        System.out.println("\n=== OCUPAÇÃO DE MESAS ===");
        System.out.println("Mesas ocupadas: " + mesasOcupadas);
        System.out.println("Mesas disponíveis: " + (totalMesas - mesasOcupadas));
        System.out.println("Total mesas: " + totalMesas);
        System.out.printf("Percentual de ocupação: %.2f%%\n", percentual);
    }
}