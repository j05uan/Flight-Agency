package ciudad.Domain.Entity;

import java.util.Set;

import aeropuerto.domain.entity.Aeropuerto;
import pais.Domain.Entity.Pais;

public class Ciudad {
    private Long id;
    private String nombre;
    private Pais pais;
    private Set<Aeropuerto> aeropuertos;

    public Ciudad() {
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Set<Aeropuerto> getAeropuertos() {
        return aeropuertos;
    }

    public void setAeropuertos(Set<Aeropuerto> aeropuertos) {
        this.aeropuertos = aeropuertos;
    }


}
