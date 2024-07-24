package tripulacionDeVuelo.Application;

import java.util.List;

import tripulacionDeVuelo.Domain.entity.TripulacionDeVuelo;
import tripulacionDeVuelo.Domain.services.TripulacionDeVueloServices;

public class TripulacionDeVueloUseCase {

    private final TripulacionDeVueloServices tripulacionDeVueloServices;

    

    public TripulacionDeVueloUseCase(TripulacionDeVueloServices tripulacionDeVueloServices) {
        this.tripulacionDeVueloServices = tripulacionDeVueloServices;
    }

    public void crearTripulacionDeVuelo(TripulacionDeVuelo tripulacionDeVuelo) {
        // Validar que el objeto no sea nulo
        if (tripulacionDeVuelo == null) {
            throw new IllegalArgumentException("El objeto TripulacionDeVuelo no puede ser nulo.");
        }
        tripulacionDeVueloServices.crearTripulacionDeVuelo(tripulacionDeVuelo);
    }

    public List<TripulacionDeVuelo> obtenerTodosAeropuertoSalidas() {
        return tripulacionDeVueloServices.obtenerTodosAeropuertoSalidas();
    }

    public TripulacionDeVuelo obtenerTripulacionDeVueloPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo.");
        }
        return tripulacionDeVueloServices.obtenerTripulacionDeVueloPorId(id);
    }

    public void actualizarTripulacionDeVuelo(TripulacionDeVuelo tripulacionDeVuelo) {
        // Validar que el objeto no sea nulo
        if (tripulacionDeVuelo == null) {
            throw new IllegalArgumentException("El objeto TripulacionDeVuelo no puede ser nulo.");
        }
        // Validar que el ID esté presente y sea válido
        if (tripulacionDeVuelo.getId() == null || tripulacionDeVuelo.getId() <= 0) {
            throw new IllegalArgumentException("El ID de tripulacionDeVuelo debe ser un número positivo.");
        }
        tripulacionDeVueloServices.actualizarTripulacionDeVuelo(tripulacionDeVuelo);
    }

    public void eliminarTripulacionDeVuelo(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo.");
        }
        tripulacionDeVueloServices.eliminarTripulacionDeVuelo(id);
    }
}

