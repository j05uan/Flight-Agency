package ciudad.infraestructure.out;

import java.util.ArrayList;
import java.util.List;

import ciudad.Domain.Entity.Ciudad;
import ciudad.Domain.Services.CiudadServices;

public class CiudadRepository implements CiudadServices {
    
     private final List<Ciudad> ciudades = new ArrayList<>();

    @Override
    public void crearCiudad(Ciudad ciudad) {
        ciudades.add(ciudad);
    }



    @Override
    public Ciudad obtenerCiudadPorId(Long id) {
        return ciudades.stream()
                .filter(ciudad -> ciudad.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void actualizarCiudad(Ciudad ciudad) {
        Ciudad ciudadExistente = obtenerCiudadPorId(ciudad.getId());
        if (ciudadExistente != null) {
            ciudadExistente.setNombre(ciudad.getNombre());
            ciudadExistente.setPais(ciudad.getPais());
        }
    }

    @Override
    public void eliminarCiudad(Long id) {
        ciudades.removeIf(ciudad -> ciudad.getId().equals(id));
    }



    @Override
    public List<Ciudad> obtenerTodasLasCiudades() {
        return new ArrayList<>(ciudades);
    }
}
