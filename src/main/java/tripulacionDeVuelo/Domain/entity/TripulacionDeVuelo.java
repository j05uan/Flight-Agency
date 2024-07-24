package tripulacionDeVuelo.Domain.entity;

import empleado.Domain.entity.Empleado;
import rolTripulante.Domain.entity.RolTripulante;
import rutaEscala.Domain.entity.RutaEscala;

public class TripulacionDeVuelo {
    private Long id;
    private RutaEscala vuelo;
    private Empleado empleado;
    private RolTripulante rol;

    public TripulacionDeVuelo() {
    }

    public TripulacionDeVuelo(Empleado empleado, Long id, RolTripulante rol, RutaEscala vuelo) {
        this.empleado = empleado;
        this.id = id;
        this.rol = rol;
        this.vuelo = vuelo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RutaEscala getVuelo() {
        return vuelo;
    }

    public void setVuelo(RutaEscala vuelo) {
        this.vuelo = vuelo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public RolTripulante getRol() {
        return rol;
    }

    public void setRol(RolTripulante rol) {
        this.rol = rol;
    }  

}
