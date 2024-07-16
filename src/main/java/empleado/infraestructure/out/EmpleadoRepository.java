package empleado.infraestructure.out;

import java.util.ArrayList;
import java.util.List;

import empleado.Domain.entity.Empleado;
import empleado.Domain.services.EmpleadoServices;

public class EmpleadoRepository implements EmpleadoServices{
private final List<Empleado> empleados = new ArrayList<>();

    @Override
    public void crearEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    @Override
    public List<Empleado> obtenerTodosLosEmpleados() {
        return new ArrayList<>(empleados);
    }

    @Override
    public Empleado obtenerEmpleadoPorId(Long id) {
        return empleados.stream()
                .filter(empleado -> empleado.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void actualizarEmpleado(Empleado empleado) {
        Empleado empleadoExistente = obtenerEmpleadoPorId(empleado.getId());
        if (empleadoExistente != null) {
            empleadoExistente.setNombre(empleado.getNombre());
            empleadoExistente.setFechaIngreso(empleado.getFechaIngreso());
            empleadoExistente.setAerolinea(empleado.getAerolinea());
            empleadoExistente.setTipoEmpleado(empleado.getTipoEmpleado());
        }
    }

    @Override
    public void eliminarEmpleado(Long id) {
        empleados.removeIf(empleado -> empleado.getId().equals(id));
    }
}
