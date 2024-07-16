package reserva.Domain.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import asientoReserva.Domain.Entity.AsientosReserva;
import cliente.Domain.entity.Cliente;
import ruta.Domain.Entity.Ruta;
import tarifa.Domain.entity.Tarifa;

public class Reserva {
    private Long id;
    private Cliente cliente;
    private Ruta ruta;
    private java.util.Date fecha;
    private Tarifa tarifa;
    private java.math.BigDecimal valor;
    private Set<AsientosReserva> asientosReservas;
    public Reserva() {
    }

    public Reserva(Set<AsientosReserva> asientosReservas, Cliente cliente, Date fecha, Long id, Ruta ruta, Tarifa tarifa, BigDecimal valor) {
        this.asientosReservas = asientosReservas;
        this.cliente = cliente;
        this.fecha = fecha;
        this.id = id;
        this.ruta = ruta;
        this.tarifa = tarifa;
        this.valor = valor;
    }

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

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Set<AsientosReserva> getAsientosReservas() {
        return asientosReservas;
    }

    public void setAsientosReservas(Set<AsientosReserva> asientosReservas) {
        this.asientosReservas = asientosReservas;
    }

    

}
