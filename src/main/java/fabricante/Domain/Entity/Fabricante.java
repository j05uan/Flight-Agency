package fabricante.Domain.Entity;

import java.util.Set;

import modelo.Domain.entity.Modelo;

public class Fabricante {
    private Long id;

    private String nombre;

    private Set<Modelo> modelos;

    public Fabricante() {
    }

    public Fabricante(Long id, Set<Modelo> modelos, String nombre) {
        this.id = id;
        this.modelos = modelos;
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

    public Set<Modelo> getModelos() {
        return modelos;
    }

    public void setModelos(Set<Modelo> modelos) {
        this.modelos = modelos;
    }


}
