package pasajero.Domain.entity;

import java.util.Set;

import asientoReserva.Domain.Entity.AsientosReserva;
import cliente.Domain.entity.Cliente;

public class Pasajero {
    private Long id;
    private Cliente cliente;
    private String nombre;
    private String apellido;
    private Set<AsientosReserva> asientosReservas;
    
    public Pasajero() {
    }

    public Pasajero(String apellido, Set<AsientosReserva> asientosReservas, Cliente cliente, Long id, String nombre) {
        this.apellido = apellido;
        this.asientosReservas = asientosReservas;
        this.cliente = cliente;
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Set<AsientosReserva> getAsientosReservas() {
        return asientosReservas;
    }

    public void setAsientosReservas(Set<AsientosReserva> asientosReservas) {
        this.asientosReservas = asientosReservas;
    }



    
}
