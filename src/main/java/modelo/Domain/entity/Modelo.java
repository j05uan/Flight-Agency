package modelo.Domain.entity;

import java.util.Set;

import avion.Domain.Entity.Avion;
import fabricante.Domain.Entity.Fabricante;

public class Modelo {
    private Long id;
    private String nombre;
    private Fabricante fabricante;
    private Set<Avion> aviones;

    public Modelo() {
    }

    public Modelo(Set<Avion> aviones, Fabricante fabricante, Long id, String nombre) {
        this.aviones = aviones;
        this.fabricante = fabricante;
        this.id = id;
        this.nombre = nombre;
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

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public Set<Avion> getAviones() {
        return aviones;
    }

    public void setAviones(Set<Avion> aviones) {
        this.aviones = aviones;
    }
    
}
    

