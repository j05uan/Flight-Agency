package pasajero.Domain.services;

import java.util.List;

import pasajero.Domain.entity.Pasajero;

public interface PasajeroServices {
    void crearPasajero (Pasajero pasajero);
    List<Pasajero> obtenerTodosLosPasajeros();
    Pasajero obtenerPasajeroPorId(Long id);
    void actualizarPasajero (Pasajero pasajero);
    void eliminarPasajero(Long id);

}
