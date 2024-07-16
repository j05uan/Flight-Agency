package asiento.Domain.Entity;

import java.util.Set;

import asientoReserva.Domain.Entity.AsientosReserva;
import avion.Domain.Entity.Avion;

public class Asiento {
    private Long id;
    private Avion avion;
    private int fila;
    private String columna;
    private boolean disponible;
    private Set<AsientosReserva> asientosReservas;
    public Asiento() {
    }
    public Asiento(Long id, Avion avion, int fila, String columna, boolean disponible,
            Set<AsientosReserva> asientosReservas) {
        this.id = id;
        this.avion = avion;
        this.fila = fila;
        this.columna = columna;
        this.disponible = disponible;
        this.asientosReservas = asientosReservas;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Avion getAvion() {
        return avion;
    }
    public void setAvion(Avion avion) {
        this.avion = avion;
    }
    public int getFila() {
        return fila;
    }
    public void setFila(int fila) {
        this.fila = fila;
    }
    public String getColumna() {
        return columna;
    }
    public void setColumna(String columna) {
        this.columna = columna;
    }
    public boolean isDisponible() {
        return disponible;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    public Set<AsientosReserva> getAsientosReservas() {
        return asientosReservas;
    }
    public void setAsientosReservas(Set<AsientosReserva> asientosReservas) {
        this.asientosReservas = asientosReservas;
    }

    
}
