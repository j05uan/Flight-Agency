package aeropuerto.application;

import aeropuerto.domain.entity.Aeropuerto;
import aeropuerto.domain.services.AeropuertoServices;

public class ActualizarAeropuerto {

    private final AeropuertoServices aeropuertoServices;

    public ActualizarAeropuerto(AeropuertoServices aeropuertoServices) {
        this.aeropuertoServices = aeropuertoServices;
    }

    public void execute(Aeropuerto aeropuerto){
        aeropuertoServices.actualizarAeropuerto(aeropuerto);
    }

}
