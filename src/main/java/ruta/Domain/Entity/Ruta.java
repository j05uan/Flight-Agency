package ruta.Domain.Entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import aeropuerto.domain.entity.Aeropuerto;
import escala.Domain.entity.Escala;
import reserva.Domain.entity.Reserva;
import salidaAeropuerto.Domain.entity.SalidaAeropuerto;

public class Ruta {
    
    private Long id;
    private java.util.Date fecha;
    private Aeropuerto aeropuertoOrigen;
    private Aeropuerto aeropuertoDestino;
    private java.math.BigDecimal valor;
    private Set<Reserva> reservas;
    private Set<Escala> escalas;
    private Set<SalidaAeropuerto> salidas;

    public Ruta() {
    }

    public Ruta(Aeropuerto aeropuertoDestino, Aeropuerto aeropuertoOrigen, Set<Escala> escalas, Date fecha, Long id, Set<Reserva> reservas, Set<SalidaAeropuerto> salidas, BigDecimal valor) {
        this.aeropuertoDestino = aeropuertoDestino;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.escalas = escalas;
        this.fecha = fecha;
        this.id = id;
        this.reservas = reservas;
        this.salidas = salidas;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Aeropuerto getAeropuertoOrigen() {
        return aeropuertoOrigen;
    }

    public void setAeropuertoOrigen(Aeropuerto aeropuertoOrigen) {
        this.aeropuertoOrigen = aeropuertoOrigen;
    }

    public Aeropuerto getAeropuertoDestino() {
        return aeropuertoDestino;
    }

    public void setAeropuertoDestino(Aeropuerto aeropuertoDestino) {
        this.aeropuertoDestino = aeropuertoDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Set<Escala> getEscalas() {
        return escalas;
    }

    public void setEscalas(Set<Escala> escalas) {
        this.escalas = escalas;
    }

    public Set<SalidaAeropuerto> getSalidas() {
        return salidas;
    }

    public void setSalidas(Set<SalidaAeropuerto> salidas) {
        this.salidas = salidas;
    }

    

}
