package tipoEmpleado.infraestructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import resource.ConfiguracionBaseDeDatos;
import tipoEmpleado.Domain.entity.TipoEmpleado;
import tipoEmpleado.Domain.services.TipoEmpleadoServices;

public class TipoEmpleadoRepository implements TipoEmpleadoServices {

    @Override
    public void crearTipoEmpleado(TipoEmpleado tipoEmpleado) {
        String sql = "INSERT INTO tipo_empleado (tipo) VALUES (?)";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, tipoEmpleado.getTipo());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        tipoEmpleado.setId(generatedKeys.getLong(1));  // Establecer el ID generado
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TipoEmpleado> obtenerTodosLosTiposEmpleado() {
        List<TipoEmpleado> tiposEmpleado = new ArrayList<>();
        String sql = "SELECT * FROM tipo_empleado";
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                TipoEmpleado tipoEmpleado = new TipoEmpleado();
                tipoEmpleado.setId(resultSet.getLong("id"));
                tipoEmpleado.setTipo(resultSet.getString("tipo"));
                tiposEmpleado.add(tipoEmpleado);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tiposEmpleado;
    }

    @Override
    public TipoEmpleado obtenerTipoEmpleadoPorId(Long id) {
        TipoEmpleado tipoEmpleado = null;
        String sql = "SELECT * FROM tipo_empleado WHERE id = ?";
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    tipoEmpleado = new TipoEmpleado();
                    tipoEmpleado.setId(resultSet.getLong("id"));
                    tipoEmpleado.setTipo(resultSet.getString("tipo"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tipoEmpleado;
    }

    @Override
    public void actualizarTipoEmpleado(TipoEmpleado tipoEmpleado) {
        String sql = "UPDATE tipo_empleado SET tipo = ? WHERE id = ?";
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, tipoEmpleado.getTipo());
            statement.setLong(2, tipoEmpleado.getId());
            
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarTipoEmpleado(Long id) {
        String sql = "DELETE FROM tipo_empleado WHERE id = ?";
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
