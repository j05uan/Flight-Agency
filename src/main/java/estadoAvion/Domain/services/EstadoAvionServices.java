package estadoAvion.Domain.services;

import java.util.List;

import estadoAvion.Domain.entity.EstadoAvion;

public interface EstadoAvionServices {
    void crearEstadoAvion(EstadoAvion estadoAvion);
    List<EstadoAvion> obtenerTodosLosEstadosAvion();
    EstadoAvion obtenerEstadoAvionPorId(Long id);
    void actualizarEstadoAvion(EstadoAvion estadoAvion);
    void eliminarEstadoAvion(Long id);
}
