package ruta.Domain.Entity;

import java.util.Date;
import java.util.Set;

import aeropuerto.domain.entity.Aeropuerto;
import reserva.Domain.entity.Reserva;
import salidaAeropuerto.Domain.entity.SalidaAeropuerto;

public class Ruta {
    
    private Long id;
    private java.util.Date fecha;
    private Aeropuerto aeropuertoOrigen;
    private Aeropuerto aeropuertoDestino;
    private SalidaAeropuerto salidaAeropuerto;
    private Set<Reserva> reservas;
    private Set<SalidaAeropuerto> salidas;

    public Ruta() {
    }

    public Ruta(Long id, Date fecha, Aeropuerto aeropuertoOrigen, Aeropuerto aeropuertoDestino,
            SalidaAeropuerto salidaAeropuerto, Set<Reserva> reservas,
            Set<SalidaAeropuerto> salidas) {
        this.id = id;
        this.fecha = fecha;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.aeropuertoDestino = aeropuertoDestino;
        this.salidaAeropuerto = salidaAeropuerto;
        this.reservas = reservas;
        this.salidas = salidas;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.util.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.util.Date fecha) {
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

    public SalidaAeropuerto getSalidaAeropuerto() {
        return salidaAeropuerto;
    }

    public void setSalidaAeropuerto(SalidaAeropuerto salidaAeropuerto) {
        this.salidaAeropuerto = salidaAeropuerto;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Set<SalidaAeropuerto> getSalidas() {
        return salidas;
    }

    public void setSalidas(Set<SalidaAeropuerto> salidas) {
        this.salidas = salidas;
    }

    
    

}
