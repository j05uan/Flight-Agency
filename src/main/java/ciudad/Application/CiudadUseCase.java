package ciudad.Application;

import java.util.List;

import ciudad.Domain.Entity.Ciudad;
import ciudad.Domain.Services.CiudadServices;

public class CiudadUseCase {
     private final CiudadServices ciudadServices;

    public CiudadUseCase(CiudadServices ciudadServices) {
        this.ciudadServices = ciudadServices;
    }
    
    public void crearCiudad(Ciudad ciudad) {
        ciudadServices.crearCiudad(ciudad);
    }
    
    public List<Ciudad> obtenerTodasLasCiudades() {
        return ciudadServices.obtenerTodasLasCiudades();
    }
    
    public Ciudad obtenerCiudadPorId(Long id) {
        return ciudadServices.obtenerCiudadPorId(id);
    }
    
    public void actualizarCiudad(Ciudad ciudad) {
        ciudadServices.actualizarCiudad(ciudad);
    }
    
    public void eliminarCiudad(Long id) {
        ciudadServices.eliminarCiudad(id);
    }
}
