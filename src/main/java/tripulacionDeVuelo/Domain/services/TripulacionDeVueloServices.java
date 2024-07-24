package tripulacionDeVuelo.Domain.services;

import java.util.List;

import tripulacionDeVuelo.Domain.entity.TripulacionDeVuelo;

public interface TripulacionDeVueloServices {
    void crearTripulacionDeVuelo (TripulacionDeVuelo tripulacionDeVuelo);
    List<TripulacionDeVuelo> obtenerTodosAeropuertoSalidas();
    TripulacionDeVuelo obtenerTripulacionDeVueloPorId (Long id);
    void actualizarTripulacionDeVuelo (TripulacionDeVuelo tripulacionDeVuelo);
    void eliminarTripulacionDeVuelo (Long id);

}
