package empleado.Application;

import java.util.List;

import empleado.Domain.entity.Empleado;
import empleado.Domain.services.EmpleadoServices;

public class EmpleadoUseCase {
      private final EmpleadoServices empleadoServices;

    public EmpleadoUseCase(EmpleadoServices empleadoServices) {
        this.empleadoServices = empleadoServices;
    }

    public void crearEmpleado(Empleado empleado) {
        empleadoServices.crearEmpleado(empleado);
    }

    public List<Empleado> obtenerTodosLosEmpleados() {
        return empleadoServices.obtenerTodosLosEmpleados();
    }

    public Empleado obtenerEmpleadoPorId(Long id) {
        return empleadoServices.obtenerEmpleadoPorId(id);
    }

    public void actualizarEmpleado(Empleado empleado) {
        empleadoServices.actualizarEmpleado(empleado);
    }

    public void eliminarEmpleado(Long id) {
        empleadoServices.eliminarEmpleado(id);
    }

}
