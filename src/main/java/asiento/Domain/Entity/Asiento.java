package asiento.Domain.Entity;

import java.util.Set;

import asientoReserva.Domain.Entity.AsientosReserva;
import avion.Domain.Entity.Avion;

public class Asiento {
    private Long id;
    private String nombre;
    private Set<AsientosReserva> asientosReservas;
    public Asiento() {
    }
    public Asiento(Long id, String nombre, Set<AsientosReserva> asientosReservas) {
        this.id = id;
        this.nombre = nombre;
        this.asientosReservas = asientosReservas;
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
    public Set<AsientosReserva> getAsientosReservas() {
        return asientosReservas;
    }
    public void setAsientosReservas(Set<AsientosReserva> asientosReservas) {
        this.asientosReservas = asientosReservas;
    }
    
    
}
