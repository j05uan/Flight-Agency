package aeropuerto.domain.entity;

import java.util.Set;

import ciudad.Domain.Entity.Ciudad;
import escala.Domain.entity.Escala;
import salidaAeropuerto.Domain.entity.SalidaAeropuerto;

public class Aeropuerto {
    private Long id;
    private String nombre;
    private Ciudad ciudad;
    private Set<Escala> escalas;
    private Set<SalidaAeropuerto> salidas;

    public Aeropuerto() {
    }

    public Aeropuerto(Ciudad ciudad, Set<Escala> escalas, Long id, String nombre, Set<SalidaAeropuerto> salidas) {
        this.ciudad = ciudad;
        this.escalas = escalas;
        this.id = id;
        this.nombre = nombre;
        this.salidas = salidas;
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

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Set<Escala> getEscalas() {
        return escalas;
    }

    public void setEscalas(Set<Escala> escalas) {
        this.escalas = escalas;
    }

    public Set<SalidaAeropuerto> getSalidas() {
        return salidas;
    }

    public void setSalidas(Set<SalidaAeropuerto> salidas) {
        this.salidas = salidas;
    }



}
