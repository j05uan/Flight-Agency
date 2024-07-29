package empleado.infraestructure.out;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import empleado.Domain.entity.Empleado;
import empleado.Domain.services.EmpleadoServices;
import resource.ConfiguracionBaseDeDatos;

public class EmpleadoRepository implements EmpleadoServices {

    @Override
    public void crearEmpleado(Empleado empleado) {
        String sql = "INSERT INTO empleados (nombre, fecha_ingreso, aerolinea_id, tipo_empleado_id) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, empleado.getNombre());
            statement.setDate(2, new Date(empleado.getFechaIngreso().getTime()));
            statement.setLong(3, empleado.getAerolinea().getId());
            statement.setLong(4, empleado.getTipoEmpleado().getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        empleado.setId(generatedKeys.getLong(1));  // Establecer el ID generado
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Empleado> obtenerTodosLosEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(resultSet.getLong("id"));
                empleado.setNombre(resultSet.getString("nombre"));
                empleado.setFechaIngreso(resultSet.getDate("fecha_ingreso"));
                // Aquí deberías obtener los objetos Aerolinea y TipoEmpleado de la base de datos o tenerlos disponibles
                // empleado.setAerolinea(...);
                // empleado.setTipoEmpleado(...);
                empleados.add(empleado);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empleados;
    }

    @Override
    public Empleado obtenerEmpleadoPorId(Long id) {
        Empleado empleado = null;
        String sql = "SELECT * FROM empleados WHERE id = ?";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    empleado = new Empleado();
                    empleado.setId(resultSet.getLong("id"));
                    empleado.setNombre(resultSet.getString("nombre"));
                    empleado.setFechaIngreso(resultSet.getDate("fecha_ingreso"));
                    // Aquí deberías obtener los objetos Aerolinea y TipoEmpleado de la base de datos o tenerlos disponibles
                    // empleado.setAerolinea(...);
                    // empleado.setTipoEmpleado(...);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empleado;
    }

    @Override
    public void actualizarEmpleado(Empleado empleado) {
        String sql = "UPDATE empleados SET nombre = ?, fecha_ingreso = ?, aerolinea_id = ?, tipo_empleado_id = ? WHERE id = ?";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, empleado.getNombre());
            statement.setDate(2, new Date(empleado.getFechaIngreso().getTime()));
            statement.setLong(3, empleado.getAerolinea().getId());
            statement.setLong(4, empleado.getTipoEmpleado().getId());
            statement.setLong(5, empleado.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarEmpleado(Long id) {
        String sql = "DELETE FROM empleados WHERE id = ?";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
