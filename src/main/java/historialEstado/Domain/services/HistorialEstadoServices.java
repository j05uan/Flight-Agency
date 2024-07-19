package historialEstado.Domain.services;

import java.util.List;

import historialEstado.Domain.entity.HistorialEstado;

public interface HistorialEstadoServices {

    void crearHistorialEstado(HistorialEstado historialEstado);
    List<HistorialEstado> obtenerTodosLosHistorialEstados();
    HistorialEstado obtenerHistorialEstadoPorId(Long id);
    void actualizarHistorialEstado (HistorialEstado historialEstado);
    void eliminarHistorialEstado(Long id);
    
}
