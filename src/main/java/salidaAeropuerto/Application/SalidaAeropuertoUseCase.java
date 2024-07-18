package salidaAeropuerto.Application;

import java.util.List;

import salidaAeropuerto.Domain.entity.SalidaAeropuerto;
import salidaAeropuerto.Domain.services.SalidaAeropuertoServices;

public class SalidaAeropuertoUseCase {

    private final SalidaAeropuertoServices salidaAeropuertoServices;

    public SalidaAeropuertoUseCase(SalidaAeropuertoServices salidaAeropuertoServices) {
        this.salidaAeropuertoServices = salidaAeropuertoServices;
    }

    public void crearSalidaAeropuerto(SalidaAeropuerto salidaAeropuerto) {
        // Validar que el objeto no sea nulo
        if (salidaAeropuerto == null) {
            throw new IllegalArgumentException("El objeto SalidaAeropuerto no puede ser nulo.");
        }
        salidaAeropuertoServices.crearSalidaAeropuerto(salidaAeropuerto);
    }

    public List<SalidaAeropuerto> obtenerTodosAeropuertoSalidas() {
        return salidaAeropuertoServices.obtenerTodosAeropuertoSalidas();
    }

    public SalidaAeropuerto obtenerSalidaAeropuertoPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo.");
        }
        return salidaAeropuertoServices.obtenerSalidaAeropuertoPorId(id);
    }

    public void actualizarSalidaAeropuerto(SalidaAeropuerto salidaAeropuerto) {
        // Validar que el objeto no sea nulo
        if (salidaAeropuerto == null) {
            throw new IllegalArgumentException("El objeto SalidaAeropuerto no puede ser nulo.");
        }
        // Validar que el ID esté presente y sea válido
        if (salidaAeropuerto.getId() == null || salidaAeropuerto.getId() <= 0) {
            throw new IllegalArgumentException("El ID de SalidaAeropuerto debe ser un número positivo.");
        }
        salidaAeropuertoServices.actualizarSalidaAeropuerto(salidaAeropuerto);
    }

    public void eliminarSalidaAeropuerto(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo.");
        }
        salidaAeropuertoServices.eliminarSalidaAeropuerto(id);
    }
}
