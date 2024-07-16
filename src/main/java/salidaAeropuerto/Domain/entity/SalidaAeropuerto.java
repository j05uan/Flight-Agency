package salidaAeropuerto.Domain.entity;

import java.util.Date;

import aeropuerto.domain.entity.Aeropuerto;
import ruta.Domain.Entity.Ruta;

public class SalidaAeropuerto {
    private Long id;
    private Aeropuerto aeropuerto;
    private Ruta ruta;
    private java.util.Date fecha;
    public SalidaAeropuerto() {
    }
    public SalidaAeropuerto(Long id, Aeropuerto aeropuerto, Ruta ruta, Date fecha) {
        this.id = id;
        this.aeropuerto = aeropuerto;
        this.ruta = ruta;
        this.fecha = fecha;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }
    public void setAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }
    public Ruta getRuta() {
        return ruta;
    }
    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }
    public java.util.Date getFecha() {
        return fecha;
    }
    public void setFecha(java.util.Date fecha) {
        this.fecha = fecha;
    }

    

}
