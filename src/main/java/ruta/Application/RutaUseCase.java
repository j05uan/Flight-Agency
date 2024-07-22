package ruta.Application;

import java.util.List;

import ruta.Domain.Entity.Ruta;
import ruta.Domain.Services.RutaServices;

public class RutaUseCase {
    
    private final RutaServices rutaServices;

    public RutaUseCase(RutaServices rutaServices) {
        this.rutaServices = rutaServices;
    }
    
    public void crearRuta(Ruta ruta){
        if(ruta == null){
            throw new IllegalArgumentException("La ruta no puede ser nula");
        }
        rutaServices.CrearRuta(ruta);
    }

    public List<Ruta> obtenerTodasLasRutas(){
        return rutaServices.obtenerTodasLasRutas();
    }

    public Ruta obtenerRutaPorId(Long id){
        if(id == null || id <= 0){
            throw new IllegalArgumentException("El ID debe ser un numero positivo");
        }
        return rutaServices.obtenerRutaPorId(id);
    }

    public void actualizarRuta(Ruta ruta){
        if (ruta == null){
            throw new IllegalArgumentException("La ruta no puede ser nula");
        }
        if(ruta.getId() == null || ruta.getId() <=0 ){
            throw new IllegalArgumentException("El ID debe ser un numero positivo");
        }
        rutaServices.actualizarRuta(ruta);
    }
    public void eliminarRuta(Long id){
        if(id == null || id <= 0){
            throw new IllegalArgumentException("El ID debe ser un numero positivo");
        }
        rutaServices.eliminarRuta(id);
    }

}
