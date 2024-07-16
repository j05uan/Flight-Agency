package aerolinea.application;

import java.util.List;

import aerolinea.Domain.Entity.Aerolinea;
import aerolinea.Domain.Services.AerolineaServices;

public class AerolineaUseCase {
    private final AerolineaServices aerolineaServices;

    public AerolineaUseCase(AerolineaServices aerolineaServices) {
        this.aerolineaServices = aerolineaServices;
    }
    
    public void crearAerolinea(Aerolinea aerolinea) {
        aerolineaServices.crearAerolinea(aerolinea);
    }
    
    public List<Aerolinea> obtenerTodasLasAerolineas() {
        return aerolineaServices.obtenerTodasLasAerolineas();
    }
    
    public Aerolinea obtenerAerolineaPorId(Long id) {
        return aerolineaServices.obtenerAerolineaPorId(id);
    }
    
    public void actualizarAerolinea(Aerolinea aerolinea) {
        aerolineaServices.actualizarAerolinea(aerolinea);
    }
    
    public void eliminarAerolinea(Long id) {
        aerolineaServices.eliminarAerolinea(id);
    }
}
