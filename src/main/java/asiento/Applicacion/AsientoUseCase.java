package asiento.Applicacion;

import java.util.List;

import asiento.Domain.Entity.Asiento;
import asiento.Domain.Services.AsientoServices;

public class AsientoUseCase {

     private final AsientoServices asientoServices ;

    public AsientoUseCase(AsientoServices asientoServices) {
        this.asientoServices = asientoServices;
    }
    
    public void crearAsiento(Asiento asiento) {
        asientoServices.crearAsiento(asiento);
    }
    
    public List<Asiento> obtenerTodasLasAsientos() {
        return asientoServices.obtenerTodasLasAsientos();
    }
    
    public Asiento obtenerAsientoPorId(Long id) {
        return asientoServices.obtenerAsientoPorId(id);
    }
    
    public void actualizarAsiento(Asiento asiento) {
        asientoServices.actualizarAsiento(asiento);
    }
    
    public void eliminarAsiento(Long id) {
        asientoServices.eliminarAsiento(id);
    }
}


