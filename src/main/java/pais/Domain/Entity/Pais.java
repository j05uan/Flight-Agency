package pais.Domain.Entity;

import java.util.Set;

import ciudad.Domain.Entity.Ciudad;



public class Pais {
    private Long id;
    private String nombre;
    private Set<Ciudad> ciudades;

    public Pais() {
    }

    public Pais(Set<Ciudad> ciudades, Long id, String nombre) {
        this.ciudades = ciudades;
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

    public Set<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(Set<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }


}
