package aeropuerto.application;

import java.util.List;

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

    public List<Aeropuerto> obtenerTodosLosAeropuertos(){
        return aeropuertoServices.obtenerTodosLosAeropuertos();
    }

    public Aeropuerto obtenerAeropuertoPorId( Long id){
        return aeropuertoServices.obtenerAeropuertoPorId(id);
    }

    public void actualizarAeropuerto(Aeropuerto aeropuerto){
        aeropuertoServices.actualizarAeropuerto(aeropuerto);
    }

    public void eliminarAeropuerto (Long id){
        aeropuertoServices.elimnarAeropuerto(id);
    }
}
