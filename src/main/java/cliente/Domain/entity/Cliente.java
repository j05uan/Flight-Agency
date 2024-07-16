package cliente.Domain.entity;

import java.util.Set;

import pasajero.Domain.entity.Pasajero;
import reserva.Domain.entity.Reserva;
import tipoDocumento.Domain.entity.TipoDocumento;

public class Cliente {
    private Long id;
    private String nombre;
    private int edad;
    private TipoDocumento tipoDocumento;
    private String documento;
    private Set<Reserva> reservas;
    private Pasajero pasajero;

    public Cliente() {
    }

    public Cliente(String documento, int edad, Long id, String nombre, Pasajero pasajero, Set<Reserva> reservas, TipoDocumento tipoDocumento) {
        this.documento = documento;
        this.edad = edad;
        this.id = id;
        this.nombre = nombre;
        this.pasajero = pasajero;
        this.reservas = reservas;
        this.tipoDocumento = tipoDocumento;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }



}
