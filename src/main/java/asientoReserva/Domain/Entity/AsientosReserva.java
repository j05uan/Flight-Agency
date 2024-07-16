package asientoReserva.Domain.Entity;

import asiento.Domain.Entity.Asiento;
import pasajero.Domain.entity.Pasajero;
import reserva.Domain.entity.Reserva;

public class AsientosReserva {
    private Long id;
    private Reserva reserva;
    private Asiento asiento;
    private Pasajero pasajero;

    public AsientosReserva() {
    }

    public AsientosReserva(Asiento asiento, Long id, Pasajero pasajero, Reserva reserva) {
        this.asiento = asiento;
        this.id = id;
        this.pasajero = pasajero;
        this.reserva = reserva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }



}
