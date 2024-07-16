package tipoEmpleado.infraestructure.out;

import java.util.ArrayList;
import java.util.List;

import tipoEmpleado.Domain.entity.TipoEmpleado;
import tipoEmpleado.Domain.services.TipoEmpleadoServices;

public class TipoEmpleadoRepository implements TipoEmpleadoServices {
    private final List<TipoEmpleado> tiposEmpleado = new ArrayList<>();

    @Override
    public void crearTipoEmpleado(TipoEmpleado tipoEmpleado) {
        tiposEmpleado.add(tipoEmpleado);
    }

    @Override
    public List<TipoEmpleado> obtenerTodosLosTiposEmpleado() {
        return new ArrayList<>(tiposEmpleado);
    }

    @Override
    public TipoEmpleado obtenerTipoEmpleadoPorId(Long id) {
        return tiposEmpleado.stream()
                .filter(tipoEmpleado -> tipoEmpleado.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void actualizarTipoEmpleado(TipoEmpleado tipoEmpleado) {
        TipoEmpleado tipoEmpleadoExistente = obtenerTipoEmpleadoPorId(tipoEmpleado.getId());
        if (tipoEmpleadoExistente != null) {
            tipoEmpleadoExistente.setTipo(tipoEmpleado.getTipo());
        }
    }

    @Override
    public void eliminarTipoEmpleado(Long id) {
        tiposEmpleado.removeIf(tipoEmpleado -> tipoEmpleado.getId().equals(id));
    }
}
