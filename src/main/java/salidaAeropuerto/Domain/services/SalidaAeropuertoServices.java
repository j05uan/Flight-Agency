package salidaAeropuerto.Domain.services;

import java.util.List;

import salidaAeropuerto.Domain.entity.SalidaAeropuerto;

public interface SalidaAeropuertoServices {
    void crearSalidaAeropuerto (SalidaAeropuerto aeropuertoSalida);
    List<SalidaAeropuerto> obtenerTodosAeropuertoSalidas();
    SalidaAeropuerto obtenerSalidaAeropuertoPorId (Long id);
    void actualizarSalidaAeropuerto (SalidaAeropuerto salidaAeropuerto);
    void eliminarSalidaAeropuerto (Long id);
}
