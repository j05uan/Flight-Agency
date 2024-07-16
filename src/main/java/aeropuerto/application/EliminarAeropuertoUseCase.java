package aeropuerto.application;

import aeropuerto.domain.services.AeropuertoServices;

public class EliminarAeropuertoUseCase {

    private final AeropuertoServices aeropuertoServices;

    public EliminarAeropuertoUseCase(AeropuertoServices aeropuertoServices) {
        this.aeropuertoServices = aeropuertoServices;
    }

    public void execute(Long id) {
        aeropuertoServices.elimnarAeropuerto(id);
    }
}
