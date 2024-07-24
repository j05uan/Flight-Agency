package reserva.Domain.services;

import java.util.List;

import reserva.Domain.entity.Reserva;

public interface ReservaServices {
    void crearReserva (Reserva reserva);
    List<Reserva> obtenerTodasReserva();
    Reserva obtenerReservaPorId (Long id);
    void actualizarReserva (Reserva reserva);
    void eliminarReserva (Long id);
}
