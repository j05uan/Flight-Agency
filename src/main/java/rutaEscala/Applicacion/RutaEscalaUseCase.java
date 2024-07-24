package rutaEscala.Applicacion;

import java.util.List;

import rutaEscala.Domain.entity.RutaEscala;
import rutaEscala.Domain.services.RutaEscalaServices;

public class RutaEscalaUseCase {

    private final RutaEscalaServices rutaEscalaServices;

    public RutaEscalaUseCase(RutaEscalaServices rutaEscalaServices) {
        this.rutaEscalaServices = rutaEscalaServices;
    }
    
     public void crearRutaEscala(RutaEscala rutaEscala) {
        // Validar que el objeto no sea nulo
        if (rutaEscala == null) {
            throw new IllegalArgumentException("El objeto rutaEscala no puede ser nulo.");
        }
        rutaEscalaServices.crearRutaEscala(rutaEscala);
    }

    public List<RutaEscala> obtenerTodasRutaEscalas() {
        return rutaEscalaServices.obtenerTodasRutaEscalas();
    }

    public RutaEscala obtenerEscalaPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo.");
        }
        return rutaEscalaServices.obtenerEscalaPorId(id);
    }

    public void actualizarEscala(RutaEscala rutaEscala) {
        // Validar que el objeto no sea nulo
        if (rutaEscala == null) {
            throw new IllegalArgumentException("El objeto Escala no puede ser nulo.");
        }
        // Validar que el ID esté presente y sea válido
        if (rutaEscala.getId() == null || rutaEscala.getId() <= 0) {
            throw new IllegalArgumentException("El ID de Escala debe ser un número positivo.");
        }
        rutaEscalaServices.actualizarEscala(rutaEscala);
    }

    public void eliminarEscala(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo.");
        }
        rutaEscalaServices.eliminarEscala(id);
    }


}
