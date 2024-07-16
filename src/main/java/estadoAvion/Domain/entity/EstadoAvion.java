package estadoAvion.Domain.entity;

import java.util.Set;

import historialEstado.Domain.entity.HistorialEstado;

public class EstadoAvion {
     private Long id;
    private String estado;
    private Set<HistorialEstado> historialEstados;

    public EstadoAvion() {
    }

    public EstadoAvion(String estado, Set<HistorialEstado> historialEstados, Long id) {
        this.estado = estado;
        this.historialEstados = historialEstados;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Set<HistorialEstado> getHistorialEstados() {
        return historialEstados;
    }

    public void setHistorialEstados(Set<HistorialEstado> historialEstados) {
        this.historialEstados = historialEstados;
    }



}
