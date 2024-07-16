package escala.Domain.entity;

import aeropuerto.domain.entity.Aeropuerto;
import ruta.Domain.Entity.Ruta;

public class Escala {
    
    private Long id;
    private Ruta ruta;
    private Aeropuerto aeropuerto;
    private String numeroVuelo;

    public Escala() {
    }

    public Escala(Aeropuerto aeropuerto, Long id, String numeroVuelo, Ruta ruta) {
        this.aeropuerto = aeropuerto;
        this.id = id;
        this.numeroVuelo = numeroVuelo;
        this.ruta = ruta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }

    public void setAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    public String getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(String numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }


}
