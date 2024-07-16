package tipoEmpleado.Application;

import java.util.List;

import tipoEmpleado.Domain.entity.TipoEmpleado;
import tipoEmpleado.Domain.services.TipoEmpleadoServices;

public class TipoEmpleadoUseCase {
private final TipoEmpleadoServices tipoEmpleadoServices;

    public TipoEmpleadoUseCase(TipoEmpleadoServices tipoEmpleadoServices) {
        this.tipoEmpleadoServices = tipoEmpleadoServices;
    }

    public void crearTipoEmpleado(TipoEmpleado tipoEmpleado) {
        tipoEmpleadoServices.crearTipoEmpleado(tipoEmpleado);
    }

    public List<TipoEmpleado> obtenerTodosLosTiposEmpleado() {
        return tipoEmpleadoServices.obtenerTodosLosTiposEmpleado();
    }

    public TipoEmpleado obtenerTipoEmpleadoPorId(Long id) {
        return tipoEmpleadoServices.obtenerTipoEmpleadoPorId(id);
    }

    public void actualizarTipoEmpleado(TipoEmpleado tipoEmpleado) {
        tipoEmpleadoServices.actualizarTipoEmpleado(tipoEmpleado);
    }

    public void eliminarTipoEmpleado(Long id) {
        tipoEmpleadoServices.eliminarTipoEmpleado(id);
    }
}
