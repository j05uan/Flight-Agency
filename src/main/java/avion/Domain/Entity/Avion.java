package avion.Domain.Entity;

import java.util.Date;
import java.util.Set;

import aerolinea.Domain.Entity.Aerolinea;
import asiento.Domain.Entity.Asiento;
import historialEstado.Domain.entity.HistorialEstado;
import modelo.Domain.entity.Modelo;

public class Avion {
    private Long id;
    private String matricula;
    private int capacidad;
    private Date fechaFabricacion;
    private Aerolinea aerolinea;
    private Modelo modelo;
    private Set<Asiento> asientos;
    private Set<HistorialEstado> historialEstados;

    public Avion() {
    }

    public Avion(Aerolinea aerolinea, Set<Asiento> asientos, int capacidad, Date fechaFabricacion, Set<HistorialEstado> historialEstados, Long id, String matricula, Modelo modelo) {
        this.aerolinea = aerolinea;
        this.asientos = asientos;
        this.capacidad = capacidad;
        this.fechaFabricacion = fechaFabricacion;
        this.historialEstados = historialEstados;
        this.id = id;
        this.matricula = matricula;
        this.modelo = modelo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Date getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(Date fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Set<Asiento> getAsientos() {
        return asientos;
    }

    public void setAsientos(Set<Asiento> asientos) {
        this.asientos = asientos;
    }

    public Set<HistorialEstado> getHistorialEstados() {
        return historialEstados;
    }

    public void setHistorialEstados(Set<HistorialEstado> historialEstados) {
        this.historialEstados = historialEstados;
    }

    @Override
    public String toString() {
        return String.format("Avión [ID=%d, Matrícula=%s, Capacidad=%d, Fecha de Fabricación=%s, Aerolínea=%s, Modelo=%s]", 
                             id, matricula, capacidad, fechaFabricacion, aerolinea, modelo);
    }
}
