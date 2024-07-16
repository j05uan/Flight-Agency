package tipoEmpleado.Domain.services;

import java.util.List;

import tipoEmpleado.Domain.entity.TipoEmpleado;

public interface TipoEmpleadoServices {
    void crearTipoEmpleado(TipoEmpleado tipoEmpleado);
    List<TipoEmpleado> obtenerTodosLosTiposEmpleado();
    TipoEmpleado obtenerTipoEmpleadoPorId(Long id);
    void actualizarTipoEmpleado(TipoEmpleado tipoEmpleado);
    void eliminarTipoEmpleado(Long id);
}
