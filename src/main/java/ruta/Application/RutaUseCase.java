package ruta.Application;

import ruta.Domain.Entity.Ruta;
import ruta.Domain.Services.RutaServices;

public class RutaUseCase {
    
    private final RutaServices rutaServices;

    public RutaUseCase(RutaServices rutaServices) {
        this.rutaServices = rutaServices;
    }
    
    public void crearRuta(Ruta ruta){
        rutaServices.CrearRuta(ruta);
    }

}
