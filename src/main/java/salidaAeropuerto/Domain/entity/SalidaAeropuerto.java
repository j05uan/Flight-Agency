package salidaAeropuerto.Domain.entity;

import java.util.Date;

import aeropuerto.domain.entity.Aeropuerto;
import ruta.Domain.Entity.Ruta;

public class SalidaAeropuerto {
    private Long id;
    private Aeropuerto aeropuerto;
    private String salidaAeropuerto;
    public SalidaAeropuerto() {
    }
    public SalidaAeropuerto(Long id, Aeropuerto aeropuerto, Ruta ruta, Date fecha, String salidaAeropuerto) {
        this.id = id;
        this.aeropuerto = aeropuerto;
        this.salidaAeropuerto = salidaAeropuerto;
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
    public String getSalidaAeropuerto() {
        return salidaAeropuerto;
    }
    public void setSalidaAeropuerto(String salidaAeropuerto) {
        this.salidaAeropuerto = salidaAeropuerto;
    }

    
    

}
