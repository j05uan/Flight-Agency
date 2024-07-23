package pasajero.Applicacion;

import java.util.List;

import pasajero.Domain.entity.Pasajero;
import pasajero.Domain.services.PasajeroServices;

public class PasajeroUseCase {
    private final PasajeroServices pasajeroServices;

    public PasajeroUseCase(PasajeroServices pasajeroServices) {
        this.pasajeroServices = pasajeroServices;
    }

    // Crear un nuevo pasajero
    public void crearPasajero(Pasajero pasajero) {
        if (pasajero == null) {
            throw new IllegalArgumentException("El objeto pasajero no puede ser nulo");
        }
        pasajeroServices.crearPasajero(pasajero);
    }

    // Obtener una lista de todos los pasajeros
    public List<Pasajero> obtenerTodosLosPasajeros() {
        return pasajeroServices.obtenerTodosLosPasajeros();
    }

    // Obtener un pasajero por su ID
    public Pasajero obtenerPasajeroPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El Id debe ser un número positivo");
        }
        return pasajeroServices.obtenerPasajeroPorId(id);
    }

    // Actualizar un pasajero existente
    public void actualizarPasajero(Pasajero pasajero) {
        if (pasajero == null) {
            throw new IllegalArgumentException("El objeto pasajero no puede ser nulo");
        }
        if (pasajero.getId() == null || pasajero.getId() <= 0) {
            throw new IllegalArgumentException("El Id del pasajero debe ser un número positivo");
        }
        pasajeroServices.actualizarPasajero(pasajero);
    }

    // Eliminar un pasajero por su ID
    public void eliminarPasajero(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El Id debe ser un número positivo");
        }
        pasajeroServices.eliminarPasajero(id);
    }
}
