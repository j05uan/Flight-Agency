package ciudad.Domain.Services;

import java.util.List;

import ciudad.Domain.Entity.Ciudad;

public interface CiudadServices {
    void crearCiudad(Ciudad ciudad);
    List<Ciudad> obtenerTodasLasCiudades();
    Ciudad obtenerCiudadPorId(Long id);
    void actualizarCiudad(Ciudad ciudad);
    void eliminarCiudad(Long id);
}
