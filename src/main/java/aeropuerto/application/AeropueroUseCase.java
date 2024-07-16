package aeropuerto.application;

import aeropuerto.domain.entity.Aeropuerto;
import aeropuerto.domain.services.AeropuertoServices;

public class AeropueroUseCase {
    private final AeropuertoServices aeropuertoServices;

    public AeropueroUseCase(AeropuertoServices aeropuertoServices) {
        this.aeropuertoServices = aeropuertoServices;
    }

    public void crearAeropuerto(Aeropuerto aeropuerto){
        aeropuertoServices.crearAeropuerto(aeropuerto);
    }
    
    public void executed(Aeropuerto aeropuerto){
        aeropuertoServices.crearAeropuerto(aeropuerto);
    }
}
