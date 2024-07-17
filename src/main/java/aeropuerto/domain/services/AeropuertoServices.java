package aeropuerto.domain.services;

import java.util.List;

import aeropuerto.domain.entity.Aeropuerto;

public interface AeropuertoServices {
    void crearAeropuerto(Aeropuerto aeropuerto);
    // Aeropuerto encontrarAeropuerto(Long id);
    void actualizarAeropuerto (Aeropuerto aeropuerto);
    void elimnarAeropuerto (Long id);
    List<Aeropuerto> obtenerTodosLosAeropuertos();
    Aeropuerto obtenerAeropuertoPorId(long id);
}
