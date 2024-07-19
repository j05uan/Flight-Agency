package historialEstado.application;

import java.util.List;

import estadoAvion.Domain.entity.EstadoAvion;
import historialEstado.Domain.entity.HistorialEstado;
import historialEstado.Domain.services.HistorialEstadoServices;

public class HistorialEstadoUseCase {

    private final HistorialEstadoServices historialEstadoServices;

    public HistorialEstadoUseCase(HistorialEstadoServices historialEstadoServices) {
        this.historialEstadoServices = historialEstadoServices;
    }

    public void crearHistorialEstado(HistorialEstado historialEstado){
        historialEstadoServices.crearHistorialEstado(historialEstado);
    }

    public List<HistorialEstado> obtenerTodosLosHistorialEstados(){
        return historialEstadoServices.obtenerTodosLosHistorialEstados();
    }

    public HistorialEstado obtenerHistorialEstadoPorId(Long id){
        return historialEstadoServices.obtenerHistorialEstadoPorId(id);
    }

    public void actualizarHistorialEstado(HistorialEstado historialEstado){
        historialEstadoServices.actualizarHistorialEstado(historialEstado);
    }

    public void eliminarHistorialEstado(Long id){
        historialEstadoServices.eliminarHistorialEstado(id);
    }

}
