package rutaEscala.Domain.entity;

import aeropuerto.domain.entity.Aeropuerto;
import avion.Domain.Entity.Avion;
import ruta.Domain.Entity.Ruta;
import salidaAeropuerto.Domain.entity.SalidaAeropuerto;


public class RutaEscala {
    
    private Long id;
    private Aeropuerto aeropuertoOrigen;
    private Aeropuerto aeropuertoDestino;
    private Ruta ruta;
    private Avion avion;
    private String horaLlegada;
    private String horaSalida;
    private SalidaAeropuerto salidaAeropuerto;

    

    public RutaEscala() {
    }
    
    public RutaEscala(Long id, Aeropuerto aeropuertoOrigen, Aeropuerto aeropuertoDestino, Ruta ruta, Avion avion,
            String horaLlegada, String horaSalida, SalidaAeropuerto salidaAeropuerto) {
        this.id = id;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.aeropuertoDestino = aeropuertoDestino;
        this.ruta = ruta;
        this.avion = avion;
        this.horaLlegada = horaLlegada;
        this.horaSalida = horaSalida;
        this.salidaAeropuerto = salidaAeropuerto;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public Ruta getRuta() {
        return ruta;
    }
    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }
    public Avion getAvion() {
        return avion;
    }
    public void setAvion(Avion avion) {
        this.avion = avion;
    }
    public String getHoraLlegada() {
        return horaLlegada;
    }
    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }
    public String getHoraSalida() {
        return horaSalida;
    }
    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }
    public SalidaAeropuerto getSalidaAeropuerto() {
        return salidaAeropuerto;
    }
    public void setSalidaAeropuerto(SalidaAeropuerto salidaAeropuerto) {
        this.salidaAeropuerto = salidaAeropuerto;
    }

    





}
