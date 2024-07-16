package empleado.Domain.services;

import java.util.List;

import empleado.Domain.entity.Empleado;

public interface EmpleadoServices {
    void crearEmpleado(Empleado empleado);
    List<Empleado> obtenerTodosLosEmpleados();
    Empleado obtenerEmpleadoPorId(Long id);
    void actualizarEmpleado(Empleado empleado);
    void eliminarEmpleado(Long id);
}
