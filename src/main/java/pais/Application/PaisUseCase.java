package pais.Application;

import java.util.List;

import pais.Domain.Entity.Pais;
import pais.Domain.Services.PaisServices;

public class PaisUseCase {
    private final PaisServices paisServices;

    public PaisUseCase(PaisServices paisServices) {
        this.paisServices = paisServices;
    }
    
    public void crearPais(Pais pais) {
        paisServices.crearPais(pais);
    }
    
    public List<Pais> obtenerTodosLosPaises() {
        return paisServices.obtenerTodosLosPaises();
    }
    
    public Pais obtenerPaisPorId(Long id) {
        return paisServices.obtenerPaisPorId(id);
    }
    
    public void actualizarPais(Pais pais) {
        paisServices.actualizarPais(pais);
    }
    
    public void eliminarPais(Long id) {
        paisServices.eliminarPais(id);
    }
}
