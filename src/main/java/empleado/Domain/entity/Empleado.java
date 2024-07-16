package empleado.Domain.entity;

import java.util.Date;
import java.util.Set;

import aerolinea.Domain.Entity.Aerolinea;
import tipoEmpleado.Domain.entity.TipoEmpleado;
import tripulacionDeVuelo.Domain.entity.TripulacionDeVuelo;

public class Empleado {
    private Long id;
    private String nombre;
    private java.util.Date fechaIngreso;
    private Aerolinea aerolinea;
    private TipoEmpleado tipoEmpleado;
    private Set<TripulacionDeVuelo> tripulaciones;

    public Empleado() {
    }

    public Empleado(Aerolinea aerolinea, Date fechaIngreso, Long id, String nombre, TipoEmpleado tipoEmpleado, Set<TripulacionDeVuelo> tripulaciones) {
        this.aerolinea = aerolinea;
        this.fechaIngreso = fechaIngreso;
        this.id = id;
        this.nombre = nombre;
        this.tipoEmpleado = tipoEmpleado;
        this.tripulaciones = tripulaciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }

    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public Set<TripulacionDeVuelo> getTripulaciones() {
        return tripulaciones;
    }

    public void setTripulaciones(Set<TripulacionDeVuelo> tripulaciones) {
        this.tripulaciones = tripulaciones;
    }

    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", aerolinea=" + aerolinea +
                ", tipoEmpleado=" + tipoEmpleado +
                ", tripulaciones=" + tripulaciones +
                '}';
    }
    

}
