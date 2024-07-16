package aerolinea.Domain.Entity;

import java.util.Set;

import avion.Domain.Entity.Avion;
import empleado.Domain.entity.Empleado;

public class Aerolinea {
    private Long id;
    private String nombre;
    private Set<Avion> aviones;
    private Set<Empleado> empleados;

    public Aerolinea() {
    }

    public Aerolinea(Long id, String nombre, Set<Avion> aviones, Set<Empleado> empleados) {
        this.id = id;
        this.nombre = nombre;
        this.aviones = aviones;
        this.empleados = empleados;
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

    public Set<Avion> getAviones() {
        return aviones;
    }

    public void setAviones(Set<Avion> aviones) {
        this.aviones = aviones;
    }

    public Set<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }

    

}
