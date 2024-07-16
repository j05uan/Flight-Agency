package rolTripulante.Domain.entity;

import java.util.Set;

import tripulacionDeVuelo.Domain.entity.TripulacionDeVuelo;

public class RolTripulante {
    private Long id;
    private String rol;
    private Set<TripulacionDeVuelo> tripulaciones;

    public RolTripulante() {
    }

    public RolTripulante(Long id, String rol, Set<TripulacionDeVuelo> tripulaciones) {
        this.id = id;
        this.rol = rol;
        this.tripulaciones = tripulaciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Set<TripulacionDeVuelo> getTripulaciones() {
        return tripulaciones;
    }

    public void setTripulaciones(Set<TripulacionDeVuelo> tripulaciones) {
        this.tripulaciones = tripulaciones;
    }



}
