package estadoAvion.Application;

import java.util.List;

import estadoAvion.Domain.entity.EstadoAvion;
import estadoAvion.Domain.services.EstadoAvionServices;

public class EstadoAvionUseCase {
    private final EstadoAvionServices estadoAvionServices;

    public EstadoAvionUseCase(EstadoAvionServices estadoAvionServices) {
        this.estadoAvionServices = estadoAvionServices;
    }

    public void crearEstadoAvion (EstadoAvion estadoAvion){
        estadoAvionServices.crearEstadoAvion(estadoAvion);
    }

    public List<EstadoAvion> obtenerTodosLosEstadosAvion(){
        return estadoAvionServices.obtenerTodosLosEstadosAvion();
    }

    public EstadoAvion obtenerEstadoAvionPorId(Long id){
        return estadoAvionServices.obtenerEstadoAvionPorId(id);
    }

    public void actualizarEstadoAvion(EstadoAvion estadoAvion){
        estadoAvionServices.actualizarEstadoAvion(estadoAvion);
    }

    public void eliminarEstadoAvion(Long id){
        estadoAvionServices.eliminarEstadoAvion(id);
    }


}
