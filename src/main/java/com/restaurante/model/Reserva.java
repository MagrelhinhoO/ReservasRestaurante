package com.restaurante.model;

import com.restaurante.enums.StatusReserva;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "mesa_id", nullable = false)
    private Mesa mesa;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "quantidade_pessoas", nullable = false)
    private int quantidadePessoas;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusReserva status;

    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL)
    private Pagamento pagamento;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public int getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(int quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    @Override
    public String toString() {
        return "Reserva #" + id +
                "\nCliente: " + cliente.getNome() +
                "\nMesa: " + mesa.getNumero() + " (Capacidade: " + mesa.getCapacidade() + ")" +
                "\nData/Hora: " + dataHora +
                "\nQuantidade de Pessoas: " + quantidadePessoas +
                "\nStatus: " + status +
                (pagamento != null ? "\nPagamento: " + pagamento : "");
    }
}
