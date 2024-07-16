package historialEstado.Domain.entity;

import java.util.Date;

import avion.Domain.Entity.Avion;
import estadoAvion.Domain.entity.EstadoAvion;

public class HistorialEstado {
    private Long id;
    private Avion avion;
    private EstadoAvion estadoAvion;
    private java.util.Date fechaInicio;

    public HistorialEstado() {
    }

    public HistorialEstado(Avion avion, EstadoAvion estadoAvion, Date fechaInicio, Long id) {
        this.avion = avion;
        this.estadoAvion = estadoAvion;
        this.fechaInicio = fechaInicio;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public EstadoAvion getEstadoAvion() {
        return estadoAvion;
    }

    public void setEstadoAvion(EstadoAvion estadoAvion) {
        this.estadoAvion = estadoAvion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    
}
