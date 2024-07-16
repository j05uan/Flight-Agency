package aeropuerto.application;

import aeropuerto.domain.entity.Aeropuerto;
import aeropuerto.domain.services.AeropuertoServices;

public class EncontrarAeropuertoUseCase {

    private final AeropuertoServices aeropuertoServices;

    public EncontrarAeropuertoUseCase(AeropuertoServices aeropuertoServices) {
        this.aeropuertoServices = aeropuertoServices;
    }

    public Aeropuerto executed(Long id){
        Aeropuerto EncontrarAeropuerto = aeropuertoServices.encontrarAeropuerto(id);
        return EncontrarAeropuerto;

    }

}
