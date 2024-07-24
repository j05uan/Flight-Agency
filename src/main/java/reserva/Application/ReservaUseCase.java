package reserva.Application;

import java.util.List;

import reserva.Domain.entity.Reserva;
import reserva.Domain.services.ReservaServices;

public class ReservaUseCase {

    public final ReservaServices reservaServices;

    public ReservaUseCase(ReservaServices reservaServices) {
        this.reservaServices = reservaServices;
    }

     public void crearReserva(Reserva reserva) {
        // Validar que el objeto no sea nulo
        if (reserva == null) {
            throw new IllegalArgumentException("El objeto reserva no puede ser nulo.");
        }
        reservaServices.crearReserva(reserva);
    }

    public List<Reserva> obtenerTodasReserva() {
        return reservaServices.obtenerTodasReserva();
    }

    public Reserva obtenerReservaPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo.");
        }
        return reservaServices.obtenerReservaPorId(id);
    }

    public void actualizarReserva(Reserva reserva) {
        // Validar que el objeto no sea nulo
        if (reserva == null) {
            throw new IllegalArgumentException("El objeto Reserva no puede ser nulo.");
        }
        // Validar que el ID esté presente y sea válido
        if (reserva.getId() == null || reserva.getId() <= 0) {
            throw new IllegalArgumentException("El ID de Reserva debe ser un número positivo.");
        }
        reservaServices.actualizarReserva(reserva);
    }

    public void eliminarReserva(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo.");
        }
        reservaServices.eliminarReserva(id);
    }

}
